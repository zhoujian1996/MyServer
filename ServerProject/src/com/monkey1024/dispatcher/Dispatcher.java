package com.monkey1024.dispatcher;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.monkey1024.request.Request;
import com.monkey1024.response.Response;
import com.monkey1024.servlet.Servlet;
import com.monkey1024.xmlparse.Name2Pattern;
import com.monkey1024.xmlparse.WebHandler;

/**
 * 分发器 每有一个客户端请求进来 首先进入到这里，进行处理
 * 多线程的分发器
 * @author Administrator
 *
 */
public class Dispatcher implements Runnable {
	
	private static  Name2Pattern name2Pattern;
	private Socket client;
	private Request request;
	private Response response;
	
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
	
	
	
	public Dispatcher(Socket client) {
		super();
		this.client = client;
		request = new Request(this.client);
		response = new Response(this.client);
	}


	@Override
	public void run() {
		
		
		String url = request.getUrl();
		System.out.println("客户端请求的url地址为:"+url);
		String pattern = name2Pattern.getClz(url);
		System.out.println("客户端请求depattern为"+pattern);
		//根据请求的url地址 映射对应的 servlet类
		try {
			Class clz = Class.forName(pattern);
			Servlet servlet = (Servlet)clz.getConstructor().newInstance();
			//调用分发处理的servlet业务方法
			servlet.service(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//这里就是错误的网页
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
		 try {
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
