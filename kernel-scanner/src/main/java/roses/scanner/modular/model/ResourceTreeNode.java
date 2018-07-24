package roses.scanner.modular.model;

import lombok.Data;

import java.util.List;

/**
 * 资源树
 *
 * @author fengshuonan
 * @date 2018-01-11 14:56
 */
@Data
public class ResourceTreeNode {

    /**
     * 资源中文名称
     */
    private String name;

    /**
     * 资源的编码
     */
    private String code;

    /**
     * 资源子节点
     */
    private List<ResourceTreeNode> children;

    public ResourceTreeNode() {
    }

    public ResourceTreeNode(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public ResourceTreeNode(String name, String code, List<ResourceTreeNode> children) {
        this.name = name;
        this.code = code;
        this.children = children;
    }

}
