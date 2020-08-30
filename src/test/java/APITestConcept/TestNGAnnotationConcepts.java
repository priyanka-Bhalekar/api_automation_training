package APITestConcept;


import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.util.ReadTestData;

import io.restassured.response.Response;

public class TestNGAnnotationConcepts {
	
	@Test (dataProvider = "data-provider")
	public void f(Integer i, String s)
	{
		System.out.println(i+s);
	}
	
	@Test(priority=2, description="validate 200 status code GET API", groups="Regression")
	  public void verifyGetUsersSC() throws IOException, ParseException {
		  Response resp= given().when().get(ReadTestData.getTestData("getUserURI"));
		  int actualStatusCode= resp.getStatusCode();
		  assertEquals(actualStatusCode,200);
		  System.out.println("Status code of the Get user method is" + actualStatusCode);
	  }
	  
	  @Test(priority=1, description="validate 404 status code for invalid url" , groups= {"Regression","Smoke"},enabled=true)
	  public void verifyInvalidURL() throws IOException, ParseException {
		  Response resp= given()
				  .when().get(ReadTestData.getTestData("invalidURI"));
		  int actualStatusCode= resp.getStatusCode();
		  assertEquals(actualStatusCode,404);
		  System.out.println("Status code of the invalid URL is" + actualStatusCode);
	  }
	
	
	@DataProvider(name = "data-provider")
	public Object[][] dp()

	{
		return new Object[][] {
			
			new Object[] {1,"priya"},
			new Object[] {2,"shweta"},
			new Object[] {3,"ritu"}
		};
		
		
	}

	
	
}
