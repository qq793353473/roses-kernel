package com.stylefeng.roses.kernel.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 简单代码生成器，service不生成接口
 *
 * @author fengshuonan
 * @Date 2018/7/20 下午1:17
 */
public class SimpleGenerator {

    public static void doGeneration(GenerateParams generateParams) {

        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(generateParams.getOutputDirectory());
        gc.setFileOverride(true);
        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setEnableCache(false);
        gc.setOpen(false);
        gc.setAuthor(generateParams.getAuthor());

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(generateParams.getJdbcDriver());
        dsc.setUrl(generateParams.getJdbcUrl());
        dsc.setUsername(generateParams.getJdbcUserName());
        dsc.setPassword(generateParams.getJdbcPassword());
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setCapitalMode(false);

        // 此处可以移除表前缀表前缀
        strategy.setTablePrefix(generateParams.getRemoveTablePrefix());

        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        // 需要生成的表
        strategy.setInclude(generateParams.getIncludeTables());

        mpg.setStrategy(strategy);

        // 自定义模板配置，可以 copy 源码 mybatis-plus/src/main/resources/templates 下面内容修改，
        // 放置自己项目的 src/main/resources/templates 目录下, 默认名称一下可以不配置，也可以自定义模板名称
        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);
        tc.setService(null);
        tc.setServiceImpl("/templates/NoneInterfaceServiceImpl.java");

        //如上任何一个模块如果设置 空 OR Null 将不生成该模块。
        mpg.setTemplate(tc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(generateParams.getParentPackage());
        pc.setModuleName("");
        pc.setXml("mapper.mapping");
        pc.setServiceImpl("service");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }

    public static void main(String[] args) {
        SimpleGenerator.doGeneration(new GenerateParams());
    }

}