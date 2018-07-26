package com.stylefeng.roses.core.treebuild;

import com.stylefeng.roses.core.treebuild.abst.AbstractTreeBuildFactory;
import com.stylefeng.roses.kernel.model.tree.Tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 默认递归工具类，用于遍历有父子关系的节点，例如菜单树，字典树等等
 *
 * @author fengshuonan
 * @Date 2018/7/25 下午5:59
 */
public class DefaultTreeBuildFactory<T extends Tree> extends AbstractTreeBuildFactory<T> {

    /**
     * 顶级节点的父节点id
     */
    private static final String ROOT_PARENT_ID = "-1";

    /**
     * 查询子节点时候的临时集合
     */
    private List<T> tempList = new ArrayList<>();

    /**
     * 查询子节点的集合
     *
     * @param nodeList 所有节点的集合
     * @param nodeId   被查询节点的id
     */
    private List<T> findChildNodes(List<T> nodeList, String nodeId) {
        if (nodeList == null || nodeId == null)
            return null;
        for (T nodeItem : nodeList) {
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (!nodeItem.getNodeParentId().equals(ROOT_PARENT_ID) && nodeId.equals(nodeItem.getNodeParentId())) {
                recursionFn(nodeList, nodeItem, nodeId);
            }
        }
        return tempList;
    }

    /**
     * 遍历一个节点的子节点
     *
     * @param nodeList 所有节点的集合
     * @param node     被操作的节点
     * @param pId      被操作节点的父级id
     */
    private void recursionFn(List<T> nodeList, T node, String pId) {

        // 获取子一级节点的集合
        List<T> childList = getSubChildsLevelOne(nodeList, node);

        // 判断子一级节点中是否还有子节点
        if (childList.size() > 0) {
            if (node.getNodeParentId().equals(pId)) {
                tempList.add(node);
            }
            Iterator<T> it = childList.iterator();
            while (it.hasNext()) {
                T n = it.next();
                recursionFn(nodeList, n, pId);
            }
        } else {
            if (node.getNodeParentId().equals(pId)) {
                tempList.add(node);
            }
        }
    }

    /**
     * 获取子一级节点的集合
     *
     * @param list 所有节点的集合
     * @param node 被查询节点的model
     * @author fengshuonan
     */
    private List<T> getSubChildsLevelOne(List<T> list, T node) {
        List<T> nodeList = new ArrayList<>();
        for (T nodeItem : list) {
            if (nodeItem.getNodeParentId().equals(node.getNodeId())) {
                nodeList.add(nodeItem);
            }
        }
        return nodeList;
    }

    @Override
    protected List<T> beforeBuild(List<T> nodes) {

        //默认不进行前置处理,直接返回

        return nodes;
    }

    @Override
    protected List<T> executeBuilding(List<T> nodes) {
        for (T treeNode : nodes) {
            List<T> linkedList = this.findChildNodes(nodes, treeNode.getNodeId());
            if (linkedList.size() > 0) {
                treeNode.setChildrenNodes(linkedList);
            }
        }
        return nodes;
    }

    @Override
    protected List<T> afterBuild(List<T> nodes) {

        //去掉所有的二级节点
        ArrayList<T> results = new ArrayList<>();
        for (T node : nodes) {
            if (node.getNodeParentId().equals(ROOT_PARENT_ID)) {
                results.add(node);
            }
        }
        return results;
    }
}