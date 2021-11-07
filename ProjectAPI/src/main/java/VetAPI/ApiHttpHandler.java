package VetAPI;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang.StringEscapeUtils;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class ApiHttpHandler implements HttpHandler {
		DataStore datastore;
		String command;

	  public ApiHttpHandler() {
			super();
			this.datastore = new DataStore();
		}

	@Override

	  public void handle(HttpExchange httpExchange) throws IOException {

	    String requestParamValue=null;

	    if("GET".equals(httpExchange.getRequestMethod())) {

	       requestParamValue = handleGetRequest(httpExchange);

	     }else if("POST".equals(httpExchange.getRequestMethod())) {


	    	 requestParamValue = handlePostRequest(httpExchange);

	      }


	    handleResponse(httpExchange,requestParamValue);

	  }

	  private String handlePostRequest(HttpExchange httpExchange) {
		  System.out.println("POST: "+httpExchange.getRequestURI().toString());
		  String parse = httpExchange.getRequestURI().toString().split("\\?")[1];
		  System.out.println(parse);
		  String[] strArray = parse.split("&");
		  if(strArray.length>0) {
			  this.command = strArray[0].split("=")[1];
			  if(this.command.contains("addUser")) {
				  String fname = null;
				  String mname = null;
				  String lname = null;
				  String email = null;
				  String role = null;
				  String status = null;
				  String password = null;
				  for(int i = 0; i<strArray.length;i++) {

					  if(strArray[i].split("=")[0].equals("fname")) {
						  fname = strArray[i].split("=")[1];
					  }
					  if(strArray[i].split("=")[0].equals("mname")) {
						  mname = strArray[i].split("=")[1];
					  }
					  if(strArray[i].split("=")[0].equals("lname")) {
						  lname = strArray[i].split("=")[1];
					  }
					  if(strArray[i].split("=")[0].equals("email")) {
						  email = strArray[i].split("=")[1];
					  }
					  if(strArray[i].split("=")[0].equals("role")) {
						  role = strArray[i].split("=")[1];
					  }
					  if(strArray[i].split("=")[0].equals("status")) {
						  status = strArray[i].split("=")[1];
					  }
					  if(strArray[i].split("=")[0].equals("password")) {
						  password = strArray[i].split("=")[1];
					  }

				  }

				  if(fname !=null && mname !=null && lname !=null && email !=null && role !=null && status !=null && password !=null) {
					  this.datastore.addUser(fname, mname, lname, email, role, status, password);

					  return "post success";
				  }

			  }



		  }



          return "post failed: missing parameters";

	  }




	   private String handleGetRequest(HttpExchange httpExchange) {
		   System.out.println("GET: "+httpExchange.getRequestURI().toString());
		   System.out.println(httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[0]);
		   this.command = httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[0];

	       return httpExchange.getRequestURI().toString().split("\\?")[1].split("=")[1];

	   }


	   private void handleResponse(HttpExchange httpExchange, String requestParamValue)  throws  IOException {

	            OutputStream outputStream = httpExchange.getResponseBody();
	            String response = "";

	            if(this.command.contains("getUser")) {
	            	response = "Details for: " + requestParamValue + '\n';
		            if(this.datastore.userExist(requestParamValue)) {
		            	User user = this.datastore.getUser(requestParamValue);
		            	response = response + "Email: " + user.getEmail() + "\nFirst Name: " + user.getFname() + "\nStatus: " + user.getStatus();

		            }
		            else {
		            	response = "User not found";
		            }

	            }

	            if(this.command.contains("addUser")) {
	            	response = requestParamValue;
	            }






	            // this line is a must

	            httpExchange.sendResponseHeaders(200, response.length());


	            outputStream.write(response.getBytes());

	            outputStream.flush();

	            outputStream.close();

	        }

	}
