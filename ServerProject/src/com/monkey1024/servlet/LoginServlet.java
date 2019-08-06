package com.monkey1024.servlet;

import com.monkey1024.request.Request;
import com.monkey1024.response.Response;

public class LoginServlet implements Servlet {

	public void service(Request request, Response response) {	 
         response.println("<html><head><title>HTTPÏìÓ¦Ê¾Àý</title>");
         response.println("</head>»¶Ó­µÇÂ¼<body>Hello server!</body></html>");
         response.pushToBrowser(200);
		
	}

	

}
