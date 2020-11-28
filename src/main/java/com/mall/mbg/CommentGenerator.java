package com.mall.mbg;


import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

/*
 * @Author maiBangMin
 * @Description [自定义注释生成器]
 * @Date 3:43 下午 2020/11/28
 * @Version 1.0
 **/
public class CommentGenerator extends DefaultCommentGenerator {

    private boolean addRemarkComments = false;

    /*
     * @Author maiBangMin
     * @Description 设置用户配置的参数
     * @Date 3:44 下午 2020/11/28
     * @Param
     * @return
     **/
    @Override
    public void addConfigurationProperties(Properties properties){
        super.addConfigurationProperties(properties);
        this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
    }

    /*
     * @Author maiBangMin
     * @Description 给字段添加注释
     * @Date 3:46 下午 2020/11/28
     * @Param
     * @return
     **/
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable,
                                IntrospectedColumn introspectedColumn){
        String remarks = introspectedColumn.getRemarks();
        // 根据参数和备注信息判断是否添加备注信息
        if(addRemarkComments && StringUtility.stringHasValue(remarks)) {
            addFieldJavaDoc(field,remarks);
        }
    }

    /*
     * @Author maiBangMin
     * @Description 给Model的字段添加注释
     * @Date 3:49 下午 2020/11/28
     * @Param
     * @return
     **/
    public void addFieldJavaDoc(Field field, String remarks){
        // 文档注释开始
        field.addJavaDocLine("/**");
        // 获取数据库当前字段的备注信息
        String[] remarkLines = remarks.split(System.getProperty("line.separator"));
        for (String remarkLine:remarkLines) {
            field.addJavaDocLine(" * " + remarkLine);
        }
        addJavadocTag(field,false);
        field.addJavaDocLine(" */");
    }

}
