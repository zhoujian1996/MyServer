package com.monkey1024.servlet;
/**
 * ������С�ű��ӿ�  ����JavaWeb Ҫ�Ӵ���
 */
import com.monkey1024.request.Request;
import com.monkey1024.response.Response;

public interface Servlet {
	void service(Request request,Response response);
	/**
	 * ��JavaWeb�л�Ӵ���
	 * void doGet(Request request,Response response) 
	 * void doPost(Request request,Response response)
	 */
}
