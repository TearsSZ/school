package com.dlb;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

//代码生成
//代码生成
public class DlbCode {
    public static void main(String[] args) {

        //数据库配置
        String url = "jdbc:mysql://47.101.165.96:3306/campus?useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        String root = "admin";
        String pwd = "Amanda+3213";
        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(url,root,pwd)
                /*.dbQuery(new MySqlQuery())//数据库查询
                .schema("mybatis-plus")//数据库schema(部分数据库适用)
                .typeConvert(new MySqlTypeConvert())//	数据库类型转换器
                .keyWordsHandler(new MySqlKeyWordsHandler())//数据库关键字处理器*/
                .build();
        //全局设置
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .fileOverride()
                .outputDir(System.getProperty("user.dir") + "/src/main/java")
                .author("大萝卜")
                .fileOverride()//覆盖生成文件，默认为false
                .disableOpenDir()//禁止打开输出目录，默认为TRUE
                //.enableKotlin()//生成kotlin代码
                .enableSwagger()//开启swagger，默认为false
                .dateType(DateType.TIME_PACK)//时间策略
                .commentDate("yyyy-MM-dd").build();
        //包设置
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("com")
                .moduleName("dlb")
                .entity("pojo")
                .service("service")
                .serviceImpl("service.impl")
                .mapper("mapper")
                //.mapperXml("mapper.xml")
                .controller("controller")
                .other("other")
                //.pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D://")
                .build();
        //模板配置
        TemplateConfig templateConfig = new TemplateConfig.Builder()
//                .disable(TemplateType.ENTITY)
//                .entity("/templates/entity.java")
//                .service("/templates/service.java")
//                .serviceImpl("/templates/serviceImpl.java")
//                .mapper("/templates/mapper.java")
//                .mapperXml("/templates/mapper.xml")
//                .controller("/templates/controller.java")
                .build();
        //策略配置
        StrategyConfig pojo = new StrategyConfig.Builder()
                //生成实体模型配置
                .entityBuilder()
                //.superClass(BaseEntity.class)//设置父类
                .disableSerialVersionUID()//禁用生成 serialVersionUID	默认值:true
                //.enableChainModel()//生成的get和set方法是链式调用的
                .enableLombok()//开启 lombok 模型	默认值:false
                .enableRemoveIsPrefix()//开启 Boolean 类型字段移除 is 前缀
                .enableTableFieldAnnotation()//开启生成实体时生成字段注解
                //.enableActiveRecord()//开启 ActiveRecord 模型
                .versionColumnName("version")//乐观锁字段名(数据库)
                .versionPropertyName("version")//乐观锁属性名(实体)
                .logicDeleteColumnName("deleted")//逻辑删除字段名(数据库)
                .logicDeletePropertyName("deleteFlag")//	逻辑删除属性名(实体)
                .naming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略	下划线转驼峰命
                .columnNaming(NamingStrategy.underline_to_camel)//数据库表字段映射到实体的命名策略	默认为 null
                .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")//添加父类公共字段
                .addIgnoreColumns("age")//添加忽略字段
                .addTableFills(new Column("create_time", FieldFill.INSERT))//添加表字段填充
                .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))//添加表字段填充
                .idType(IdType.AUTO)//全局主键类型
                //.formatFileName("%sEntity")//格式化文件名称
                .build();
        StrategyConfig controller = new StrategyConfig.Builder()
                //controller策略配置
                .controllerBuilder()
                .enableRestStyle()//开启生成@RestController 控制器 默认false
                .build();
        StrategyConfig service = new StrategyConfig.Builder()
                //service策略配置
                .serviceBuilder()
//                .superServiceClass(BaseService.class)
//                .superServiceImplClass(BaseServiceImpl.class)
                .formatServiceFileName("%sService")//格式化 service 接口文件名称
                .formatServiceImplFileName("%sServiceImp")//格式化 service 实现类文件名称
                .build();
        StrategyConfig mapper = new StrategyConfig.Builder()
                .mapperBuilder()
                .superClass(BaseMapper.class)
                .enableMapperAnnotation()//开启 @Mapper 注解
                .enableBaseResultMap()//启用 BaseResultMap 生成	默认值:false
                .enableBaseColumnList()//启用 BaseColumnList	默认值:false
                //.cache(MyMapperCache.class)//缓存实现类
                .formatMapperFileName("%sDao")
                .formatXmlFileName("%sMapper")
                .build();
        new AutoGenerator(dataSourceConfig)//数据源
                .global(globalConfig)//全局
                .packageInfo(packageConfig)//包
                .template(templateConfig)//模板配置
                //策略配置
                .strategy(pojo)//实体类
                /*.strategy(controller)//控制器
                .strategy(service)//service
                .strategy(mapper)//mapper*/
                .execute();

//        FastAutoGenerator.create("jdbc:p6spy:mysql://47.101.165.96:3306/msa?useSSL=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8"
//                , "admin", "Amanda+3213")
//                .globalConfig(builder -> {
//                    builder.fileOverride()
//                            .outputDir(projectPath + "/src/main/java")
//                            .author("大萝卜")
//                            .fileOverride()//覆盖生成文件，默认为false
//                            .disableOpenDir()//禁止打开输出目录，默认为TRUE
//                            .enableKotlin()
//                            .enableSwagger()//开启swagger，默认为false
//                            .dateType(DateType.TIME_PACK)//时间策略
//                            .commentDate("yyyy-MM-dd");//默认格式
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.dlb")
//                            .moduleName("blog")
//                            .entity("pojo")
//                            .service("service")
//                            .serviceImpl("service.impl")
//                            .mapper("mapper")
//                            //.mapperXml("mapper.xml")
//                            .controller("controller")
//                            .other("other");
//                })
//                .strategyConfig(builder -> {
//                    builder.entityBuilder()
//                            //.superClass(BaseEntity.class)//设置父类
//                            .disableSerialVersionUID()//禁用生成 serialVersionUID	默认值:true
//                            .enableChainModel()
//                            .enableLombok()//开启 lombok 模型	默认值:false
//                            .enableRemoveIsPrefix()
//                            .enableTableFieldAnnotation()
//                            .enableActiveRecord()
//                            .versionColumnName("version")//乐观锁字段名(数据库)
//                            .versionPropertyName("version")//乐观锁属性名(实体)
//                            .logicDeleteColumnName("deleted")//逻辑删除字段名(数据库)
//                            .logicDeletePropertyName("deleteFlag")//	逻辑删除属性名(实体)
//                            .naming(NamingStrategy.no_change)//数据库表映射到实体的命名策略	默认下划线转驼峰命
//                            .columnNaming(NamingStrategy.underline_to_camel)//数据库表字段映射到实体的命名策略	默认为 null
//                            .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")//添加父类公共字段
//                            .addIgnoreColumns("age")//添加忽略字段
//                            .addTableFills(new Column("create_time", FieldFill.INSERT))//添加表字段填充
//                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))//添加表字段填充
//                            .idType(IdType.AUTO);//全局主键类型
//                    //.formatFileName("%sEntity")//格式化文件名称
//                })
//                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
    }
}
