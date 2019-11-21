package cn.stylefeng.roses.core.reqres.request;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.google.common.base.CaseFormat;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

public class RequestQuery  extends RequestData {
    public RequestQuery() {
    }

    public Wrapper buildWrapper(Class<?> clazz) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (TableInfoHelper.getTableInfo(clazz).isLogicDelete()) {
            TableFieldInfo field = (TableFieldInfo)TableInfoHelper.getTableInfo(clazz).getFieldList().stream().filter(TableFieldInfo::isLogicDelete).findFirst().get();
            queryWrapper.eq(field.getColumn(), field.getLogicNotDeleteValue());
        }

        Field[] fields = this.getClass().getDeclaredFields();

        try {
            Field[] var4 = fields;
            int var5 = fields.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                Field f = var4[var6];
                String fieldName = f.toString().substring(f.toString().lastIndexOf(".") + 1);
                String[] splitFieldName = fieldName.split("_");
                if (splitFieldName.length == 2) {
                    String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = this.getClass().getMethod("get" + methodName);
                    Object val = method.invoke(this);
                    if (val != null) {
                        String columnName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, splitFieldName[1]);
                        String var14 = splitFieldName[0].toLowerCase();
                        byte var15 = -1;
                        switch(var14.hashCode()) {
                            case 3244:
                                if (var14.equals("eq")) {
                                    var15 = 0;
                                }
                                break;
                            case 3294:
                                if (var14.equals("ge")) {
                                    var15 = 3;
                                }
                                break;
                            case 3309:
                                if (var14.equals("gt")) {
                                    var15 = 2;
                                }
                                break;
                            case 3365:
                                if (var14.equals("in")) {
                                    var15 = 9;
                                }
                                break;
                            case 3449:
                                if (var14.equals("le")) {
                                    var15 = 5;
                                }
                                break;
                            case 3464:
                                if (var14.equals("lt")) {
                                    var15 = 4;
                                }
                                break;
                            case 3511:
                                if (var14.equals("ne")) {
                                    var15 = 1;
                                }
                                break;
                            case 3321751:
                                if (var14.equals("like")) {
                                    var15 = 6;
                                }
                                break;
                            case 102974389:
                                if (var14.equals("likel")) {
                                    var15 = 7;
                                }
                                break;
                            case 102974395:
                                if (var14.equals("liker")) {
                                    var15 = 8;
                                }
                                break;
                            case 105008952:
                                if (var14.equals("notin")) {
                                    var15 = 10;
                                }
                        }

                        switch(var15) {
                            case 0:
                                queryWrapper.eq(columnName, val);
                                break;
                            case 1:
                                queryWrapper.ne(columnName, val);
                                break;
                            case 2:
                                queryWrapper.gt(columnName, val);
                                break;
                            case 3:
                                queryWrapper.ge(columnName, val);
                                break;
                            case 4:
                                queryWrapper.lt(columnName, val);
                                break;
                            case 5:
                                queryWrapper.le(columnName, val);
                                break;
                            case 6:
                                queryWrapper.like(columnName, val);
                                break;
                            case 7:
                                queryWrapper.likeLeft(columnName, val);
                                break;
                            case 8:
                                queryWrapper.likeRight(columnName, val);
                                break;
                            case 9:
                                queryWrapper.in(columnName, (Collection)val);
                                break;
                            case 10:
                                queryWrapper.notIn(columnName, (Collection)val);
                        }
                    }
                }
            }

            return queryWrapper;
        } catch (Exception var16) {
            throw new RuntimeException(var16);
        }
    }
}