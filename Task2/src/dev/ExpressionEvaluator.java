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
//	      reader.close();
	      evaluateExpression(q);
	    }
	    catch(IOException e){
	      e.printStackTrace();
	    }
	}
	public static void evaluateExpression(ArrayList<String> list) {
		int N = list.size();
	    	for(String str : list) {
	    		try {
	    			String encodedVal = URLEncoder.encode(str, StandardCharsets.UTF_8.toString());
		    		String apiUrl = "https://api.mathjs.org/v4/?expr=" + encodedVal;
		    		//System.out.println(apiUrl);
		    		URL url = new URL(apiUrl);
		    		//System.out.println(url);
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
}
