package com.monkey1024.servlet;

import com.monkey1024.request.Request;
import com.monkey1024.response.Response;

public class RegisterServlet implements Servlet {

	@Override
	public void service(Request request, Response response) {
		 response.println("<html><head><title>HTTP响应示例</title>");
         response.println("</head>欢迎使用注册<body>Hello server!</body></html>");
         response.pushToBrowser(200);
		
	}

	

}
