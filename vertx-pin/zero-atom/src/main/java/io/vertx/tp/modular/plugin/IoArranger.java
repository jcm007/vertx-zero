package io.vertx.tp.modular.plugin;

import cn.vertxup.atom.domain.tables.pojos.MAttribute;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.atom.modeling.Model;
import io.vertx.tp.atom.modeling.element.DataTpl;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.atom.Kv;
import io.vertx.up.commune.Record;
import io.vertx.up.commune.element.JComponent;
import io.vertx.up.fn.TiConsumer;
import io.vertx.up.util.Ut;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * ## Manager of ...
 *
 * This class will parse DataTpl for standard workflow
 *
 * @author <a href="http://www.origin-x.cn">Lang</a>
 */
class IoArranger {
    private static final String PLUGIN_CONFIG = "plugin.config";

    /**
     * Extract `IComponent` here.
     *
     * @param tpl {@link DataTpl}
     *
     * @return {@link java.util.concurrent.ConcurrentMap} The JComponent map for each field.
     */
    static ConcurrentMap<String, JComponent> pluginIn(final DataTpl tpl) {
        return extractPlugin(tpl, MAttribute::getInComponent, IComponent.class);
    }

    /**
     * Extract `OComponent` here.
     *
     * @param tpl {@link DataTpl}
     *
     * @return {@link java.util.concurrent.ConcurrentMap} The JComponent map for each field.
     */
    static ConcurrentMap<String, JComponent> pluginOut(final DataTpl tpl) {
        return extractPlugin(tpl, MAttribute::getOutComponent, OComponent.class);
    }

    /**
     * Extract `INormalizer` here.
     *
     * @param tpl {@link DataTpl}
     *
     * @return {@link java.util.concurrent.ConcurrentMap} The JComponent map for each field.
     */
    static ConcurrentMap<String, JComponent> pluginNormalize(final DataTpl tpl) {
        return extractPlugin(tpl, MAttribute::getNormalize, INormalizer.class);
    }

    /**
     * Extract `OExpression` here.
     *
     * @param tpl {@link DataTpl}
     *
     * @return {@link java.util.concurrent.ConcurrentMap} The JComponent map for each field.
     */
    static ConcurrentMap<String, JComponent> pluginExpression(final DataTpl tpl) {
        return extractPlugin(tpl, MAttribute::getExpression, OExpression.class);
    }


    /**
     * @param tpl          {@link DataTpl} The model definition template in data event
     * @param fnComponent  {@link java.util.function.Function} The component name extract from.
     * @param interfaceCls {@link java.lang.Class} required interface class
     *
     * @return {@link java.util.concurrent.ConcurrentMap} The JComponent map for each field.
     */
    private static ConcurrentMap<String, JComponent> extractPlugin(
            final DataTpl tpl, final Function<MAttribute, String> fnComponent, final Class<?> interfaceCls) {
        /*
         * 1. Iterate tpl attributes.
         */
        final Model model = tpl.atom().getModel();
        final ConcurrentMap<String, JComponent> pluginMap = new ConcurrentHashMap<>();
        model.getAttributes().forEach(attribute -> {
            /*
             * 2. Attribute
             */
            final String componentName = fnComponent.apply(attribute);
            final Class<?> componentCls = Ut.clazz(componentName, null);
            if (Objects.nonNull(componentCls)) {
                /*
                 * 3. SourceConfig
                 */
                final JComponent component = new JComponent(attribute.getName(), componentCls);
                if (component.valid(interfaceCls)) {
                    final JsonObject config = componentConfig(attribute, componentCls);
                    pluginMap.put(attribute.getName(), component.bind(config));
                }
            }
        });
        return pluginMap;
    }

    /**
     * Extract component configuration from attribute `sourceConfig` instead of other place.
     *
     * 1. sourceConfig stored component configuration.
     * 2. The json configuration came from `field = componentCls`.
     *
     * @param attribute    {@link MAttribute} Processed attribute that are related to `M_ATTRIBUTE`
     * @param componentCls {@link java.lang.Class} The component class of type
     *
     * @return {@link JsonObject} The extracted configuration of current component.
     */
    private static JsonObject componentConfig(final MAttribute attribute, final Class<?> componentCls) {
        final JsonObject sourceConfig = Ut.toJObject(attribute.getSourceConfig());
        final JsonObject combine;
        if (sourceConfig.containsKey(PLUGIN_CONFIG)) {
            final JsonObject pluginConfig = Ut.toJObject(sourceConfig.getValue(PLUGIN_CONFIG));
            if (pluginConfig.containsKey(componentCls.getName())) {
                combine = Ut.toJObject(pluginConfig.getValue(componentCls.getName()));
            } else {
                combine = new JsonObject();
            }
        } else {
            combine = new JsonObject();
        }
        combine.put(KeField.SOURCE, attribute.getSource());
        combine.put(KeField.SOURCE_FIELD, attribute.getSourceField());
        return combine;
    }

