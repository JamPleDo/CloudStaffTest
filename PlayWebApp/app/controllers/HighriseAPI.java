package controllers;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import models.PersonList;
import models.TagList;
import sun.misc.BASE64Encoder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HighriseAPI {
	
	private Client client;
	private String token;
	private String password = "X";
	
	public HighriseAPI(){
	}
	
	public HighriseAPI(String token){
		client = Client.create();
		this.token = token;
	}
	
    public PersonList getContactByTag(String tag) {
		try {
			String authString = token + ":" + password;
			String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
			
			WebResource webResource = client.resource("https://seambestbusinesssolutioninc.highrisehq.com/people.xml?tag=" + tag);
			ClientResponse resp = webResource.accept("application/xml").header("Authorization", "Basic " + authStringEnc).get(ClientResponse.class);
			
			if (resp.getStatus() != 200) {
				System.err.println("Unable to connect to the server");
				return null;
			}
			
			Serializer serializer = new Persister();

			return serializer.read(PersonList.class, resp.getEntity(String.class));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    
    public TagList getTags() {
		try {
			String authString = token + ":" + password;
			String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
			
			WebResource webResource = client.resource("https://seambestbusinesssolutioninc.highrisehq.com/tags.xml");
			ClientResponse resp = webResource.accept("application/xml").header("Authorization", "Basic " + authStringEnc).get(ClientResponse.class);
			
			if (resp.getStatus() != 200) {
				System.err.println("Unable to connect to the server");
			}
			
			Serializer serializer = new Persister();

			return serializer.read(TagList.class, resp.getEntity(String.class));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
