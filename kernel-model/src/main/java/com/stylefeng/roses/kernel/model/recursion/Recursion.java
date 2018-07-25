package com.stylefeng.roses.kernel.model.recursion;

import java.util.List;

/**
 * 递归操作的接口
 *
 * @author fengshuonan
 * @date 2018-07-25-下午5:59
 */
public interface Recursion<T> extends Comparable {

    List<T> getChildren();

}
