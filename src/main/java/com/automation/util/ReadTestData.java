package com.automation.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadTestData {

	
	public static JSONObject getJsonData() throws IOException, ParseException {
		File filename = new File("Resources//TestData//testdata.json");
		
		String json = FileUtils.readFileToString(filename, "UTF-8");
		Object obj = new JSONParser().parse(json);
		JSONObject jsonObject = (JSONObject) obj;
		return jsonObject;
	}
	
	
	public static String getTestData(String keyname) throws IOException, ParseException
	{
		String testdata;
		return testdata= (String) getJsonData().get(keyname);
	}
	
}
