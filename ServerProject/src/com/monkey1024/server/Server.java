package com.monkey1024.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.monkey1024.dispatcher.Dispatcher;
import com.monkey1024.request.Request;
import com.monkey1024.response.Response;

/**
 * 
 * @author Administrator
 *	��������
 */
public class Server {
	
	private ServerSocket server;
	private Socket client;
	
	public static void main(String[] args) {
		new Server().start();
	}
	
	//����������
	public void start() {
		 try {
			this.server = new ServerSocket(8888);
			service();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//��������ʼ����
	public void service() {
		
		try {
			while(true) {
				this.client = server.accept();
			    System.out.println("һ���ͻ�����������");
			    new Thread(new Dispatcher(client)).start();//����һ���߳�
			}
            
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//����������
	public void stop() {
		
	}
	
	
	
	
}
