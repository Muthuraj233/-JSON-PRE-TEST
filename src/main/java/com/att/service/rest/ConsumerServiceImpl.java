package com.att.service.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConsumerServiceImpl implements ConsumerService {

	public void getNumbers(String endPointURL) throws MalformedURLException, IOException {
		try {
			URL url = new URL(endPointURL);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
			InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
			System.out.println(processSumNumbers(inputStreamReader));
		} catch (Exception e) {
			System.out.println("Bad request" + "400");
			e.printStackTrace();
		}

	}

	private static long processSumNumbers(InputStreamReader inputStreamReader) throws IOException {
		BufferedReader in = new BufferedReader(inputStreamReader);
		String s1 = "";
		String output = "";
		while ((s1 = in.readLine()) != null) {
			output = output + s1;
		}
		System.out.println(output);
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(output);

		long totalCount = 0;

		if (jsonTree.isJsonArray()) {

			JsonArray jsonArray = jsonTree.getAsJsonArray();
			for (JsonElement jsonElement : jsonArray) {
				JsonObject jsonObject = jsonElement.getAsJsonObject();
				Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
				long totalValue = 0;
				for (Map.Entry<String, JsonElement> entry : entries) {
					if (entry.getKey().equals("numbers")) {
						JsonArray jarray = entry.getValue().getAsJsonArray();
						for (JsonElement element : jarray) {
							totalValue += element.getAsInt();
						}
						totalCount += totalValue;
					}
				}

			}
		}
		return totalCount;
	}
}
