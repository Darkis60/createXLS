package com.createXLS.structure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.createXLS.structure.service.IMainService;

@RestController
public class MainController {
	@Autowired
	IMainService mainService;
	
	@GetMapping("/create")
	public boolean create() {
		return mainService.findStructure();
	}
}
