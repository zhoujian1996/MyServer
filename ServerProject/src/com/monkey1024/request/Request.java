package com.monkey1024.request;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ��װ�������������Ϣ������Ϣ���д���
 * @author Administrato��r
 * ע�⣺ HTTP������ʽ ��Ҫ�Ƕ��ַ����Ĵ���
 *
 *get����:
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
	private String url; //ע�������url��
	private String requestParams;//�������
	private Socket client;  //һ���ͻ��˵�����һ�η���
	private InputStream is;
	private String requestMessage; //��������ȡ��������Ϣ
	private Map<String,List<String>> key2value;  //�����������
	
	
	
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



	//��request��Ϣ���н���
	public void parseRequestMessage() {
		String requestSouce ="";//�ֲ����� ������Դ
		/**
         * =====================================
         * ����Ϣ�����зֽ�� :����ʽ    ����·��   
         �������(get���ܴ���)
         *   ��:GET /index.html?name=123&pwd=5456 HTTP/1.1
         * 
         * ���Ϊpost��ʽ��������������� ���������
         * 
         * ˼·:
         * 1)����ʽ :�ҳ���һ��/  ��ȡ����
         * 2)������Դ:�ҳ���һ��/   HTTP/ 
         * =====================================
         */
		this.method = requestMessage.substring(0,requestMessage.indexOf("/")).trim();

		
		//������Դ�ڵ�һ��/  �� HTTP/֮��
		int requestSouceStartIndex = requestMessage.indexOf("/");
		int requestSourceEndIndex = requestMessage.indexOf("HTTP/");
		requestSouce = requestMessage.substring(requestSouceStartIndex,requestSourceEndIndex).trim();
	   
		//������get����post��������û���ʺ�
		int requestParaindex = requestSouce.indexOf("?");
		if(requestParaindex!=-1) {
			String[] urlArray=requestSouce.split("\\?");
			url = urlArray[0];
			this.requestParams = urlArray[1];
		}else {
			url = requestSouce; 
		}

		//����������
		int lastIndex = requestMessage.lastIndexOf("\r\n");
		if(lastIndex!=-1) {
			requestParams = this.requestParams+requestMessage.substring(lastIndex).trim();
		}
	
		//�Ի�ȡ�Ĳ������з�װ����
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
		
		System.out.println("����ʽ------>: "+method+"   ����url---->:"+url+"  �������---->:"+requestParams);
		
		System.out.println("���������װ"+key2value);
		
	}
	
	/**
	 * ����ҳ���name��ȡ��Ӧ�Ķ��ֵ
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
	 * ����ҳ���name��ȡ��Ӧ�Ķ��ֵ
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
