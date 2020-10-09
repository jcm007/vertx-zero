package io.vertx.tp.plugin.cache.l1;

import io.vertx.core.json.JsonObject;
import io.vertx.tp.plugin.cache.hit.CacheMeta;
import io.vertx.up.util.Ut;

import java.util.Objects;

/**
 * @author <a href="http://www.origin-x.cn">lang</a>
 */
public class AlgorithmRecord extends AbstractL1Algorithm {
    @Override
    public <T> void dataDelivery(final JsonObject message, final T entity, final CacheMeta meta) {
        if (Objects.nonNull(message)) {
            message.put("data", (JsonObject) Ut.serializeJson(entity));
            message.put("collection", Boolean.FALSE);
        }
    }

    @Override
    public String dataType() {
        return "RECORD";
    }
}
