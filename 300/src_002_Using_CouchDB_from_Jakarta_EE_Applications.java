// ----- CouchDB info
var request1 = get.apply("");
var response1 = invoke.apply(request1);
System.out.println(response1.statusCode() +"\n" 
    + response1.body());  

// ----- create database
var request2 = put.apply("mydb","");
var response2 = invoke.apply(request2);
System.out.println(response2.statusCode() + "\n" 
    + response2.body());  

// ----- create entry
var request3 = put.apply("mydb/001",
    "{ \"name\":\"John\", \"age\":42 }");
var response3 = invoke.apply(request3);
System.out.println(response3.statusCode() + "\n" 
    + response3.body());  

// ----- delete database
var request99 = delete.apply("mydb");
var response99 = invoke.apply(request99);
System.out.println(response99.statusCode() + "\n" 
    + response99.body());  
