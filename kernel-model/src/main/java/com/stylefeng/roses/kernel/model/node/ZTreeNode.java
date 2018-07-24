package com.stylefeng.roses.kernel.model.node;

import lombok.Data;

/**
 * jquery ztree 插件的节点
 *
 * @author fengshuonan
 * @date 2017年2月17日 下午8:25:14
 */
@Data
public class ZTreeNode {

    private Integer id;     //节点id

    private Integer pId;    //父节点id

    private String name;    //节点名称

    private Boolean open;   //是否打开节点

    private Boolean checked = false;//是否被选中

    private Integer isMenu; //是否是菜单(1:是功能权限   2:是操作权限)

    public static ZTreeNode createParent() {
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setChecked(true);
        zTreeNode.setId(0);
        zTreeNode.setName("顶级");
        zTreeNode.setOpen(true);
        zTreeNode.setPId(0);
        return zTreeNode;
    }

}
