package com.te.springmvc.services;
import java.util.List;

import com.te.springmvc.beans.AdminBean;
import com.te.springmvc.beans.ItemBean;

public interface AdminService {
	
	public AdminBean authenticate(Integer aid, String password);
	
	public ItemBean getEmpData(Integer aid);

	public boolean addItem(ItemBean Bean);
	
	public boolean updateItem(ItemBean infoBean);
	
	public boolean deleteItem(Integer id);
	
	public List<ItemBean> getAllEmployeeDetails();

	public AdminBean getAllItems1();

	public AdminBean getAllItems();

}
