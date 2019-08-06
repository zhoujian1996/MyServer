package com.monkey1024.response;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Date;

/** 
 * 封装Response
 * @author Administrator
 *
 */
public class Response {
	
	//两个拼接返回信息格式用到的常量
	 public static final String CRLF="\r\n";
     public static final String BLANK=" ";
     private Socket client;
     private BufferedWriter bfw;
     private StringBuilder headInfo; //返回的数据报的头格式
     private StringBuilder contentInfo;//返回的数据报的内容
     private int size; //字节长度
     
     public Response(Socket client) {
		super();
		this.client = client;
		try {
			this.bfw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.headInfo=new StringBuilder();
		this.contentInfo = new StringBuilder();
		this.size = 0;// 返还内容的字节长度
	}
     
     public String createHeadInfo(int code) {
    	 headInfo.append("HTTP/1.1").append(BLANK);
    	 headInfo.append(code).append(BLANK);
    	 headInfo.append("OK").append(CRLF);
    	 headInfo.append("Server:bjsxt Server/0.0.1").append(CRLF);
    	 headInfo.append("Date:").append(new java.util.Date()).append(CRLF);
    	 headInfo.append("Content-Type:").append(BLANK);
    	 headInfo.append("text/html;").append(BLANK);
    	 headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
    	 headInfo.append("Content-Length:").append(size).append(CRLF);
    	 headInfo.append(CRLF);
    	 return headInfo.toString();
     }
     
     
     /**
      * 构建正文
      */
     public Response print(String info){
            contentInfo.append(info);
            size+=info.getBytes().length;
            return this;
     }
     
     /**
      * 构建正文+回车
      */
     public Response println(String info){
            contentInfo.append(info).append(CRLF);
            size+=(info+CRLF).getBytes().length;
            return this;
     }
     
     
     
     //将数据推送到浏览器
     public void pushToBrowser(int code) {
    	 try {
			bfw.write(createHeadInfo(code)); //推送头部
			bfw.write(contentInfo.toString());
			bfw.flush();  //必须要刷新一下
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
	
	
	
}
