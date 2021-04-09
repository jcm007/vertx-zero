package io.vertx.tp.crud.connect;

import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.json.JsonObject;
import io.vertx.tp.crud.init.IxPin;
import io.vertx.tp.crud.refine.Ix;
import io.vertx.tp.ke.atom.metadata.KJoin;
import io.vertx.tp.ke.atom.metadata.KModule;
import io.vertx.tp.ke.cv.KeField;
import io.vertx.up.commune.Envelop;
import io.vertx.up.log.Annal;
import io.vertx.up.uca.jooq.UxJooq;
import io.vertx.up.unity.Ux;
import io.vertx.up.util.Ut;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

interface Pool {
    ConcurrentMap<String, IxLinker> LINKER_MAP =
            new ConcurrentHashMap<>();
}

interface OxSwitcher {

    static JsonObject getData(final JsonObject original, final KModule module) {
        /*
         * Safe call because of MoveOn
         */
        final KJoin connect = module.getConnect();
        /*
         * Remove primary key, it will generate new.
         */
        final JsonObject inputData = original.copy();
        final String mapped = connect.getJoined(original);
        if (Ut.notNil(mapped)) {
            /*
             * data is response data, here the code will generate final response
             */
            final String joinedValue = original.getString(connect.getMappedBy());
            inputData.put(mapped, joinedValue);
        }
        return inputData;
    }

    static JsonObject getCondition(final JsonObject original, final KModule module) {
        /*
         * Safe call because of MoveOn
         */
        final JsonObject filters = new JsonObject();
        final KJoin connect = module.getConnect();
        final String mapped = connect.getJoined(original);
        if (Ut.notNil(mapped)) {
            /*
             * joinedValue
             */
            final String joinedValue = original.getString(connect.getMappedBy());
            filters.put(mapped, joinedValue);
        }
        /*
         * Append `Sigma` Here
         */
        if (original.containsKey(KeField.SIGMA)) {
            filters.put("", Boolean.TRUE);
            filters.put(KeField.SIGMA, original.getString(KeField.SIGMA));
        }
        return filters;
    }

    static Future<Envelop> moveOn(final JsonObject data,
                                  final MultiMap headers,
                                  final KModule module,
                                  final BiFunction<UxJooq, KModule, Future<Envelop>> function) {
        /*
         * Linker data preparing
         */
        final KJoin connect = module.getConnect();
        final Annal LOGGER = Annal.get(OxSwitcher.class);
        if (Objects.isNull(connect)) {
            /*
             * IxJoin null, could not identify connect
             */
            Ix.infoDao(LOGGER, "IxJoin is null");
            return Ux.future(Envelop.success(data));
        } else {
            final String moduleName = connect.getJoinedBy();
            if (Ut.isNil(moduleName)) {
                Ix.infoDao(LOGGER, "The `joinedBy` field is null");
                return Ux.future(Envelop.success(data));
            } else {
                final String identifier = data.getString(moduleName);
                /*
                 * Get the data of module, data -> `moduleName` value
                 */
                final KModule config = connect.getModule(identifier, IxPin::getActor);
                if (Objects.isNull(config)) {
                    Ix.infoDao(LOGGER, "System could not find configuration for `{0}`, data = {1}",
                            identifier, connect.getJoined());
                    return Ux.future(Envelop.success(data));
                } else {
                    final UxJooq dao = IxPin.getDao(config, headers);
                    return function.apply(dao, config);
                }
            }
        }
    }

    static Future<Envelop> moveEnd(final JsonObject original, final Envelop response,
                                   final KModule config) {
        JsonObject createdJoined = response.data();
        /*
         * Merged two data here,
         * Be careful is that we must overwrite createdJoined
         * instead of data because the original data must be keep
         * Here are some modification of `key` here.
         * Here provide `joinedKey` field for target object.
         */
        if (Objects.isNull(createdJoined)) {
            createdJoined = new JsonObject();
        } else {
            final String joinedField = config.getField().getKey();
            createdJoined.put(KeField.JOINED_KEY, createdJoined.getString(joinedField));
        }
        createdJoined.mergeIn(original, true);
        return Ux.future(Envelop.success(createdJoined));
    }
}
