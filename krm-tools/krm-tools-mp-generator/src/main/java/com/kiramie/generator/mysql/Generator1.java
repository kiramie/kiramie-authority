package com.kiramie.generator.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.MySqlKeyWordsHandler;

/**
 * @author yangbin
 * @since 2022/11/23
 **/
public class Generator1 {
    public static void main(String[] args) {
        //String projectPath = System.getProperty("user.dir");
        // 数据源配置、
        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig
                .Builder(
                "jdbc:mysql://127.0.0.1:3306/recurtion?useUnicode=true&serverTimezone=Asia/Shanghai&useSSL=false&characterEncoding=utf8",
                "root",
                "12345678")
                .dbQuery(new MySqlQuery())
                .typeConvert(new MySqlTypeConvert())
                .keyWordsHandler(new MySqlKeyWordsHandler());

        FastAutoGenerator.create(dataSourceConfig)
                .globalConfig(builder -> {
                    builder.author("kiramie") //设置作者
                            .commentDate("yyyy-MM-dd")//注释日期
                            .enableSwagger()//开启 swagger 模式
                            .fileOverride()//覆盖已生成文件
                            //.outputDir(projectPath + "/service-user/src/main/java/com/demo/user"); //指定输出目录
                            .outputDir("/Users/binyang/Documents/idea_self03/kiramie-authority/krm-tools/krm-tools-mp-generator/src/main/java/com/kiramie/generatorCode/generator1"); //指定输出目录

                })
                .packageConfig(builder -> {
                    builder.parent(""); // 设置父包名
                })
                .strategyConfig(builder -> {
                    builder
                            //.addInclude("tb_user")//设置需要生成的表名(include 与 exclude 只能配置一项)
                            //.addExclude()//设置需要排除的表名(include 与 exclude 只能配置一项)
                            //.enableSchema()//启用 schema
                            .addTablePrefix("pd_auth", "pd_common", "pd_core") //设置过滤表前缀

                            .entityBuilder()
                            .enableLombok()//开启 lombok 模型
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解

                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation()
                            .enableBaseResultMap()

                            .serviceBuilder()
                            .superServiceImplClass(ServiceImpl.class)
                            .formatServiceImplFileName("%sServiceImpl")

                            .controllerBuilder()//controller配置
                            .enableRestStyle()//开启生成@RestController 控制器
                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
