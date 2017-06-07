package com.dugu.spring.boot.soap.api.util;

import org.springframework.stereotype.Component;

import com.dugu.spring.boot.soap.api.dto.Address;
import com.dugu.spring.boot.soap.api.dto.UserInfo;
import com.management.tms.service.types.RegistrationRequest;

@Component
public class TMSUtils {

	public RegistrationRequest convertDTO2XMLData(UserInfo user, Address add) {
		RegistrationRequest request = new RegistrationRequest();
		request.setName(user.getName());
		request.setAge(user.getAge());
		request.setBikeModel(user.getBikeModel());
		request.setBloodGroup(user.getBloodGroup());
		request.setDlNo(user.getDlNo());
		com.management.tms.service.types.Address soapAddress = null;

		soapAddress = new com.management.tms.service.types.Address();
		soapAddress.setStreet(add.getStreet());
		soapAddress.setCity(add.getCity());
		soapAddress.setDist(add.getDist());
		soapAddress.setState(add.getState());
		soapAddress.setPinCode(add.getPinCode());
		request.getAddress().add(soapAddress);

		return request;
	}

	
}
