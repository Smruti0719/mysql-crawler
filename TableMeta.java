package com.crawler.model;

import java.util.ArrayList;
import java.util.List;

public class TableMeta {

    private String tableName;
    private List<ColumnMeta> columns = new ArrayList<>();
    private List<String> primaryKeys = new ArrayList<>();
    private List<ForeignKeyMeta> foreignKeys = new ArrayList<>();

   
    public TableMeta(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    public List<ColumnMeta> getColumns() {
        return columns;
    }

    public void addColumn(ColumnMeta column) {
        columns.add(column);
    }

    public void addPrimaryKey(String key) {
        primaryKeys.add(key);
    }

    public void addForeignKey(ForeignKeyMeta fk) {
        foreignKeys.add(fk);
    }

    public List<String> getPrimaryKeys() {
        return primaryKeys;
    }

    public List<ForeignKeyMeta> getForeignKeys() {
        return foreignKeys;
    }
}
