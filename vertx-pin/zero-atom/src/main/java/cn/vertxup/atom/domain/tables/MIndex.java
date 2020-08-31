/*
 * This file is generated by jOOQ.
 */
package cn.vertxup.atom.domain.tables;


import cn.vertxup.atom.domain.Db;
import cn.vertxup.atom.domain.Indexes;
import cn.vertxup.atom.domain.Keys;
import cn.vertxup.atom.domain.tables.records.MIndexRecord;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import javax.annotation.Generated;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 * This class is generated by jOOQ.
 */
@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.10.8"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class MIndex extends TableImpl<MIndexRecord> {

    /**
     * The reference instance of <code>DB_ETERNAL.M_INDEX</code>
     */
    public static final MIndex M_INDEX = new MIndex();
    private static final long serialVersionUID = -2056235212;
    /**
     * The column <code>DB_ETERNAL.M_INDEX.KEY</code>. 「key」- 索引ID
     */
    public final TableField<MIndexRecord, String> KEY = createField("KEY", org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "「key」- 索引ID");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.NAME</code>. 「name」- 索引名称
     */
    public final TableField<MIndexRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR(255), this, "「name」- 索引名称");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.TYPE</code>. 「type」- 索引类型
     */
    public final TableField<MIndexRecord, String> TYPE = createField("TYPE", org.jooq.impl.SQLDataType.VARCHAR(20), this, "「type」- 索引类型");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.CLUSTERED</code>. 「clustered」- 是否聚集索引
     */
    public final TableField<MIndexRecord, Boolean> CLUSTERED = createField("CLUSTERED", org.jooq.impl.SQLDataType.BIT, this, "「clustered」- 是否聚集索引");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.COLUMNS</code>. 「columns」- JsonArray格式，索引覆盖的列集合
     */
    public final TableField<MIndexRecord, String> COLUMNS = createField("COLUMNS", org.jooq.impl.SQLDataType.CLOB, this, "「columns」- JsonArray格式，索引覆盖的列集合");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.ENTITY_ID</code>. 「entityId」- 关联的实体ID
     */
    public final TableField<MIndexRecord, String> ENTITY_ID = createField("ENTITY_ID", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「entityId」- 关联的实体ID");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.COMMENTS</code>. 「comments」- 当前索引的描述信息
     */
    public final TableField<MIndexRecord, String> COMMENTS = createField("COMMENTS", org.jooq.impl.SQLDataType.CLOB, this, "「comments」- 当前索引的描述信息");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.SIGMA</code>. 「sigma」- 统一标识
     */
    public final TableField<MIndexRecord, String> SIGMA = createField("SIGMA", org.jooq.impl.SQLDataType.VARCHAR(32), this, "「sigma」- 统一标识");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.LANGUAGE</code>. 「language」- 使用的语言
     */
    public final TableField<MIndexRecord, String> LANGUAGE = createField("LANGUAGE", org.jooq.impl.SQLDataType.VARCHAR(10), this, "「language」- 使用的语言");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.ACTIVE</code>. 「active」- 是否启用
     */
    public final TableField<MIndexRecord, Boolean> ACTIVE = createField("ACTIVE", org.jooq.impl.SQLDataType.BIT, this, "「active」- 是否启用");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.METADATA</code>. 「metadata」- 附加配置数据
     */
    public final TableField<MIndexRecord, String> METADATA = createField("METADATA", org.jooq.impl.SQLDataType.CLOB, this, "「metadata」- 附加配置数据");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.CREATED_AT</code>. 「createdAt」- 创建时间
     */
    public final TableField<MIndexRecord, LocalDateTime> CREATED_AT = createField("CREATED_AT", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「createdAt」- 创建时间");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.CREATED_BY</code>. 「createdBy」- 创建人
     */
    public final TableField<MIndexRecord, String> CREATED_BY = createField("CREATED_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「createdBy」- 创建人");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.UPDATED_AT</code>. 「updatedAt」- 更新时间
     */
    public final TableField<MIndexRecord, LocalDateTime> UPDATED_AT = createField("UPDATED_AT", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "「updatedAt」- 更新时间");
    /**
     * The column <code>DB_ETERNAL.M_INDEX.UPDATED_BY</code>. 「updatedBy」- 更新人
     */
    public final TableField<MIndexRecord, String> UPDATED_BY = createField("UPDATED_BY", org.jooq.impl.SQLDataType.VARCHAR(36), this, "「updatedBy」- 更新人");

    /**
     * Create a <code>DB_ETERNAL.M_INDEX</code> table reference
     */
    public MIndex() {
        this(DSL.name("M_INDEX"), null);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.M_INDEX</code> table reference
     */
    public MIndex(String alias) {
        this(DSL.name(alias), M_INDEX);
    }

    /**
     * Create an aliased <code>DB_ETERNAL.M_INDEX</code> table reference
     */
    public MIndex(Name alias) {
        this(alias, M_INDEX);
    }

    private MIndex(Name alias, Table<MIndexRecord> aliased) {
        this(alias, aliased, null);
    }

    private MIndex(Name alias, Table<MIndexRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MIndexRecord> getRecordType() {
        return MIndexRecord.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Db.DB_ETERNAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.M_INDEX_IDX_M_INDEX_ENTITY_ID, Indexes.M_INDEX_NAME, Indexes.M_INDEX_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<MIndexRecord> getPrimaryKey() {
        return Keys.KEY_M_INDEX_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<MIndexRecord>> getKeys() {
        return Arrays.<UniqueKey<MIndexRecord>>asList(Keys.KEY_M_INDEX_PRIMARY, Keys.KEY_M_INDEX_NAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MIndex as(String alias) {
        return new MIndex(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MIndex as(Name alias) {
        return new MIndex(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public MIndex rename(String name) {
        return new MIndex(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MIndex rename(Name name) {
        return new MIndex(name, null);
    }
}
