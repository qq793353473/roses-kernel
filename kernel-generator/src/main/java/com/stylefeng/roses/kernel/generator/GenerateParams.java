package com.stylefeng.roses.kernel.generator;

import lombok.Data;

/**
 * 代码生成所需要传递的参数
 *
 * @author fengshuonan
 * @date 2018-07-20-下午1:09
 */
@Data
public class GenerateParams {

    private String author = "fengshuonan";

    private String outputDirectory = "temp";

    private String jdbcDriver = "com.mysql.jdbc.Driver";

    private String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/guns?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=UTC";

    private String jdbcUserName = "root";

    private String jdbcPassword = "root";

    private String[] removeTablePrefix = {"xx_"};

    private String[] includeTables;

    private String parentPackage = "com.stylefeng.roses.xxx.modular";

}
