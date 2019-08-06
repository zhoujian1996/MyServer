package com.monkey1024.xmlparse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.monkey1024.bean.ServletEntity;
import com.monkey1024.bean.ServletMappingEntity;

public class Name2Pattern {
	
	private List<ServletEntity> servletEntitys;
	private List<ServletMappingEntity> servletMappingEntities;
	private Map<String,String> pattern2Name;
	private Map<String,String> name2Clz;
	
	//���캯������� ���ݸ�ֵ���ʼ��
	public Name2Pattern(List<ServletEntity> servletEntitys, List<ServletMappingEntity> servletMappingEntities) {
		super();
		this.servletEntitys = servletEntitys;
		this.servletMappingEntities = servletMappingEntities;
		this.pattern2Name = new HashMap<String, String>();
		this.name2Clz = new HashMap<String, String>();
		for(ServletMappingEntity servletMappingEntity:servletMappingEntities) {
			for(String pattern:servletMappingEntity.getUrlPatterns()) {
			pattern2Name.put(pattern,servletMappingEntity.getUrlName());
			}
		}
		
		for(ServletEntity servletEntity:servletEntitys) {
			name2Clz.put(servletEntity.getServletName(), servletEntity.getServletClz());
		
	}
	}
	
	public String getClz(String url) {
		String urlName = pattern2Name.get(url);
		String clz = name2Clz.get(urlName);
		if(clz==null) {
			return "com.monkey1024.servlet.Error"; // ��web.xml��û�в�ѯ��ҳ���򷵻ش���ҳ��
		}else {
			return clz;
		}
	}
	
	
}
