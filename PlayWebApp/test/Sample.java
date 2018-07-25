import sun.misc.BASE64Encoder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Sample {

  public static void main(String[] args) {

	  //testPostWithoutBasicAuth();
	  //testPOSTWithBasicAuth();
	  testGETWithBasicAuth();
 }

  /**
   * Below method is used to test GET request with HTTP Basic authentication in the header of the request
   */
	private static void testGETWithBasicAuth() {
		try {
	
	        Client client = Client.create();
	
	        String name = "b3eab3582ebd43cdaab7b2ed0c07c71b";
	        String password = "X";
	        String authString = name + ":" + password;
	        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
	        System.out.println("Base64 encoded auth string: " + authStringEnc);
	        WebResource webResource = client.resource("https://seambestbusinesssolutioninc.highrisehq.com/people.xml");
	        
	        ClientResponse resp = webResource.accept("application/xml")
	                                         .header("Authorization", "Basic " + authStringEnc)
	                                         .get(ClientResponse.class);
	        if(resp.getStatus() != 200){
	            System.err.println("Unable to connect to the server");
	        }
	        String output = resp.getEntity(String.class);
	        System.out.println("Response for the GET with HTTP Basic authentication request: "+output);
	
	      } catch (Exception e) {
	
	        e.printStackTrace();
	
	      } finally {
	    	  System.out.println("=========================================================================");
	      }
	}
	
	/**
	 * Below method is used to test POST request with HTTP Basic authentication in the header of the request
	 */
	private static void testPOSTWithBasicAuth() {
		try {
	
	        Client client = Client.create();
	
	        String name = "admin";
	        String password = "admin";
	        String authString = name + ":" + password;
	        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
	        System.out.println("Base64 encoded auth string: " + authStringEnc);
	        WebResource webResource = client.resource("http://localhost:8080/RESTfulAuth/rest/hello/getSalary");
	        
	        ClientResponse resp = webResource.accept("application/json")
	                                         .header("Authorization", "Basic " + authStringEnc)
	                                         .post(ClientResponse.class);
	        if(resp.getStatus() != 200){
	            System.err.println("Unable to connect to the server");
	        }
	        String output = resp.getEntity(String.class);
	        System.out.println("Response for the POST with HTTP Basic authentication request: "+output);
	      } catch (Exception e) {
	        e.printStackTrace();
	      } finally {
	    	  System.out.println("=========================================================================");
	      }
	}
	
	/**
	 * Below method is used to test POST request without HTTP Basic authentication in the header of the request
	 */
	private static void testPostWithoutBasicAuth() {
		try {
	
	        Client client = Client.create();
	        WebResource webResource = client.resource("http://localhost:8080/RESTfulAuth/rest/hello/getSalary");
	
	        String input = "{\"empId\":\"123\"}";
	
	        ClientResponse response = webResource.type("application/json")
	           .post(ClientResponse.class, input);
	
	        if (response.getStatus() != 201) {
	            throw new RuntimeException("Failed : HTTP error code : "
	                 + response.getStatus());
	        }
	
	        System.out.println("HTTP Basic authentication error .... \n");
	        String output = response.getEntity(String.class);
	        System.out.println(output);
	      } catch (Exception e) {
	        e.printStackTrace();
	      } finally {
	    	  System.out.println("=========================================================================");
	      }
	}
}