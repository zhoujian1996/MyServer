package com.monkey1024.server;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.monkey1024.servlet.Servlet;
import com.monkey1024.xmlparse.Name2Pattern;
import com.monkey1024.xmlparse.WebHandler;

public class WebApp {
	
	private static  Name2Pattern name2Pattern;
	static{
		 File inputFile = new File("web.xml");
         SAXParserFactory factory = SAXParserFactory.newInstance();
         SAXParser saxParser;
         WebHandler webhandler = new WebHandler();
		try {
			saxParser = factory.newSAXParser();
			saxParser.parse(inputFile, webhandler);   
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          name2Pattern = new Name2Pattern(webhandler.getServletEntitys(),webhandler.getServletMappingEntitys());  
	}
	/**
	 * 通过url获取配置文件对应的servlet
	 * @return
	 */
	public static Servlet getServletFromUrl() {
		return null;
	}
	
}
