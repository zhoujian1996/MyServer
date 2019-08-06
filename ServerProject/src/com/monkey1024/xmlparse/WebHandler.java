package com.monkey1024.xmlparse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* web.xmlÎÄµµ
 * ½âÎö web
 */
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.monkey1024.bean.ServletEntity;
import com.monkey1024.bean.ServletMappingEntity;

public class WebHandler extends DefaultHandler {
	
	
	private String tag;
	private boolean isServletEntity;
	private ServletEntity servletEntity;
	private ServletMappingEntity servletMappingEntity;
	private List<ServletEntity> servletEntitys;
	private List<ServletMappingEntity> servletMappingEntitys;
	private Set<String> urlPatterns;
	
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String content = new String(ch,start,length).trim();
		if(!content.equals("")) {
			if(isServletEntity) {
				if(tag.equals("servlet-name")) {
					servletEntity.setServletName(content);
				}else if(tag.equals("servlet-class")) {
					servletEntity.setServletClz(content);
				}
			}else {
				if(tag.equals("servlet-name")) {
					servletMappingEntity.setUrlName(content);
					
				}else if(tag.equals("url-pattern")) {
					urlPatterns.add(content);
				}
				
			}
		}
	}

	

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("servlet")) {
			servletEntitys.add(servletEntity);
		}else if(qName.equals("servlet-mapping")) {
			servletMappingEntity.setUrlPatterns(urlPatterns);
			servletMappingEntitys.add(servletMappingEntity);
		}
	}

	@Override
	public void startDocument() throws SAXException {
		servletMappingEntitys = new ArrayList<ServletMappingEntity>();
		servletEntitys = new ArrayList<ServletEntity>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		tag = qName;
		if(tag.equals("servlet")) {	
			servletEntity = new ServletEntity();
			isServletEntity = true;
		}else if(tag.equals("servlet-mapping")) {
			servletMappingEntity = new ServletMappingEntity();
			urlPatterns = new HashSet<>();
			isServletEntity = false;
		}
	}



	public List<ServletEntity> getServletEntitys() {
		return servletEntitys;
	}



	public void setServletEntitys(List<ServletEntity> servletEntitys) {
		this.servletEntitys = servletEntitys;
	}



	public List<ServletMappingEntity> getServletMappingEntitys() {
		return servletMappingEntitys;
	}



	public void setServletMappingEntitys(List<ServletMappingEntity> servletMappingEntitys) {
		this.servletMappingEntitys = servletMappingEntitys;
	}
	
	
	
	
	
	

}
