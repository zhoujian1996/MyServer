package com.monkey1024.servlet;

import com.monkey1024.request.Request;
import com.monkey1024.response.Response;

//����ҳ��
public class Error implements Servlet {

	@Override
	public void service(Request request, Response response) {
		 response.println("<html><head><title>HTTP��Ӧʾ��</title>");
         response.println("</head><h1>����һ������ҳ��</h1><body>Hello server!</body></html>");
         response.pushToBrowser(200);
		
	}

	

}
