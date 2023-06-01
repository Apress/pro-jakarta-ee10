import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.function.BiFunction;
import java.util.function.Function;
...

final String SERVER = "127.0.0.1:5984";
final String USER = "admin";
final String PASSWD = "PASSWD";

var auth = new Authenticator() {
    protected PasswordAuthentication 
    getPasswordAuthentication() {
        return new PasswordAuthentication(USER, 
              PASSWD.toCharArray());
    }
};
var encodedAuth = Base64.getEncoder()
    .encodeToString((USER + ":" + PASSWD)
    .getBytes(StandardCharsets.UTF_8));		

Function<String,HttpRequest> get = (url) -> {
    return HttpRequest.newBuilder()
       .uri(URI.create("http://" + SERVER  + "/" + url))
       .timeout(Duration.ofSeconds(10))
       .header("Authorization", "Basic " + encodedAuth)
       .GET().build();
};

Function<String,HttpRequest> delete = (url) -> {
    return HttpRequest.newBuilder()
        .uri(URI.create("http://" + SERVER  + "/" + url))
        .timeout(Duration.ofSeconds(10))
        .header("Authorization", "Basic " + encodedAuth)
        .DELETE().build();
};

BiFunction<String,String,HttpRequest> post = 
(url,body) -> {
    return HttpRequest.newBuilder()
        .uri(URI.create("http://" + SERVER + "/" + url))
        .timeout(Duration.ofSeconds(10))
        .header("Authorization", "Basic " + encodedAuth)
        .POST(HttpRequest.BodyPublishers.ofString(body))
        .build();
};

BiFunction<String,String,HttpRequest> put = (url,body) -> {
    return HttpRequest.newBuilder()
        .uri(URI.create("http://" + SERVER + "/" + url))
        .timeout(Duration.ofSeconds(10))
        .header("Authorization", "Basic " + encodedAuth)
        .PUT(HttpRequest.BodyPublishers.ofString(body))
        .build();
};
	 
HttpClient client = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .connectTimeout(Duration.ofSeconds(10))
        .authenticator(auth)
        .build();
		
Function<HttpRequest,HttpResponse<String>> invoke = 
(req) -> {
    try {
        return client.send(req, BodyHandlers.ofString());
    } catch (Exception e) {
        e.printStackTrace(System.err);
        return null;	
   } 		
};
