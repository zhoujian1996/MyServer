package com.monkey1024.servlet;
/**
 * 服务器小脚本接口  后面JavaWeb 要接触到
 */
import com.monkey1024.request.Request;
import com.monkey1024.response.Response;

public interface Servlet {
	void service(Request request,Response response);
	/**
	 * 在JavaWeb中会接触到
	 * void doGet(Request request,Response response) 
	 * void doPost(Request request,Response response)
	 */
}
