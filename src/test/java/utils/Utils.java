package utils;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {

    static PrintStream log;
    static Properties properties;
    static {
        try {
            log = new PrintStream(new FileOutputStream("src/log.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try{
             properties = new Properties();
            FileInputStream file;
            file = new FileInputStream("src/test/resources/global.properties");
            properties.load(file);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }



    public static RequestSpecification requestSpecificationBuilder() throws IOException {
        return new RequestSpecBuilder()
                .setBaseUri(getGlobalValue("baseURL"))
                .setContentType(ContentType.JSON)
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .build();

    }

    public static ResponseSpecification responseSpecificationBuilder() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON).build();
    }

    public static String getGlobalValue(String key) throws IOException {
        return properties.getProperty(key);
    }

    public static void setGlobalValue(String key, String value){
        properties.setProperty(key, value);
    }
    public static String generateEmail(){

        return new Faker().internet().emailAddress();
    }
    public static String generatePassword(){
        return  new Faker().internet().password();
    }
    public static String generateFirstName(){
        return new Faker().name().firstName();
    }

    public static  String generateLastName(){

        return new Faker().name().lastName();
    }
    public static String generatePhoneNumber(){
        return  String.valueOf(new Faker().number().digits(10));
    }
}
