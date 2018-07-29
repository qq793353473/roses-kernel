package com.stylefeng.roses.kernel.model.db;

/**
 * 数据库初始化，可初始化表，校验字段，校验表名是否存在等
 *
 * @author fengshuonan
 * @date 2018-07-29 22:05
 */
public interface DbInitializer {

    /**
     * 获取表的初始化语句
     *
     * @author stylefeng
     * @Date 2018/7/29 22:10
     */
    String getTableInitSql();

    /**
     * 获取表的名称
     *
     * @author stylefeng
     * @Date 2018/7/29 22:10
     */
    String getTableName();

    /**
     * 获取表的字段
     *
     * @author stylefeng
     * @Date 2018/7/29 22:49
     */
    String[] getTableFields();

    /**
     * 获取表对应的实体
     *
     * @author stylefeng
     * @Date 2018/7/29 22:49
     */
    Class<?> getEntityClass();
}
