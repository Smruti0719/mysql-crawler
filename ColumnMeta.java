package com.crawler.model;

public class ColumnMeta {

    private String name;
    private String type;
    private int size;
    private boolean nullable;

    public ColumnMeta(String name, String type, int size, boolean nullable) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.nullable = nullable;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getSize() { return size; }
    public boolean isNullable() { return nullable; }
}

