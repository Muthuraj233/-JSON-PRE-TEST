package com.att.service.rest;

import com.att.service.rest.ConsumerServiceImpl;

public class RestService {

	public static void main(String[] args) {

		ConsumerService consumerServiceImpl;
		if (args.length > 0) {
			consumerServiceImpl = new ConsumerServiceImpl();
			try {
				if (args[0].isEmpty()) {
					args[0] = "http://attservice-dev.corp.att.com/restservices/get/numbers";
				}
				consumerServiceImpl.getNumbers(args[0]);
			} catch (Exception ex) {
				System.out.println("No Endpoint URL Found" + ex);
			}

		}
	}
}
