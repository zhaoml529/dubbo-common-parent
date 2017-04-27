package com.zml.base.util;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.zml.base.entity.ColumnEntity;
import com.zml.base.entity.TableEntity;
import com.zml.base.exceptions.GeneratorServiceException;
import com.zml.common.utils.DateUtils;

/**
 * 代码生成器   工具类
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月19日 下午11:40:24
 */
public class GenUtils {

	public static List<String> getTemplates(){
		List<String> templates = new ArrayList<String>();
		templates.add("template/Entity.java.vm");
		templates.add("template/Dao.java.vm");
		templates.add("template/Dao.xml.vm");
		templates.add("template/DaoImpl.java.vm");
		templates.add("template/Service.java.vm");
		templates.add("template/ServiceImpl.java.vm");
		templates.add("template/Controller.java.vm");
		templates.add("template/Exception.java.vm");
		templates.add("template/list.html.vm");
		templates.add("template/list.js.vm");
		templates.add("template/menu.sql.vm");
		return templates;
	}
	
	/**
	 * 生成代码
	 */
	public static void generatorCode(TableEntity tableEntity,
			List<ColumnEntity> columns, ZipOutputStream zip){
		//配置信息
		Configuration config = getConfig();
		
		//表名转换成Java类名
		String className = tableToJava(tableEntity.getTableName(), config.getString("tablePrefix"));
		tableEntity.setClassName(className);
		//将第一个字符改小写
		tableEntity.setClassname(StringUtils.uncapitalize(className));
		
		//列信息
		List<ColumnEntity> columsList = new ArrayList<>();
		for(ColumnEntity column : columns){
			
			//列名转换成Java属性名
			String attrName = columnToJava(column.getColumnName().trim());
			column.setAttrName(attrName);
			//将第一个字符改小写
			column.setAttrname(StringUtils.uncapitalize(attrName));	
			
			//列的数据类型，转换成Java类型
			String attrType = config.getString(column.getDataType(), "unknowType");
			column.setAttrType(attrType);
			
			//是否主键
			if("PRI".equalsIgnoreCase(column.getColumnKey()) && tableEntity.getPk() == null){
				tableEntity.setPk(column);
			}
			
			columsList.add(column);
		}
		tableEntity.setColumns(columsList);
		
		//没主键，则第一个字段为主键
		if(tableEntity.getPk() == null){
			tableEntity.setPk(tableEntity.getColumns().get(0));
		}
		
		//设置velocity资源加载器
		Properties prop = new Properties();  
		prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");  
		Velocity.init(prop);
		
		//封装模板数据
		Map<String, Object> map = new HashMap<>();
		map.put("tableName", tableEntity.getTableName());
		map.put("comments", tableEntity.getComments());
		map.put("pk", tableEntity.getPk());
		map.put("className", tableEntity.getClassName());
		map.put("classname", tableEntity.getClassname());
		map.put("pathName", tableEntity.getClassname().toLowerCase());
		map.put("columns", tableEntity.getColumns());
		map.put("package", config.getString("package"));
		map.put("author", config.getString("author"));
		map.put("email", config.getString("email"));
		map.put("datetime", DateUtils.getTimeStampStr(new Date()));
        VelocityContext context = new VelocityContext(map);
        
        //获取模板列表
		List<String> templates = getTemplates();
		for(String template : templates){
			//渲染模板
			StringWriter sw = new StringWriter();
			Template tpl = Velocity.getTemplate(template, "UTF-8");
			tpl.merge(context, sw);
			
			try {
				//添加到zip
				zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), config.getString("package"))));  
				IOUtils.write(sw.toString(), zip, "UTF-8");
				IOUtils.closeQuietly(sw);
				zip.closeEntry();
			} catch (IOException e) {
				throw GeneratorServiceException.create("渲染模板失败，表名：" + tableEntity.getTableName(), GeneratorServiceException.XUAN_RAN_ERR);
			}
		}
	}
	
	
	/**
	 * 列名转换成Java属性名
	 * 1.带下划线的列名	user_name -> UserName
	 * 2.不带下划线驼峰格式的列名   userName -> UserName
	 */
	public static String columnToJava(String columnName) {
		if(columnName.indexOf("_") != -1) {
			return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
		} else {
			return WordUtils.capitalize(columnName);
		}
	}
	
	/**
	 * 表名转换成Java类名
	 */
	public static String tableToJava(String tableName, String tablePrefix) {
		if(StringUtils.isNotBlank(tablePrefix)){
			tableName = tableName.replace(tablePrefix, "");
		}
		return columnToJava(tableName.trim().toLowerCase());
	}
	
	/**
	 * 获取配置信息
	 */
	public static Configuration getConfig(){
		try {
			return new PropertiesConfiguration("generator.properties");
		} catch (ConfigurationException e) {
			throw GeneratorServiceException.GET_CONFIG_ERR;
		}
	}
	
	/**
	 * 获取文件名
	 */
	public static String getFileName(String template, String className, String packageName){
		StringBuffer packagePath = new StringBuffer();
		StringBuffer angularPath = new StringBuffer();
		packagePath.append("main").append(File.separator).append("java").append(File.separator);
		angularPath.append("main").append(File.separator).append("webapp").append(File.separator);
		if(StringUtils.isNotBlank(packageName)){
			//packagePath += packageName.replace(".", File.separator) + File.separator;
			packagePath.append(packageName.replace(".", File.separator)).append(File.separator);
		}
		
		if(template.contains("Entity.java.vm")){
			return packagePath.append("entity") .append(File.separator).append(className).append(".java").toString();
		}
		
		if(template.contains("Dao.java.vm")){
			return packagePath.append("dao").append(File.separator).append("I").append(className).append("Dao.java").toString();
		}
		
		if(template.contains("DaoImpl.java.vm")){
			return packagePath.append("dao") .append(File.separator).append("impl").append(File.separator).append(className).append("DaoImpl.java").toString();
		}
		
		if(template.contains("Dao.xml.vm")){
			return packagePath.append("dao").append(File.separator).append(className).append("Mapper.xml").toString();
		}
		
		if(template.contains("Service.java.vm")){
			return packagePath.append("service").append(File.separator).append("I").append(className).append("Service.java").toString();
		}
		
		if(template.contains("ServiceImpl.java.vm")){
			return packagePath.append("service") .append(File.separator).append("impl").append(File.separator).append(className).append("ServiceImpl.java").toString();
		}
		
		if(template.contains("Controller.java.vm")){
			return packagePath.append("controller").append(File.separator).append(className).append("Controller.java").toString();
		}
		
		if(template.contains("Exception.java.vm")){
			return packagePath.append("exceptions").append(File.separator).append(className).append("ServiceException.java").toString();
		}
		
		if(template.contains("list.html.vm")){
			return angularPath.append("tpls").append(File.separator).append(className.toLowerCase()).append(File.separator).append(className.toLowerCase()).append(".html").toString();
		}
		
		if(template.contains("list.js.vm")){
			return angularPath.append("js").append(File.separator).append(className.toLowerCase()).append(File.separator).append(className.toLowerCase()).append(".js").toString();
		}

		if(template.contains("menu.sql.vm")){
			return className.toLowerCase() + "_menu.sql";
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(GenUtils.columnToJava("user_name_Phone"));
		System.out.println(GenUtils.columnToJava("userNamePhone"));
		System.out.println(GenUtils.columnToJava("goods"));
		
		System.out.println(WordUtils.capitalize("userName"));
		System.out.println(WordUtils.capitalizeFully("UserName"));
	}
}
