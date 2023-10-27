package dev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExpressionEvaluator {

	public static void main(String[] args) {
		InputStreamReader input = new InputStreamReader(System.in);
	    BufferedReader reader = new BufferedReader(input);
	    String line;
	    
	    System.out.println("Enter expressions to evaluate and type 'end' to finish :");

	    ArrayList<String> q = new ArrayList<>();

	    try{
	      while(!(line = reader.readLine()).equals("end")){
	    	  String exp = line;
	    	  q.add(exp);
	      }
	      evaluateExpression(q);
	    }
	    catch(IOException e){
	      e.printStackTrace();
	    }
	}
	public static void evaluateExpression(ArrayList<String> expressions) {
		for(String expression : expressions) {
			Thread evaluateThread = new Thread(() -> processExpression(expression));
			evaluateThread.start();
			try {
				evaluateThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void processExpression(String str) {
		try {
			String encodedVal = URLEncoder.encode(str, StandardCharsets.UTF_8.toString());
    		String apiUrl = "https://api.mathjs.org/v4/?expr=" + encodedVal;
    		URL url = new URL(apiUrl);
    		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    		if(connection.getResponseCode() == 200) {
    			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    			String result = reader.readLine();
    			System.out.println(str + " => " + result);
    			reader.close();
    			connection.disconnect();
    		}
    		else {
    			System.out.println(str + " => Error " + connection.getResponseCode() + connection.getResponseMessage());
    		}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
	    }

	  }
}

/*
 * Regarding the requirement that the API supports only 50 requests per second per client and my application 
 * has to implement 500 requests per second. There can be 3 scenerios for this:
 * 1. Using single machine
 * If we are running code on single machine, the server identifies client with ip address. Therefore we can't make 
 * 500 calls if ip rate limit is of 50 calls.
 * 
 * 2.
 * Using single machine
 * We can negotiate with the server to increase the limit to 500.
 * 
 * 3. Using multi machine
 * We can use multiple machines and load balancers in round robin fashion.
 * 
 * */
