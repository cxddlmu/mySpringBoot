package com.cxd.permissionManager.dataSource;

/**
 * Created by cuixiaodong on 2018/5/6.
 */
public enum DataSourceType {
    master("master"), slave("slave");

    private DataSourceType(String name) {
        this.name = name();
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
