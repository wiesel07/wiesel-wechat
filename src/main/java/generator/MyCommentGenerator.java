/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2016 Bosssoft Co, Ltd.
 * All rights reserved.
 * 
 * Created on 2016年9月13日
 *******************************************************************************/

package generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * TODO 此处填写 class 信息
 *
 * @author wujian (mailto:wujian@bosssoft.com.cn)
 */
public class MyCommentGenerator extends DefaultCommentGenerator {

	@Override
	public void addFieldComment(Field field,
			IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {

		super.addFieldComment(field, introspectedTable, introspectedColumn);

		// 1.详解方式
		// // 添加字段注释
		// StringBuffer sb = new StringBuffer();
		//
		// field.addJavaDocLine("/**");
		// field.addJavaDocLine(" * <pre>");
		// if (introspectedColumn.getRemarks() != null)
		// field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
		// sb.append(" * 表字段 : ");
		// sb.append(introspectedTable.getFullyQualifiedTable());
		// sb.append('.');
		// sb.append(introspectedColumn.getActualColumnName());
		// field.addJavaDocLine(sb.toString());
		// field.addJavaDocLine(" * </pre>");
		// field.addJavaDocLine(" * ");
		// // addJavadocTag(field, false);
		// field.addJavaDocLine(" */");

		// 2.简洁方式
		if (introspectedColumn.getRemarks() == null
				&& introspectedColumn.getRemarks().equals("")) {
			return;
		}

		field.addJavaDocLine("/**");
		field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
		//addJavadocTag(field, false);
		field.addJavaDocLine(" */");

	}
}

/*
 * 修改历史 $Log$
 */