package com.stylefeng.roses.kernel.model.tree;

import java.util.List;

/**
 * 构造树节点的接口规范
 *
 * @author fengshuonan
 * @date 2018-07-25-下午5:59
 */
public interface Tree extends Comparable {

    /**
     * 获取节点id
     */
    String getNodeId();

    /**
     * 获取节点父id
     */
    String getNodeParentId();

    /**
     * 获取子节点
     */
    List getChildrenNodes();

    /**
     * 设置children
     */
    void setChildrenNodes(List childrenNodes);

    /**
     * 获取菜单级别
     */
    Integer getLevels();
}
