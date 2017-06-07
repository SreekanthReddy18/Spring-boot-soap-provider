package com.dugu.spring.boot.soap.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dugu.spring.boot.soap.api.dto.Address;
import com.dugu.spring.boot.soap.api.dto.UserInfo;
import com.dugu.spring.boot.soap.api.service.ETMSServiceImpl;
import com.dugu.spring.boot.soap.api.util.TMSUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.management.tms.service.TMSException;
import com.management.tms.service.types.RegistrationResponse;

@Controller
public class TMSController {
	@Autowired
	private ETMSServiceImpl service;
	@Autowired
	private TMSUtils utils;

	@RequestMapping(value = "/")
	public String home() {
		return "register";
	}

	@RequestMapping(value = "/registration")
	public String doRegistration(@ModelAttribute UserInfo user,
			@ModelAttribute Address address, Model model) throws TMSException,
			JsonProcessingException {
		String message = "Registration success";
		// Wrapping the value from DTO to SOAP XML DATA
		RegistrationResponse registrationResponse = service.register(utils
				.convertDTO2XMLData(user, address));
		if (registrationResponse != null) {
			model.addAttribute("registrationResponse", registrationResponse);
			model.addAttribute("message", message);
		} else {
			model.addAttribute("registrationResponse",
					new RegistrationResponse());
		}
		System.out.println(new ObjectMapper()
				.writeValueAsString(registrationResponse));
		return "register";
	}
}
