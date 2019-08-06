package com.monkey1024.servlet;

import com.monkey1024.request.Request;
import com.monkey1024.response.Response;

//错误页面
public class Error implements Servlet {

	@Override
	public void service(Request request, Response response) {
		 response.println("<html><head><title>HTTP响应示例</title>");
         response.println("</head><h1>这是一个错误页面</h1><body>Hello server!</body></html>");
         response.pushToBrowser(200);
		
	}

	

}
