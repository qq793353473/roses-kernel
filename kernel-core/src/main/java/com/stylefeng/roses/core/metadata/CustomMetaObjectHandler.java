package com.stylefeng.roses.core.metadata;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.stylefeng.roses.core.context.LoginContext;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 自定义sql字段填充器,本类默认在default-config.properties中配置
 * <p>
 * 若实际项目中，字段名称不一样，可以新建一个此类，在yml配置中覆盖mybatis-plus.global-config.metaObject-handler配置即可
 *
 * @author fengshuonan
 * @Date 2018/7/4 下午12:42
 */
public class CustomMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object delFlag = getFieldValByName(getDeleteFlagFieldName(), metaObject);
        if (delFlag == null) {
            setFieldValByName(getDeleteFlagFieldName(), getDefaultDelFlagValue(), metaObject);
        }

        Object createTime = getFieldValByName(getCreateTimeFieldName(), metaObject);
        if (createTime == null) {
            setFieldValByName(getCreateTimeFieldName(), new Date(), metaObject);
        }

        Object createUser = getFieldValByName(getCreateUserFieldName(), metaObject);
        if (createUser == null) {

            //如果获取不到当前登录用户
            Object accountId = null;
            try {
                accountId = LoginContext.me().getUserUniqueId();
            } catch (Exception e) {
                //如果获取不到当前用户id就不记录
            }

            setFieldValByName(getCreateUserFieldName(), accountId, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(getUpdateTimeFieldName(), new Date(), metaObject);

        Object updateUser = getFieldValByName(getUpdateUserFieldName(), metaObject);
        if (updateUser == null) {

            //如果获取不到当前登录用户
            Object accountId = null;
            try {
                accountId = LoginContext.me().getUserUniqueId();
            } catch (Exception e) {
                //如果获取不到当前用户id就不记录
            }

            setFieldValByName(getUpdateUserFieldName(), accountId, metaObject);
        }
    }

    /**
     * 获取逻辑删除字段的名称（非数据库中字段名称）
     */
    protected String getDeleteFlagFieldName() {
        return "delFlag";
    }

    /**
     * 获取逻辑删除字段的默认值
     */
    protected Object getDefaultDelFlagValue() {
        return "N";
    }

    /**
     * 获取创建时间字段的名称（非数据库中字段名称）
     */
    protected String getCreateTimeFieldName() {
        return "createTime";
    }

    /**
     * 获取创建用户字段的名称（非数据库中字段名称）
     */
    protected String getCreateUserFieldName() {
        return "createUser";
    }

    /**
     * 获取更新时间字段的名称（非数据库中字段名称）
     */
    protected String getUpdateTimeFieldName() {
        return "updateTime";
    }

    /**
     * 获取更新用户字段的名称（非数据库中字段名称）
     */
    protected String getUpdateUserFieldName() {
        return "updateUser";
    }
}