package APITestConcept;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.automation.util.BaseTest;
import com.automation.util.ExtentReport;
import com.automation.util.Helper;
import com.automation.util.ReadTestData;

import io.restassured.response.Response;
import pojo.PojoClass;

import static io.restassured.RestAssured.given;
public class NewTest extends BaseTest{
	
	
	
	//Get methods
  @Test(description="validate 200 status code GET API", groups="smoke_suite")
  public void verifyGetUsersSC() throws IOException, ParseException {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("verifyGetUsersSC","status code should be 200");  
	  Response resp= given()
			  .when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUserURI"));
	  int actualStatusCode= resp.getStatusCode();
	  assertEquals(actualStatusCode,200);
	  System.out.println("Status code of the Get user method is" + actualStatusCode);
	}
  
  
  @Test(description="validate 404 status code for invalid url")
  public void verifyInvalidURL() throws IOException, ParseException {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("verifyInvalidURL","status code should be 404");  
	  Response resp= given()
			  .when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("invalidURI"));
	  int actualStatusCode= resp.getStatusCode();
	  assertEquals(actualStatusCode,404);
	  System.out.println("Status code of the invalid URL is" + actualStatusCode);
  }
  
  
  @Test(description="validate 404 status code for given end point which gives 404")
  public void verifyGivenFailedEndPoint() throws IOException, ParseException {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("verifyGivenFailedEndPoint","status code should be 404");  
	  Response resp= given().
			  when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("failedEndPoint"));
	  int actualStatusCode= resp.getStatusCode();
	  assertEquals(actualStatusCode,404); 
	  System.out.println("Status code for the given 404 endpoint is" + actualStatusCode);
  }
  
  @Test(description="use of asString() method to check body of the request")
  public void displayRequestBody() throws IOException, ParseException {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("displayRequestBody","status code should be 200");   
	  Response resp= given()
			  .when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUserURI"));
	  int actualStatusCode= resp.getStatusCode();
	  assertEquals(actualStatusCode,200);
	  System.out.println("Status code of the URL is" + actualStatusCode);
	  System.out.println(resp.getBody().asString()); 
  }
  
  @Test(description="validate 204 for Delete user")
  public void verifyStatusCodeDelete() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("verifyStatusCodeDelete","status code should be 204");   
	  Response resp = given()
		.delete(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUserURI"));
	  assertEquals(resp.statusCode(),204);
  }
  
  @Test(description="Verify email for user id=2")
  public void verifyEmailUID2() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("verifyEmailUID2","Fetch email of second user");   
	  Response resp =given().when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUserURI"));
	  assertEquals(resp.path(ReadTestData.getTestData("emailPath")),ReadTestData.getTestData("user2Email"));
  }
  
  @Test(description="Verify company for user id=2")
  public void verifyCompanyUID2() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("verifyCompanyUID2","Fetch company name for user id 2");   
	  Response resp =given().when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUserURI"));
	  assertEquals(resp.path(ReadTestData.getTestData("companyPath")),ReadTestData.getTestData("companyName"));
  }
  
  @Test(description="Validate per page value which is an integer value")
  public void verifyPerPageValue() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("verifyPerPageValue","Fetch per page value");   
	  Response resp =given().when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUsersURI"));
	  assertEquals(resp.path("per_page").toString(),"6");
  }
  
  @Test(description="concept of query parameters")
  public void useOfQueryParam() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("useOfQueryParam","concept of query param");   
	  Response resp =given().queryParam("page", "2")
			  .when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUsersURI"));
	  assertEquals(resp.path("per_page").toString(),"6");
  }
  
  @Test(description="concept of path params")
  public void useOfPathParam() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("useOfPathParam","concept of path param");   
	  Response resp =given().pathParam("raceSeason", "2018")
			  .when().get(ReadTestData.getTestData("getPathParamURI"));
	  assertEquals(resp.path("MRData.total"),"21");
	  System.out.println(resp.getBody().asString());
  }
  
  @Test(description="concept of header in given method")
  public void useOfHeaderConcept() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("useOfHeaderConcept","concept of header");   
	  Response resp =given().pathParam("raceSeason", "2018").header("ContentType", "application/json")
			  .when().get(ReadTestData.getTestData("getPathParamURI"));
	  assertEquals(resp.path("MRData.total"),"21");
	  System.out.println(resp.getBody().asString());
  }
  
  // Concept of collection, getList()
  @Test(description ="to get the list of data which is actually map that has key value pairs")
 public void verifyListOfData() throws IOException, ParseException
 {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("verifyListOfData","Fetch list of data");    
	  Response resp =given().when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUsersURI"));
	  List<String>jsonResponse = resp.jsonPath().getList("data") ;
	  System.out.println("**The number of data in the list is:  "+ jsonResponse.size());
	 
 }
 
  //Use of getString method of jsonPath() to get the value of a specific key of a particular data out of the list
  @Test(description ="to get email value of data of second node i.e index 1 or userid=2")
  public void getEmailOfSecondData() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("getEmailOfSecondData","to get the email of user id 2");   
 	  Response resp =given().when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUsersURI"));
 	  String jsonResponse = resp.jsonPath().getString("data[1].email");
 	  System.out.println("**The email of second data is:  "+ jsonResponse);
 	
  }
  

  //Use of getString method of jsonPath() to get the value of a specific key of a particular data out of the list
  @Test(description ="to get firstname value of data of second node i.e index 1 or userid=2")
  public void getFirstNameOfSecondData() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("getFirstNameOfSecondData","Fetch first name value of user id 2");   
 	  Response resp =given().when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUsersURI"));	  
 	  String jsonResponse = resp.jsonPath().getString("data[1].first_name");
 	  System.out.println("**The firstname of second data is:  "+ jsonResponse);
 	
  }
  

  //Use of getString method of jsonPath() to get the value of a specific key of a particular data out of the list
  @Test(description ="to get lastname value of data of second node i.e index 1 or userid=2")
  public void getLastNameOfSecondData() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("getLastNameOfSecondData","Fetch last name of user id 2");   
 	  Response resp =given()
 			  .when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUsersURI")); 	  
 	  String jsonResponse = resp.jsonPath().getString("data[1].last_name");
 	  System.out.println("**The lastname of second data is:  "+ jsonResponse);
 	
  }

  // Use of getlist method of jsonPath() to get the value of a specific key in list 
  @Test(description ="validate if in the list fistname Janet is present or not ")
  public void searchyFirstname() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("searchyFirstname","search firstname janet is present");   
	  Response resp =given().when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUsersURI"));
 	  
 	  List<String> firstnameList = resp.jsonPath().getList("data.first_name");
      for(String s:firstnameList)
      {
    	  if(s.toUpperCase().contentEquals("JANET"))
    	  System.out.println("List contains Janet");
      }
  }
 
  
 //Map concept: To get access to particular hash map in data list
  @Test(description="Access to all keys of user id 2 ")
  public void useOfMap() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("useOfMap","use of map");   
	  Response resp =given().when().get(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUsersURI"));
 	  Map<String,String> userID2Data= resp.jsonPath().getMap("data[1]");
 	  System.out.println("Details of user 2: "+ userID2Data);
 	  System.out.println("***Firstname of user ID 2 using map:  "+userID2Data.get("first_name").toString());
 	  System.out.println("Last Name of user id 2 using map: " +userID2Data.get("last_name"));
  }
  
  //json file concept for post
  @Test (description="Automate post methods of users using json file")
  public void validatePost() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("validatePost","post methods using json");     
	 FileInputStream file = new FileInputStream (new File(System.getProperty("user.dir")+"\\\\Resources\\TestData\\postuser.json"));
	  Response resp= given().body(IOUtils.toString(file,"UTF-8")).
			  when().post(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUsersURI"));
	  assertEquals(resp.getStatusCode(),201);
	  System.out.println("Status code for post method is:  " + resp.getStatusCode());
	  System.out.println("Response body of post method :" + resp.getBody().asString());//always use asString() for getBoday()
  }
  
  //concept of header 
  @Test (description="Automate post methods of users with headers")
  public void validateHeaderPost() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("validateHeaderPost","post methods with headers");   
	 FileInputStream file = new FileInputStream (new File(System.getProperty("user.dir")+"\\\\Resources\\TestData\\postuser.json"));
	  Response resp= given().header(ReadTestData.getTestData("contentType"),ReadTestData.getTestData("contentTypeValue")).body(IOUtils.toString(file,"UTF-8")).
			  when().post(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUsersURI"));
	  assertEquals(resp.getStatusCode(),201);
	  System.out.println("Status code for post method is:  " + resp.getStatusCode());
	  System.out.println("Response body of post method :" + resp.getBody().asString());//always use asString() for getBoday()
  }
  
  //concept of pojo class
  @Test (description="Automate post methods of users using pojoclass")
  public void validatePostWithPojoClass() throws IOException, ParseException
  {
	  ExtentReport.extentlog=ExtentReport.extentreport.startTest("validatePostWithPojoClass","post methods using pojo classes");    
	 String job="automation";
	 String name="priyanka";
	  PojoClass obj = new PojoClass(name,job);  
	  Response resp= given().header(ReadTestData.getTestData("contentType"),ReadTestData.getTestData("contentTypeValue")).body(obj).
			  when().post(Helper.propertyReader(Helper.commonFilePath, "baseurl")+ReadTestData.getTestData("getUsersURI"));
	  assertEquals(resp.getStatusCode(),201);
	  System.out.println("Status code for post method is:  " + resp.getStatusCode());
	  System.out.println("Response body of post method :" + resp.getBody().asString());//always use asString() for getBoday()
  }
}
