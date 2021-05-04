package com.te.springmvc.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.te.springmvc.beans.AdminBean;
import com.te.springmvc.beans.ItemBean;
import com.te.springmvc.services.AdminService;

@Controller
public class AdminController {

	@Autowired(required = true)
	private AdminService service;

	@GetMapping("/adminLogin")
	public String getLoginPage() {
		return "adminLogin";

	}

	@PostMapping("/adminLogin")
	public String authenticate(Integer aid, String password, ModelMap map, HttpServletRequest request) {
		AdminBean bean = service.authenticate(aid, password);
		if (bean != null) {
			System.out.println(" success login");
			HttpSession session = request.getSession(true);
			session.setAttribute("loggedInfo", bean);
//			session.setMaxInactiveInterval(60);
			return "home";
		} else {
			System.out.println(" login failure");
			map.addAttribute("msg", " User Details Not Found");
			return "adminLogin";
		}
	}

	@GetMapping("/logout")
	public String handleLogout(HttpSession httpSession, ModelMap map) {
		httpSession.invalidate();
		map.addAttribute("msg", "logged out Successfully");
		return "adminLogin";
	}//

	@GetMapping("/searchPage")
	public String getSearchPage(HttpSession session, ModelMap map) {

		if (session.getAttribute("loggedInfo") != null) {
			return "searchPage";
		} else {
			map.addAttribute("msg", " Please Login First ");
			return "adminLogin";
		}

	}

	@GetMapping("/search")
	public String getEmployeeData(Integer aid, ModelMap map,
			@SessionAttribute(name = "loggedInfo", required = false) AdminBean infoBean) {
		if (infoBean != null) {
			ItemBean Bean = service.getEmpData(aid);

			if (Bean != null) {
				map.addAttribute("data", Bean);
			} else {
				map.addAttribute("msg", "Details Not Found");
			}

			return "searchPage";
		} else {
			map.addAttribute("msg", " Please Login First ");
			return "adminLogin";
		}

	}

	@GetMapping("/add")
	public String getAddPage(@SessionAttribute(name = "loggedInfo", required = false) AdminBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			return "addItem";
		} else {
			map.addAttribute("msg", " Please Login First ");
			return "adminLogin";
		}
	}// end of getaddpage

	@PostMapping("/add")
	public String addEmployeeDetails(@SessionAttribute(name = "loggedInfo", required = false) AdminBean infoBean,
			ItemBean Info, ModelMap map) {
		if (infoBean != null) {
			if (service.addItem(Info)) {
				map.addAttribute("msg", "Item Details Added");
			} else {
				map.addAttribute("errMsg", "Details already exists");
			}
			return "addItem";
		} else {
			map.addAttribute("msg", " Please Login First ");
			return "adminLogin";
		}

	}//

	@GetMapping("/update")
	public String getUpdateFrom(@SessionAttribute(name = "loggedInfo", required = false) AdminBean infoBean,
			ModelMap map) {
		if (infoBean != null) {

			return "updatePage";
		} else {
			map.addAttribute("msg", " Please Login First ");
			return "adminLogin";
		}
	}//

	@PostMapping("/update")
	public String getUpdateData(ItemBean InfoBean,
			@SessionAttribute(name = "loggedInfo", required = false) AdminBean infoBean, ModelMap map, ItemBean bean) {
		if (infoBean != null) {
			if (service.updateItem(bean)) {
				map.addAttribute("msg", "Details Updates");
			} else {
				map.addAttribute("msg", "Details Are not Updates");
			}
			return "updatePage";
		} else {
			map.addAttribute("msg", " Please Login First ");
			return "adminLogin";
		}
	}

	@GetMapping("/delete")
	public String getDeleteForm(@SessionAttribute(name = "loggedInfo", required = false) AdminBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			return "deletePage";
		} else {
			map.addAttribute("msg", " Please Login First ");
			return "adminLogin";
		}
	}

	@GetMapping("/deleteEmp")
	public String deletEmpData(Integer aid, @SessionAttribute(name = "loggedInfo", required = false) AdminBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			if (service.deleteItem(aid)) {
				map.addAttribute("msg", "Data Deleted Successfully");
			}

			return "deletePage";
		} else {
			map.addAttribute("msg", " Please Login First ");
			return "adminLogin";
		}

	}

	@GetMapping("/getAll")
	public String getAllData(@SessionAttribute(name = "loggedInfo", required = false) AdminBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			List<ItemBean> beans = service.getAllEmployeeDetails();
			if (beans != null) {
				map.addAttribute("infobeans", beans);
			} else {
				map.addAttribute("msg", "No records Found");
			}
			return "searchPage";
		} else {
			map.addAttribute("msg", " Please Login First ");
			return "adminLogin";
		}
	}
}
