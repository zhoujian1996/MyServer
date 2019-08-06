package com.monkey1024.bean;
/**
 * 
 * @author Administrator
 *	web°£xml÷– servlet”≥…‰
 */
public class ServletEntity {
	
	private String servletName;
	private String servletClz;
	
	
	public ServletEntity(String servletName, String servletClz) {
		super();
		this.servletName = servletName;
		this.servletClz = servletClz;
	}


	public ServletEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getServletName() {
		return servletName;
	}


	public void setServletName(String servletName) {
		this.servletName = servletName;
	}


	public String getServletClz() {
		return servletClz;
	}


	public void setServletClz(String servletClz) {
		this.servletClz = servletClz;
	}


	@Override
	public String toString() {
		return "ServletEntity [servletName=" + servletName + ", servletClz=" + servletClz + "]";
	}
	
	
	
	
	

}
