package com.monkey1024.xmlparse;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.monkey1024.servlet.Servlet;

public class WebXmlParse {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		 File inputFile = new File("web.xml");
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser = factory.newSAXParser();
         WebHandler webhandler = new WebHandler();
         saxParser.parse(inputFile, webhandler);     
         
         Name2Pattern name2Pattern = new Name2Pattern(webhandler.getServletEntitys(),webhandler.getServletMappingEntitys());
         
         String pattern = name2Pattern.getClz("/g");
        
         //利用反射 实现编译
         Class clz = Class.forName(pattern);
         Servlet servlet = (Servlet)clz.getConstructor().newInstance();
        // servlet.service();
	
	     
	        
	        
	}

}
