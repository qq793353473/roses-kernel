package com.stylefeng.roses.core.metadata;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.stylefeng.roses.core.context.LoginContext;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 自定义sql字段填充器
 *
 * @author fengshuonan
 * @Date 2018/7/4 下午12:42
 */
public class CustomMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object delFlag = getFieldValByName("delFlag", metaObject);
        if (delFlag == null) {
            setFieldValByName("delFlag", "N", metaObject);
        }

        Object createTime = getFieldValByName("createTime", metaObject);
        if (createTime == null) {
            setFieldValByName("createTime", new Date(), metaObject);
        }

        Object createUser = getFieldValByName("createUser", metaObject);
        if (createUser == null) {

            //如果获取不到当前登录用户
            String accountId = null;
            try {
                accountId = LoginContext.me().getAccountId();
            } catch (Exception e) {
                accountId = "";
            }

            setFieldValByName("createUser", accountId, metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", new Date(), metaObject);

        Object updateUser = getFieldValByName("updateUser", metaObject);
        if (updateUser == null) {

            //如果获取不到当前登录用户
            String accountId = null;
            try {
                accountId = LoginContext.me().getAccountId();
            } catch (Exception e) {
                accountId = "";
            }

            setFieldValByName("updateUser", accountId, metaObject);
        }
    }
}