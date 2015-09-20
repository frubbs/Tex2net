package grizzly;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.grizzly2.servlet.GrizzlyWebContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class MainHTML {
	  /**
     * @param args the command line arguments
     */
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(8080).build();
    }
  
    //public static final URI BASE_URI = getBaseURI();
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8081/text2net/";

    
    protected static HttpServer startServer() throws IOException {
         
    	
    	 final ResourceConfig rc = new ResourceConfig().packages("com.text2net");
    	 HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    	 HttpHandler httpHandler = new CLStaticHttpHandler(HttpServer.class.getClassLoader(),"/static/");
    	 httpServer.getServerConfiguration().addHttpHandler(httpHandler, "/");
         // create and start a new instance of grizzly http server
         // exposing the Jersey application at BASE_URI
         return httpServer;
         //GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
         /*
    	
        final Map<String, String> initParams = new HashMap<String, String>();
  
        initParams.put("com.sun.jersey.config.property.packages",
                "com.test.jerseyrestgrizzly");
  
        System.out.println("Starting grizzly...");        
       
        return GrizzlyWebContainerFactory.create(BASE_URI, initParams); */
         
    }
  
    public static void main(String[] args) throws IOException {
         
        HttpServer httpServer = startServer();
         
       // httpServer.getServerConfiguration().addHttpHandler(new StaticHttpHandler("C://Users//rafa//workspace//Text2Net//text2net//html//TesteRestAngular"),"/test");        
         
        httpServer.start();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nTry out %shelloworld\nHit enter to stop it...",
                BASE_URI, BASE_URI));
        System.in.read();
         
        httpServer.stop();
         
    }
}
