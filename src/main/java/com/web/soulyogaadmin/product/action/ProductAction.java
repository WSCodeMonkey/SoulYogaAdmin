package com.web.soulyogaadmin.product.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport {
	private HttpServletRequest request =ServletActionContext.getRequest();

	
	
	@SuppressWarnings("unchecked")
	@Action(value = "addProductRedirect", results = {
			@Result(name = "REDIRECT", location = "/product/addProduct.jsp") })
	public String addProductRedirect(){
		
		return "REDIRECT";
	}

	@SuppressWarnings("unchecked")
	@Action(value = "addProduct", results = {
			@Result(name = "SUCCESS", location = "/product/addProduct.jsp") })
	public String addProduct(){
		 String productName=request.getParameter("productname");
		 String productType=request.getParameter("producttype");
		
		
		
		return "SUCCESS";
	
	}





}
