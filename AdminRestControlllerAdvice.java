package com.te.springmvc.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.te.springmvc.exception.AdminException;

@RestControllerAdvice
public class AdminRestControlllerAdvice {

	@ExceptionHandler(AdminException.class)
	public AdminResponse handleEmployeeExp(AdminException exception) {
		AdminResponse response = new AdminResponse();
		response.setStatusCode(500);
		response.setMsg(exception.getMessage());
		return response;
	}
}