/*
 * This file is generated by jOOQ.
*/
package cn.vertxup.ui.domain.tables.pojos;


import cn.vertxup.ui.domain.tables.interfaces.IUiForm;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.annotation.Generated;


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
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UiForm implements IUiForm {

    private static final long serialVersionUID = -2099931898;

    private String        key;
    private String        name;
    private String        code;
    private BigDecimal    window;
    private Integer       columns;
    private String        hidden;
    private String        row;
    private String        identifier;
    private Boolean       active;
    private String        sigma;
    private String        metadata;
    private String        language;
    private LocalDateTime createdAt;
    private String        createdBy;
    private LocalDateTime updatedAt;
    private String        updatedBy;

    public UiForm() {}

    public UiForm(UiForm value) {
        this.key = value.key;
        this.name = value.name;
        this.code = value.code;
        this.window = value.window;
        this.columns = value.columns;
        this.hidden = value.hidden;
        this.row = value.row;
        this.identifier = value.identifier;
        this.active = value.active;
        this.sigma = value.sigma;
        this.metadata = value.metadata;
        this.language = value.language;
        this.createdAt = value.createdAt;
        this.createdBy = value.createdBy;
        this.updatedAt = value.updatedAt;
        this.updatedBy = value.updatedBy;
    }

    public UiForm(
        String        key,
        String        name,
        String        code,
        BigDecimal    window,
        Integer       columns,
        String        hidden,
        String        row,
        String        identifier,
        Boolean       active,
        String        sigma,
        String        metadata,
        String        language,
        LocalDateTime createdAt,
        String        createdBy,
        LocalDateTime updatedAt,
        String        updatedBy
    ) {
        this.key = key;
        this.name = name;
        this.code = code;
        this.window = window;
        this.columns = columns;
        this.hidden = hidden;
        this.row = row;
        this.identifier = identifier;
        this.active = active;
        this.sigma = sigma;
        this.metadata = metadata;
        this.language = language;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public UiForm setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public UiForm setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public UiForm setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public BigDecimal getWindow() {
        return this.window;
    }

    @Override
    public UiForm setWindow(BigDecimal window) {
        this.window = window;
        return this;
    }

    @Override
    public Integer getColumns() {
        return this.columns;
    }

    @Override
    public UiForm setColumns(Integer columns) {
        this.columns = columns;
        return this;
    }

    @Override
    public String getHidden() {
        return this.hidden;
    }

    @Override
    public UiForm setHidden(String hidden) {
        this.hidden = hidden;
        return this;
    }

    @Override
    public String getRow() {
        return this.row;
    }

    @Override
    public UiForm setRow(String row) {
        this.row = row;
        return this;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public UiForm setIdentifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    @Override
    public Boolean getActive() {
        return this.active;
    }

    @Override
    public UiForm setActive(Boolean active) {
        this.active = active;
        return this;
    }

    @Override
    public String getSigma() {
        return this.sigma;
    }

    @Override
    public UiForm setSigma(String sigma) {
        this.sigma = sigma;
        return this;
    }

    @Override
    public String getMetadata() {
        return this.metadata;
    }

    @Override
    public UiForm setMetadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    @Override
    public String getLanguage() {
        return this.language;
    }

    @Override
    public UiForm setLanguage(String language) {
        this.language = language;
        return this;
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    @Override
    public UiForm setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @Override
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override
    public UiForm setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    @Override
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public UiForm setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public String getUpdatedBy() {
        return this.updatedBy;
    }

    @Override
    public UiForm setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UiForm (");

        sb.append(key);
        sb.append(", ").append(name);
        sb.append(", ").append(code);
        sb.append(", ").append(window);
        sb.append(", ").append(columns);
        sb.append(", ").append(hidden);
        sb.append(", ").append(row);
        sb.append(", ").append(identifier);
        sb.append(", ").append(active);
        sb.append(", ").append(sigma);
        sb.append(", ").append(metadata);
        sb.append(", ").append(language);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(createdBy);
        sb.append(", ").append(updatedAt);
        sb.append(", ").append(updatedBy);

        sb.append(")");
        return sb.toString();
    }

    // -------------------------------------------------------------------------
    // FROM and INTO
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public void from(IUiForm from) {
        setKey(from.getKey());
        setName(from.getName());
        setCode(from.getCode());
        setWindow(from.getWindow());
        setColumns(from.getColumns());
        setHidden(from.getHidden());
        setRow(from.getRow());
        setIdentifier(from.getIdentifier());
        setActive(from.getActive());
        setSigma(from.getSigma());
        setMetadata(from.getMetadata());
        setLanguage(from.getLanguage());
        setCreatedAt(from.getCreatedAt());
        setCreatedBy(from.getCreatedBy());
        setUpdatedAt(from.getUpdatedAt());
        setUpdatedBy(from.getUpdatedBy());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <E extends IUiForm> E into(E into) {
        into.from(this);
        return into;
    }

    public UiForm(io.vertx.core.json.JsonObject json) {
        fromJson(json);
    }
}
