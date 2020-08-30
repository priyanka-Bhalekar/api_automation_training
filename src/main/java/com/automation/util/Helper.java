package com.automation.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;


public class Helper {
	
	public static final String commonFilePath=System.getProperty("user.dir")+"//Common.properties";
	public static void CreateFolder(String path)
	{
		File file =new File(path);
		if(!file.exists())
		{
			file.mkdir();	
		}
		
		else
		{
			System.out.println("Folder already created");
		}
		
	}
	
	
	public static String TimeStamp()
	{
		Date now =new Date();
		String Timestamp= now.toString().replace(":", "-");
		return Timestamp;
		
	}
	
	public static String propertyReader(String filePath, String key) 
	{
		String value=null;
		try(InputStream input=new FileInputStream(filePath) )
		{
			Properties prop =new Properties();
			prop.load(input);
			value = prop.getProperty(key);
		}
		
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		return value;	
	} 

}
