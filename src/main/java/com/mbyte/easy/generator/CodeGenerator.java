package com.mbyte.easy.generator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.*;

/**
 * <p>
 *  自动生成工具类
 * </p>
 *
 * @author 王震
 * @since 2019-03-11
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("黄润宣");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://129.204.110.32:3306/byte_easy?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("itenscen2015");
        mpg.setDataSource(dsc);
        /**
         * 输入表名和模块名
         */
        String moduleName = scanner("模块名");
        String tableName = scanner("表名");

        /**
        *                             t_
         * 忽略前缀配置 eg： t_test  ====> test
         */
        String ignorePrefix = scanner("忽略前缀:");


        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent("com.mbyte.easy");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
                Map<String, Object> map = new HashMap<>();
                map.put("superController", "com.mbyte.easy.common.controller.BaseController");
                this.setMap(map);
            }
        };

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass("com.mbyte.easy.common.entity.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(false);
        strategy.setSuperControllerClass("com.mbyte.easy.common.controller.BaseController");
        strategy.setInclude(tableName);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");

        List<FileOutConfig> focList = new ArrayList<>();

        //去除前缀
        String tableNameNew = tableName.indexOf(ignorePrefix) != -1 ? tableName.substring(ignorePrefix.length()) : tableName;

        String expand = projectPath + File.separator + "expand" + File.separator + pc.getModuleName()+File.separator+tableNameNew;

        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义Mapper.xml文件存放的路径
                return projectPath + "/src/main/resources/mybatis/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        focList.add(new FileOutConfig("/generator/java/controller/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String expand = projectPath + File.separator + "expand" + File.separator + pc.getModuleName();
                String entityFile = String.format((expand + File.separator + "%s" + ".java"), tableInfo.getControllerName());
                return entityFile;
            }
        });
//
        focList.add(new FileOutConfig("/generator/template/test-list.html.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityFile = String.format((expand + File.separator + "%s" + ".html"), tableNameNew + "-list");
                return entityFile;
            }
        });
        focList.add(new FileOutConfig("/generator/template/add.html.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityFile = String.format((expand + File.separator + "%s" + ".html"), "add");
                return entityFile;
            }
        });
        focList.add(new FileOutConfig("/generator/template/edit.html.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String entityFile = String.format((expand + File.separator + "%s" + ".html"), "edit");
                return entityFile;
            }
        });


        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));


        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }



}
