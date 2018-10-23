package com.att.service.rest;

import java.io.IOException;
import java.net.MalformedURLException;

public interface ConsumerService {

	public void getNumbers(String endPointURL) throws MalformedURLException, IOException;
}
