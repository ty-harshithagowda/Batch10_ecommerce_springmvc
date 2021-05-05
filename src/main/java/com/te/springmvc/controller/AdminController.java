package com.te.springmvc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import com.te.springmvc.beans.AdminBean;
import com.te.springmvc.services.AdminService;

public class AdminController<AdminInfoBean> {

	@Autowired
	private AdminService service;

	@GetMapping(path = "/getEmp", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public AdminResponse getItem(int id, AdminInfoBean AdminBean) {

		AdminResponse response = new AdminResponse();
		AdminBean infoBean = (AdminBean) service.getAllItems1();

		if (infoBean != null) {
			response.setStatusCode(200);
			response.setMsg("Success");
//			response.setBean(AdminBean);
		} else {
			response.setStatusCode(404);
			response.setMsg("Failure , Data Not found");
		}

		return response; 
}
}