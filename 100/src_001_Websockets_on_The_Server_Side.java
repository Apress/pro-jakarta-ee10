package book.jakartapro.websocket.server;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
    
@ApplicationScoped
@ServerEndpoint("/actions")
public class WebSocketServer {	
    @OnOpen
    public void open(Session session) {
        // Somehow handle an opened session. For example
        // save the session object in the instance of
        // some session registrar class... 
    }

    @OnClose
    public void close(Session session) {
        // Somehow handle a closed session
    }

    @OnError
    public void onError(Throwable error) {
        // Somehow handle a websocket error
    }

    @OnMessage
    public void handleMessage(String message, 
            Session session) {
        // Receive a string message. It is not required 
        // to use JSON as a message format, but it is a 
        // good choice in many cases and it simplifies
        // programming JavaScript websocket clients  
    	try (JsonReader reader = Json.createReader(
                  new StringReader(message))) {
            JsonObject jsonMessage = reader.readObject();

            // handle the incomming message...
        }
    }
}    
