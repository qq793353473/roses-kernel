package com.stylefeng.roses.core.util;

import com.stylefeng.roses.kernel.model.tree.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 递归工具类，用于遍历有父子关系的节点，例如菜单树，字典树等等
 *
 * @author fengshuonan
 * @Date 2018/7/25 下午5:59
 */
public class TreeBuildFactory<T extends Tree> {

    /**
     * 查询子节点时候的临时集合
     */
    private List<T> linkedList = new ArrayList<>();

    /**
     * 构建整个菜单树
     *
     * @author fengshuonan
     */
    private void buildNodeTree(List<T> nodeList) {
        for (T treeNode : nodeList) {
            List<T> linkedList = this.findChildNodes(nodeList, treeNode.getNodeId());
            if (linkedList.size() > 0) {
                treeNode.setChildrenNodes(linkedList);
            }
        }
    }

    /**
     * 查询子节点的集合
     *
     * @author fengshuonan
     */
    private List<T> findChildNodes(List<T> nodeList, String parentId) {
        if (nodeList == null && parentId == null)
            return null;
        for (Iterator<T> iterator = nodeList.iterator(); iterator.hasNext(); ) {
            T node = iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (!node.getNodeParentId().equals("-1") && parentId.equals(node.getNodeParentId())) {
                recursionFn(nodeList, node, parentId);
            }
        }
        return linkedList;
    }

    /**
     * 遍历一个节点的子节点
     *
     * @author fengshuonan
     */
    private void recursionFn(List<T> nodeList, T node, String pId) {
        List<T> childList = getChildList(nodeList, node);// 得到子节点列表
        if (childList.size() > 0) {// 判断是否有子节点
            if (node.getNodeParentId().equals(pId)) {
                linkedList.add(node);
            }
            Iterator<T> it = childList.iterator();
            while (it.hasNext()) {
                T n = it.next();
                recursionFn(nodeList, n, pId);
            }
        } else {
            if (node.getNodeParentId().equals(pId)) {
                linkedList.add(node);
            }
        }
    }

    /**
     * 得到子节点列表
     *
     * @author fengshuonan
     */
    private List<T> getChildList(List<T> list, T node) {
        List<T> nodeList = new ArrayList<>();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T n = it.next();
            if (n.getNodeParentId().equals(node.getNodeId())) {
                nodeList.add(n);
            }
        }
        return nodeList;
    }

    /**
     * 清除掉按钮级别的资源
     *
     * @date 2017年2月19日 下午11:04:11
     */
    protected List<T> readToBuild(List<T> nodes) {
        return nodes;
    }

    /**
     * 清除所有二级菜单
     *
     * @date 2017年2月19日 下午11:18:19
     */
    private List<T> clearLevelTwo(List<T> nodes) {
        ArrayList<T> results = new ArrayList<>();
        for (T node : nodes) {
            Integer levels = node.getLevels();
            if (levels.equals(1)) {
                results.add(node);
            }
        }
        return results;
    }

    /**
     * 构建菜单列表
     *
     * @date 2017年2月19日 下午11:18:19
     */
    public List<T> doBuild(List<T> nodes) {

        List<T> processNodes = this.readToBuild(nodes);

        new TreeBuildFactory<T>().buildNodeTree(processNodes);

        List<T> menuNodes = clearLevelTwo(processNodes);

        //对菜单排序
        Collections.sort(menuNodes);

        //对菜单的子菜单进行排序
        for (T menuNode : menuNodes) {
            if (menuNode.getChildrenNodes() != null && menuNode.getChildrenNodes().size() > 0) {
                Collections.sort(menuNode.getChildrenNodes());
            }
        }

        return menuNodes;
    }
}