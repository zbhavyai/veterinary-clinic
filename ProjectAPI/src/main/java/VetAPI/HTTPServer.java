package VetAPI;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class HTTPServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
			server.createContext("/test", new  ApiHttpHandler());
			ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newCachedThreadPool();

			server.setExecutor(threadPoolExecutor);

			server.start();

			System.out.println(" Server started on port 8001");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
