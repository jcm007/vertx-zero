package io.vertx.tp.ke.booter;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.tp.plugin.excel.atom.ExTable;

import java.util.List;
import java.util.Set;

/*
 * Split booter for some divide application of tool
 * 1) Loader
 */
public class Bt {

    /*
     * doImport
     * doImport: with prefix to do filter
     */
    public static void doImports(final String folder) {
        BtLoader.doImports(folder);
    }

    public static Future<Boolean> impAsync(final String folder) {
        return BtLoader.impAsync(folder);
    }

    public static Future<Boolean> impAsync(final String folder, final String prefix) {
        return BtLoader.impAsync(folder, prefix);
    }

    public static void doImports(final String folder, final String prefix) {
        BtLoader.doImports(folder, prefix);
    }

    public static void doImports(final String folder, final Handler<AsyncResult<List<String>>> callback) {
        BtLoader.doImports(folder, callback);
    }

    public static void ingestExcels(final String folder, final Handler<AsyncResult<Set<ExTable>>> callback) {
        BtLoader.doIngests(folder, callback);
    }
}
