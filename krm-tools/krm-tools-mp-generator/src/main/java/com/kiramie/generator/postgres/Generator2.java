package com.kiramie.generator.postgres;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.PostgreSqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.PostgreSqlKeyWordsHandler;

/**
 * @author yangbin
 * @since 2022/11/23
 **/
public class Generator2 {
    public static void main(String[] args) {
        //String projectPath = System.getProperty("user.dir");
        // 数据源配置(postgres需要指定schema)
        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig
                //.Builder(
                //"jdbc:postgresql://127.0.0.1:5432/self-test-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai",
                //"binyang",
                //"12345678")
                .Builder(
                "jdbc:postgresql://pgm-wz941jm7u7c27n4k3o.pg.rds.aliyuncs.com:5432/granwin?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Shanghai",
                "granwin",
                "9VAHP%MQGfPOEM$x")
                .dbQuery(new PostgreSqlQuery())
                .typeConvert(new PostgreSqlTypeConvert())
                .keyWordsHandler(new PostgreSqlKeyWordsHandler())
                //.schema("base")
                .schema("device")
                ;

        FastAutoGenerator.create(dataSourceConfig)
                .globalConfig(builder -> {
                    //builder.author("kiramie") //设置作者
                    builder.author("yangbin") //设置作者
                            .commentDate("yyyy-MM-dd")//注释日期
                            .enableSwagger()//开启 swagger 模式
                            .fileOverride()//覆盖已生成文件
                            //.outputDir(projectPath + "/service-user/src/main/java/com/demo/user"); //指定输出目录
                            .outputDir("/Users/binyang/Documents/idea_self03/kiramie-authority/krm-tools/krm-tools-mp-generator/src/main/java/com/kiramie/generatorCode/generator2"); //指定输出目录

                })
                .packageConfig(builder -> {
                    builder.parent(""); // 设置父包名
                })
                .strategyConfig(builder -> {
                    builder
                            //.addInclude("tb_user")//设置需要生成的表名(include 与 exclude 只能配置一项)
                            //.addExclude()//设置需要排除的表名(include 与 exclude 只能配置一项)
                            //.enableSchema()//启用 schema(无效)
                            //.addTablePrefix("t_", "pd_auth", "pd_common", "pd_core") //设置过滤表前缀
                            .addTablePrefix("t_") //设置过滤表前缀

                            .entityBuilder()
                            .enableLombok()//开启 lombok 模型
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解

                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation()
                            .enableBaseResultMap()

                            //.serviceBuilder()
                            //.superServiceImplClass(ServiceImpl.class)
                            //.formatServiceImplFileName("%sServiceImpl")

                            //.controllerBuilder()//controller配置
                            //.enableRestStyle()//开启生成@RestController 控制器
                    ;
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
