package com.monkey1024.bean;

import java.util.Set;

/**
 * ServletMapping”≥…‰¿‡
 * @author Administrator
 *
 */
public class ServletMappingEntity {
	
	private String urlName;
	private Set<String> urlPatterns;
	public ServletMappingEntity(String urlName, Set<String> urlPatterns) {
		super();
		this.urlName = urlName;
		this.urlPatterns = urlPatterns;
	}
	public ServletMappingEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ServletMappingEntity [urlName=" + urlName + ", urlPatterns=" + urlPatterns + "]";
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public Set<String> getUrlPatterns() {
		return urlPatterns;
	}
	public void setUrlPatterns(Set<String> urlPatterns) {
		this.urlPatterns = urlPatterns;
	}
	
	
	
	
}
