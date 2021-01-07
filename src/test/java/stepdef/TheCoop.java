package stepdef;

import Pojo.CoopResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TheCoop {

    static String response = null;
    private static FileWriter file;
    static String code;
    private static String directory=System.getProperty("user.dir");
    private String sample=directory+"/src/main/java/testdata"+ File.separator+"sample.json";
    static  String resp = "null";

    @Given("The page URL is {string}")
    public void thePageURLIs(String arg0) {
        RestAssured.baseURI = arg0;
    }

    @Given("The barn unlock url is {string}")
    public void theBarnUnlockUrlIs(String arg0) {
        RestAssured.baseURI = RestAssured.baseURI + arg0;
    }

    @When("Perform post operation")
    public void performPostOperation(DataTable table) {
        List<Map<String, String>> list = table.asMaps();
        Map<String, String> headerMap = new HashMap<String, String>();
        int count = list.size();
        int num;
        for (num = 0; num < count; num++) {
            headerMap.put(list.get(num).get("Key"), list.get(num).get("Value"));
        }
        response = given().log().all().headers(headerMap).
                when().post().
                then().log().all().extract().response().asString();
        CoopResponse co = given().log().all().headers(headerMap).
                when().post().then().log().all().extract().response().as(CoopResponse.class);
        System.out.println(co.getAction());
        System.out.println(co.getData());
        System.out.println(co.getMessage());
        System.out.println(co.getSuccess());

    }

    @Then("Validate the response of post")
    public void validateTheResponseOfPost() {
        System.out.println(response);
    }

    @When("perform post token call")
    public void performPostTokenCall() throws JsonProcessingException {

        CoopResponse cr = given()
                .formParam("client_id","RestApi")
                .formParam("client_secret","75e3ca564180f1d9ed9ccee0f449aeb4")
                .formParam("grant_type","client_credentials")
                .post("http://coop.apps.symfonycasts.com/token")
                .then().extract().response().as(CoopResponse.class);
        System.out.println("Access_token: "+cr.getAccess_token());
        System.out.println("expires_in: "+cr.getExpires_in());
        System.out.println("scope: "+cr.getScope());
        System.out.println("token_type: "+cr.getToken_type());

        code = cr.getAccess_token();
        System.out.println(code);

        String resp;
        resp = given()
                .auth()
                .oauth2(code)
                .post("http://coop.apps.symfonycasts.com/api/1568/barn-unlock").asString();
        System.out.println(resp);
    }

    @Then("validate call")
    public void validateCall() {

    }


    @When("perform chickens-feed call")
    public void performChickensFeedCall() {

        resp = acccode();
        resp = given()
                .auth()
                .oauth2(resp)
                .post("http://coop.apps.symfonycasts.com/api/1568/chickens-feed").asString();
        System.out.println(resp);
    }

    public String acccode(){
        CoopResponse cr = given()
                .header("ContentType","application/json")
                .formParam("client_id","RestApi")
                .formParam("client_secret","75e3ca564180f1d9ed9ccee0f449aeb4")
                .formParam("grant_type","client_credentials")
                .post("http://coop.apps.symfonycasts.com/token")
                .then().extract().response().as(CoopResponse.class);

        code = cr.getAccess_token();
        System.out.println(code);
        return code;
    }

    @Given("make the token call")
    public void makeTheTokenCall() {

    }

    @When("perform toiletseat-down call")
    public void performToiletseatDownCall() {
        resp = acccode();
        resp = given()
                .auth()
                .oauth2(resp)
                .post("http://coop.apps.symfonycasts.com/api/1568/toiletseat-down").asString();
        System.out.println(resp);

    }

    @When("perform barn-unlock call")
    public void performBarnUnlockCall() {
        resp = acccode();
        resp = given()
                .auth()
                .oauth2(resp)
                .post("http://coop.apps.symfonycasts.com/api/1568/barn-unlock").asString();
        System.out.println(resp);

    }

    @When("perform eggs-collect call")
    public void performEggsCollectCall() {
        resp = acccode();
        resp = given()
                .auth()
                .oauth2(resp)
                .post("http://coop.apps.symfonycasts.com/api/1568/eggs-collect").asString();
        System.out.println(resp);

    }

    @When("perform eggs-count call")
    public void performEggsCountCall() {
        resp = acccode();
        resp = given()
                .auth()
                .oauth2(resp)
                .post("http://coop.apps.symfonycasts.com/api/1568/eggs-count").asString();
        System.out.println(resp);
    }
}
