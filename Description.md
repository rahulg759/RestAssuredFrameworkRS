Remember - Always run on your run tomcat port. My tomcat port is 8087

Pckg -->>> src/main/java -->>> com.api.rest.api.helper -->>

GetRequest class - This is a class in which i used main method and call methods from here.

PostRequest class - This is a class in which i used main method and call methods from here.

DeleteRequest class - This is a class in which i used main method and call methods from here.


RestAPIHelper - This is a framework level class in which i have drafted all generic methods.



Pckg -->>> src/test/java -->>> com.api.rest.junit.helper -->>

1- Class - TestPostHelper ---- >>>
    This is a class in which i used JUNIT annotations for executing test cases.
    
2-  Class - TestGetHelper ---- >>>
    This is a class in which i used JUNIT annotations for executing test cases.
    
3-  Class - TestDeleteHelper ---- >>>>
    This is a class in which i used JUNIT annotations for executing test cases.
    
    
  =============================================================###############============================================================
  
  
1- Normal laptop war file is just like we are sending the request and getting the response as a normal so we are sending the request as a delete request, post request, put request and get request.
All these API description will found in MyResource class in document (index.html).


2- For authentication, we need to go MyResourceSecure class from index.com( html file ). these are basic authentication requests.
If we are not sending the correct credentials along with the api request or sending the blank credential so it will throw the 401 error as well unauthorised author.
After adding the authentication in authorization section in postman and click on preview request then automatically authentication will be added in header section.

whenever we send the authorisation as admin and welcome, then postman this admin and welcome paese it like this - admin:welcome in string format.
and convert this entire string admin:welcome in encrypted form using base64 encoded.
Append this credential admin:welcome as YWRtaW46d2VsY29tZQ==(this is a encrypted form of admin:welcome using base64 encoding - base64 encoded string).

In postman when you select raw and select the application/JSON in reuest body then in header section automatically content type as applications/JSON will be added.

3-

=============================================================###############============================================================

Prompt war file -->>

1 - For prompt first we need to deploy laptop war file as prompt in tomcat.
2 - Go to ResourceSecureWithPrompt (index.html) and get the details from there.

Fidler - Fiddler is a free debugging proxy for any browser. We can use it to compose and execute different HTTP requests to our Web API and check HTTP response. Let's see how to use Fiddler to send an HTTP request to our local Web API and check the response.

