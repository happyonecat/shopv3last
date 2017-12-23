package com.yh.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class BeanFactory {
public static Object getBean(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
	//生产对象--根据清单生产--配置文件,将每一个bean对象的生产细节配置到配置文件中
	//使用dom4j的xml解析技术
	//jaxen-1.1-beta-6.jar  dom4j-1.6.1.jar 需要这两个jar包
	//1 创建解析器
	SAXReader reader = new SAXReader();
	//2 解析文档 --bean.xml
	String path = BeanFactory.class.getClassLoader().getResource("bean.xml").getPath();
	try {
		Document doc = reader.read(path);
	//3 获得元素
	Element element = (Element) doc.selectSingleNode("//bean[@id='"+name+"']");
	String className = element.attributeValue("class");
	// 4利用发射创建对象
	Class clazz = Class.forName(className);
	Object object = clazz.newInstance();
	 return object;
	} catch (DocumentException e) {
		e.printStackTrace();
	}
	return null; 
}
}
