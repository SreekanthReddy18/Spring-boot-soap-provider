package com.dugu.spring.boot.soap.api.service;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import com.management.tms.service.ETmsService;
import com.management.tms.service.TMSException;
import com.management.tms.service.types.RegistrationRequest;
import com.management.tms.service.types.RegistrationResponse;

@Service
public class ETMSServiceImpl implements ETmsService {

	@Override
	public RegistrationResponse register(RegistrationRequest inputRequest)
			throws TMSException {
		RegistrationResponse response = new RegistrationResponse();
		response.setName(inputRequest.getName());
		response.setRegistrationId("" + new Random().nextInt(362377));
		response.setIssueDate(convertDate(new Date()));
		return response;
	}

	private XMLGregorianCalendar convertDate(Date date) {
		DatatypeFactory df = null;
		GregorianCalendar gc = null;
		try {
			df = DatatypeFactory.newInstance();
			gc = new GregorianCalendar();
			gc.setTimeInMillis(date.getTime());
		} catch (DatatypeConfigurationException e) {
			System.out.println("Date can't be convertable");
		}
		return df.newXMLGregorianCalendar(gc);
	}
}