    // --------------------------- Execute The Workflow ------------------------
    private static <T extends IoSource> JsonObject sourceData(final ConcurrentMap<String, JComponent> inMap,
                                                              final Class<?> interfaceCls) {
        /*
         * Source Data Convert
         */
        final ConcurrentMap<String, JComponent> componentMap = new ConcurrentHashMap<>();
        inMap.values().forEach(component -> componentMap.put(component.source(), component));
        /*
         * Source Data Process
         */
        final JsonObject sourceData = new JsonObject();
        if (Objects.nonNull(interfaceCls)) {
            componentMap.values().forEach(component -> {
                final T componentInstance = component.instance(interfaceCls);
                if (Objects.nonNull(componentInstance)) {
                    /*
                     * Source Data Extract ( Code Logical )
                     */
                    componentInstance.source(component.configSource()).forEach(sourceData::put);
                }
            });
        }
        return sourceData;
    }

    static void runNorm(final Record[] records, final ConcurrentMap<String, JComponent> inMap) {
        run(records, inMap, null, (processed, component, config) -> {
            final INormalizer reference = component.instance(INormalizer.class);
            Arrays.stream(records).forEach(record -> run(record, component, reference::before));
        });
    }

    static void runNorm(final Record record, final ConcurrentMap<String, JComponent> normalizeMap) {
        run(record, normalizeMap, null, (processed, component, config) -> {
            final INormalizer reference = component.instance(INormalizer.class);
            run(record, component, reference::before);
        });
    }

    static void runExpr(final Record[] records, final ConcurrentMap<String, JComponent> inMap) {
        run(records, inMap, null, (processed, component, config) -> {
            final OExpression reference = component.instance(OExpression.class);
            Arrays.stream(records).forEach(record -> run(record, component, reference::after));
        });
    }

    static void runExpr(final Record record, final ConcurrentMap<String, JComponent> normalizeMap) {
        run(record, normalizeMap, null, (processed, component, config) -> {
            final OExpression reference = component.instance(OExpression.class);
            run(record, component, reference::after);
        });
    }

    static void runIn(final Record record, final ConcurrentMap<String, JComponent> inMap) {
        run(record, inMap, IComponent.class, (processed, component, config) -> {
            final IComponent reference = component.instance(IComponent.class);
            run(record, component, kv -> reference.before(kv, record, config));
        });
    }

    static void runIn(final Record[] records, final ConcurrentMap<String, JComponent> inMap) {
        run(records, inMap, IComponent.class, (processed, component, config) -> {
            final IComponent reference = component.instance(IComponent.class);
            Arrays.stream(records).forEach(record -> run(record, component, kv -> reference.before(kv, record, config)));
        });
    }

    static void runOut(final Record record, final ConcurrentMap<String, JComponent> inMap) {
        run(record, inMap, OComponent.class, (processed, component, config) -> {
            final OComponent reference = component.instance(OComponent.class);
            run(record, component, kv -> reference.after(kv, record, config));
        });
    }

    static void runOut(final Record[] records, final ConcurrentMap<String, JComponent> inMap) {
        run(records, inMap, OComponent.class, (processed, component, config) -> {
            final OComponent reference = component.instance(OComponent.class);
            Arrays.stream(records).forEach(record -> run(record, component, kv -> reference.after(kv, record, config)));
        });
    }

    /* Post Run */
    private static void run(final Record record, final JComponent component,
                            final Function<Kv<String, Object>, Object> executor) {
        final String field = component.key();
        final Kv<String, Object> kv = Kv.create(field, record.get(field));
        final Object processedValue = executor.apply(kv);
        if (Objects.nonNull(processedValue)) {
            /*
             * Replace original data
             */
            record.set(field, processedValue);
        }
    }

    /* Top Run */
    private static <T> void run(final T input, final ConcurrentMap<String, JComponent> inMap,
                                final Class<?> interfaceCls,
                                final TiConsumer<T, JComponent, JsonObject> consumer) {
        if (!inMap.isEmpty()) {
            final JsonObject dataMap = sourceData(inMap, interfaceCls);
            inMap.forEach((field, component) -> {
                final JsonObject config = component.config();
                config.put(KeField.SOURCE_DATA, dataMap);
                consumer.accept(input, component, config);
            });
        }
    }
}
