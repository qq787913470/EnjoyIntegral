package com.yada.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yada.security.service.UserGrpRoleIdxManager;

@Controller
public class UserGrpRoleIdxController {

	@Autowired
	private UserGrpRoleIdxManager userGrpRoleIdxManager;
	
	@RequestMapping
	public String list(){
		userGrpRoleIdxManager.selectByUserGrpId(2L);
		return "";
	}
}
