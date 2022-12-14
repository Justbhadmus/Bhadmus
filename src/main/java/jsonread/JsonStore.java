package jsonread;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class JsonStore {

    String baseURI = "https://jsonplaceholder.typicode.com";
    Random picker = new Random();
    int pick = pickRandomUserId(getAllUsersID());
    // Get all IDs
    public ArrayList getAllUsersID(){
        Response resp = given()
                .contentType(ContentType.JSON)
                .when().get(baseURI +"/users");
        ArrayList id = resp.then().extract().path("id");
        return id;
    }
    // Gets a random userID
    public int pickRandomUserId(ArrayList m){
        return (int) m.get(picker.nextInt(m.size()));
    }

    public boolean checkNumberRange(ArrayList l){
        for(int i=0; i < l.size(); i++){
            return((int)l.get(i)>1 && (int)l.get(i)<100);
        }
        return false;
    }

    public void printUsernameToConsole(){
        String basePATH = String.format(baseURI+"/users/%d", pick);
        Response resp = given()
                .contentType(ContentType.JSON)
                .when()
                .get(basePATH);
        String email = resp.then().extract().path("email");
        System.out.println(email);
    }

    public void getUSerIDAndVerifyRange(){
        String basePATH = String.format(baseURI+"/users/%d/posts", pick);
        Response resp = given()
                .contentType(ContentType.JSON)
                .when().get(basePATH);
        ArrayList id = resp.then().extract().path("id");
        System.out.println(checkNumberRange(id));

    }

    public void postNonEmptyComment(){
        Response resp = given()
                .contentType(ContentType.JSON)
                .body(String.format("{\n" +
                        "    \"title\": \"non-empty title for user ID %d\",\n" +
                        "    \"body\": \"non-empty body\",\n" +
                        "    \"userId\": %d\n" +
                        "}", pick, pick))
                .when().post(baseURI+"/posts");
        String title = resp.then().extract().path("title");
        boolean present = title.contains(String.format("user ID %d", pick));
        System.out.println(present);
        Assert.assertEquals(resp.getStatusCode(), 201);
    }



}


