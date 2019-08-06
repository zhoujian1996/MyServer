package com.monkey1024.response;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Date;

/** 
 * ��װResponse
 * @author Administrator
 *
 */
public class Response {
	
	//����ƴ�ӷ�����Ϣ��ʽ�õ��ĳ���
	 public static final String CRLF="\r\n";
     public static final String BLANK=" ";
     private Socket client;
     private BufferedWriter bfw;
     private StringBuilder headInfo; //���ص����ݱ���ͷ��ʽ
     private StringBuilder contentInfo;//���ص����ݱ�������
     private int size; //�ֽڳ���
     
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
		this.size = 0;// �������ݵ��ֽڳ���
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
      * ��������
      */
     public Response print(String info){
            contentInfo.append(info);
            size+=info.getBytes().length;
            return this;
     }
     
     /**
      * ��������+�س�
      */
     public Response println(String info){
            contentInfo.append(info).append(CRLF);
            size+=(info+CRLF).getBytes().length;
            return this;
     }
     
     
     
     //���������͵������
     public void pushToBrowser(int code) {
    	 try {
			bfw.write(createHeadInfo(code)); //����ͷ��
			bfw.write(contentInfo.toString());
			bfw.flush();  //����Ҫˢ��һ��
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
	
	
	
}
