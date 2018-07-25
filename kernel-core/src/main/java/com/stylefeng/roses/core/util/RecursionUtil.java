package com.stylefeng.roses.core.util;

import com.stylefeng.roses.kernel.model.enums.YesOrNotEnum;
import com.stylefeng.roses.kernel.model.recursion.Recursion;

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
public class RecursionUtil<T extends Recursion>{

    /**
     * 查询子节点时候的临时集合
     */
    private List<T> linkedList = new ArrayList<>();

    /**
     * 构建整个菜单树
     *
     * @author fengshuonan
     */
    public void buildNodeTree(List<T> nodeList) {
        for (T treeNode : nodeList) {
            List<T> linkedList = treeNode.findChildNodes(nodeList, treeNode.getId());
            if (linkedList.size() > 0) {
                treeNode.setChildren(linkedList);
            }
        }
    }

    /**
     * 查询子节点的集合
     *
     * @author fengshuonan
     */
    public List<T> findChildNodes(List<T> nodeList, Integer parentId) {
        if (nodeList == null && parentId == null)
            return null;
        for (Iterator<T> iterator = nodeList.iterator(); iterator.hasNext(); ) {
            T node = (T) iterator.next();
            // 根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (node.getParentId() != 0 && parentId.equals(node.getParentId())) {
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
    public void recursionFn(List<T> nodeList, T node, Integer pId) {
        List<T> childList = getChildList(nodeList, node);// 得到子节点列表
        if (childList.size() > 0) {// 判断是否有子节点
            if (node.getParentId().equals(pId)) {
                linkedList.add(node);
            }
            Iterator<T> it = childList.iterator();
            while (it.hasNext()) {
                T n = (T) it.next();
                recursionFn(nodeList, n, pId);
            }
        } else {
            if (node.getParentId().equals(pId)) {
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
        List<T> nodeList = new ArrayList<T>();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            T n = (T) it.next();
            if (n.getParentId().equals(node.getId())) {
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
    public static List<T> clearBtn(List<T> nodes) {
        ArrayList<T> noBtns = new ArrayList<T>();
        for (T node : nodes) {
            if (node.getMenuFlag() == YesOrNotEnum.Y.getFlag()) {
                noBtns.add(node);
            }
        }
        return noBtns;
    }

    /**
     * 清除所有二级菜单
     *
     * @date 2017年2月19日 下午11:18:19
     */
    public static List<T> clearLevelTwo(List<T> nodes) {
        ArrayList<T> results = new ArrayList<T>();
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
    public static List<T> buildTitle(List<T> nodes) {

        List<T> clearBtn = clearBtn(nodes);

        new T().buildNodeTree(clearBtn);

        List<T> menuNodes = clearLevelTwo(clearBtn);

        //对菜单排序
        Collections.sort(menuNodes);

        //对菜单的子菜单进行排序
        for (T menuNode : menuNodes) {
            if (menuNode.getChildren() != null && menuNode.getChildren().size() > 0) {
                Collections.sort(menuNode.getChildren());
            }
        }

        return menuNodes;
    }

}