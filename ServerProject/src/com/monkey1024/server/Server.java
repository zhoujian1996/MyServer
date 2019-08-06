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
 *	服务器端
 */
public class Server {
	
	private ServerSocket server;
	private Socket client;
	
	public static void main(String[] args) {
		new Server().start();
	}
	
	//开启服务器
	public void start() {
		 try {
			this.server = new ServerSocket(8888);
			service();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//服务器开始服务
	public void service() {
		
		try {
			while(true) {
				this.client = server.accept();
			    System.out.println("一个客户端连接上了");
			    new Thread(new Dispatcher(client)).start();//开启一个线程
			}
            
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//结束服务器
	public void stop() {
		
	}
	
	
	
	
}
