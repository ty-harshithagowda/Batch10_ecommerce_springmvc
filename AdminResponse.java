package com.te.springmvc.controller;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonRootName;

import lombok.Data;



@Data
@JsonRootName("response")
@JsonPropertyOrder({"status" , "msg"})
@XmlRootElement(name = "response")
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class  AdminResponse<AdminInfoBean> implements Serializable{

	@JsonProperty("status")
	@XmlElement(name = "status-code")
	private int statusCode;
	
	
	private String  msg;
	
	@JsonProperty("Admin-info")
	@XmlElement(name = "Admin-info")
	private AdminInfoBean bean;
	
	
	private List<AdminInfoBean> AdminInfo;
}
