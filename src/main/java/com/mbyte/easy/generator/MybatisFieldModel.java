package com.mbyte.easy.generator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @auther: wangzhen
 * @date: 19-4-11 21:53
 * @description: 封装mybatis参数
 */
public class MybatisFieldModel {
    private String column;
    private String jdbcType;
    private String property;
    private boolean keyFlag;

    public MybatisFieldModel() {
    }

    public MybatisFieldModel(String column, String jdbcType, String property, boolean keyFlag) {
        this.column = column;
        this.jdbcType = jdbcType;
        this.property = property;
        this.keyFlag = keyFlag;
    }

    public boolean isKeyFlag() {
        return keyFlag;
    }

    public void setKeyFlag(boolean keyFlag) {
        this.keyFlag = keyFlag;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "MybatisFieldModel{" +
                "column='" + column + '\'' +
                ", jdbcType='" + jdbcType + '\'' +
                ", property='" + property + '\'' +
                '}';
    }
}
