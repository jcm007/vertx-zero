package io.vertx.tp.plugin.excel.tpl;

import io.vertx.up.fn.Fn;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author <a href="http://www.origin-x.cn">lang</a>
 */
class BlueDye {
    private static final ConcurrentMap<Integer, BlueDye> PICKER_POOL =
            new ConcurrentHashMap<>();
    /*
     * Fix Issue:
     * The maximum number of Cell Styles was exceeded. You can define up to 64000 style in a .xlsx Workbook
     */
    private static final ConcurrentMap<String, DyeCell> STYLE_POOL =
            new ConcurrentHashMap<>();

    private final transient Workbook workbook;

    private BlueDye(final Workbook workbook) {
        this.workbook = workbook;
    }
    /*
     * Style applying to fix issue:
     */

    static BlueDye get(final Workbook workbook) {
        return Fn.pool(PICKER_POOL, workbook.hashCode(), () -> new BlueDye(workbook));
    }

    /*
     * {TABLE} cell
     */
    void onTable(final Cell cell) {
        final DyeCell dyeCell = Fn.pool(STYLE_POOL, "TABLE",
                () -> DyeCell.create(this.workbook)
                        .color("FFFFFF", "3EB7FF")
                        .align(HorizontalAlignment.CENTER)
                        .border(BorderStyle.THIN)
                        .font(13, false));
        cell.setCellStyle(dyeCell.build());
    }

    void onModel(final Cell cell) {
        final DyeCell dyeCell = Fn.pool(STYLE_POOL, "MODEL",
                () -> DyeCell.create(this.workbook)
                        .color("FFFFFF", "696969")
                        .align(HorizontalAlignment.CENTER)
                        .border(BorderStyle.THIN)
                        .font(13, false));
        cell.setCellStyle(dyeCell.build());
    }

    void onEmpty(final Cell cell) {
        final DyeCell dyeCell = Fn.pool(STYLE_POOL, "EMPTY",
                () -> DyeCell.create(this.workbook)
                        .align(HorizontalAlignment.CENTER)
                        .border(BorderStyle.THIN));
        cell.setCellStyle(dyeCell.build());
    }

    void onCnHeader(final Cell cell) {
        this.onTable(cell);
    }

    void onEnHeader(final Cell cell) {
        final DyeCell dyeCell = Fn.pool(STYLE_POOL, "HEADER",
                () -> DyeCell.create(this.workbook)
                        .color("000000", "FFEC8B")
                        .align(HorizontalAlignment.CENTER)
                        .border(BorderStyle.THIN)
                        .font(13, false));
        cell.setCellStyle(dyeCell.build());
    }

    void onData(final Cell cell) {
        final DyeCell dyeCell = Fn.pool(STYLE_POOL, "DATA",
                () -> DyeCell.create(this.workbook)
                        .border(BorderStyle.THIN)
                        .font(13, false));
        cell.setCellStyle(dyeCell.build());
    }
}
