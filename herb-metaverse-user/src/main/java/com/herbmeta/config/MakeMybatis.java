package com.herbmeta.config;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.Data;

import java.util.Collections;

public class MakeMybatis {
    public static void main(String[] args) {

        Entity.Builder builder1 = new StrategyConfig.Builder()
                .entityBuilder()
                .disableSerialVersionUID()
                .enableChainModel()
                .enableLombok();


        FastAutoGenerator.create("jdbc:mysql://106.54.120.90:23306/herb_meta?autoReconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false",
                "herb-meta","Asdf#123")
                .globalConfig(builder -> {
                    builder.author("herb")
                            .enableSwagger()
                            .fileOverride()
                            .outputDir(System.getProperty("user.dir")+"/herb-metaverse-user/src/main/java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.herbmeta")
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("dto")
                            .controller("controller")
                            .other("other")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,System.getProperty("user.dir")+"/herb-metaverse-user/src/main/resources/mapper"));

                })
                .strategyConfig(builder -> {
                    builder.addInclude("")
                            .addTablePrefix("t_","c_");
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}

