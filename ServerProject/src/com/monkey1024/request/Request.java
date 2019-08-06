package com.monkey1024.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装浏览器的请求信息并对信息进行处理
 * @author Administrato奥r
 * 注意： HTTP的请求方式 主要是对字符串的处理
 *
 *get方法:
	GET /aaa?name=zhoujian HTTP/1.1
	Host: localhost:8888
	Connection: keep-alive
	User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36
	Accept: *
	Accept-Encoding: gzip, deflate, br
	Accept-Language: zh-CN,zh;q=0.9
 *
 *
 */
public class Request {
	
	private String method;
	private String url; //注意这里的url与
	private String requestParams;//请求参数
	private Socket client;  //一个客户端的请求，一次访问
	private InputStream is;
	private String requestMessage; //服务器获取额请求信息
	private Map<String,List<String>> key2value;  //根据请求参数
	
	
	
	public Request(Socket client) {
		super();
		this.client = client;
		int len = -1;
		byte[] datas = new byte[1024*1024];
		this.key2value = new HashMap<String, List<String>>();
		this.requestParams="";
		try {
			this.is = this.client.getInputStream();
			len = is.read(datas);
			this.requestMessage = new String(datas,0,len); 
			System.out.println(new String(datas,0,len));
			parseRequestMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}



	//对request信息进行解析
	public void parseRequestMessage() {
		String requestSouce ="";//局部变量 请求资源
		/**
         * =====================================
         * 从信息的首行分解出 :请求方式    请求路径   
         请求参数(get可能存在)
         *   如:GET /index.html?name=123&pwd=5456 HTTP/1.1
         * 
         * 如果为post方式，请求参数可能在 最后正文中
         * 
         * 思路:
         * 1)请求方式 :找出第一个/  截取即可
         * 2)请求资源:找出第一个/   HTTP/ 
         * =====================================
         */
		this.method = requestMessage.substring(0,requestMessage.indexOf("/")).trim();

		
		//请求资源在第一个/  与 HTTP/之间
		int requestSouceStartIndex = requestMessage.indexOf("/");
		int requestSourceEndIndex = requestMessage.indexOf("HTTP/");
		requestSouce = requestMessage.substring(requestSouceStartIndex,requestSourceEndIndex).trim();
	   
		//分析是get还是post，分析有没有问好
		int requestParaindex = requestSouce.indexOf("?");
		if(requestParaindex!=-1) {
			String[] urlArray=requestSouce.split("\\?");
			url = urlArray[0];
			this.requestParams = urlArray[1];
		}else {
			url = requestSouce; 
		}

		//如果请求参数
		int lastIndex = requestMessage.lastIndexOf("\r\n");
		if(lastIndex!=-1) {
			requestParams = this.requestParams+requestMessage.substring(lastIndex).trim();
		}
	
		//对获取的参数进行封装解析
		for(String entity:requestParams.split("&")) {
			String[]  splitValue = entity.split("=");
			if(key2value.containsKey(splitValue[0])) {
				key2value.get(splitValue[0]).add(splitValue[1]);
			}else {
				if(splitValue.length==2) {
				List<String> list = new ArrayList<>();
				list.add(splitValue[1]);
				key2value.put(splitValue[0], list);}
			}
		}
		
		System.out.println("请求方式------>: "+method+"   请求url---->:"+url+"  请求参数---->:"+requestParams);
		
		System.out.println("请求参数封装"+key2value);
		
	}
	
	/**
	 * 根据页面的name获取对应的多个值
	 * @return
	 */
	public String[] getParameterValues(String name) {
		List<String> values = null;
		if((values=key2value.get(name))==null) {
			return null;
		}else {
			return values.toArray(new String[0]);
		}
	}
	
	
	/**
	 * 根据页面的name获取对应的多个值
	 * @return
	 */
	public String getParameterValue(String name) {
		List<String> values = null;
		if((values=key2value.get(name))==null) {
			return null;
		}else {
			return values.toArray(new String[0])[0];
		}
	}



	public String getMethod() {
		return method;
	}



	public void setMethod(String method) {
		this.method = method;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	

}
