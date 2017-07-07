package http;

import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by sbt-yakimov-vi on 07.07.2017.
 */
public class RequestFabric {

    private String cookiUFS;
    private String cookiPD;


    public Response auth(String login, String password){

        Response response = given()
                .header("Authorization", "Basic dGVzdGNybW1hbmFnZXI2LWV4dDpxMTIzNDU2Nzg=")
                .get("http://sbt-orefs-091.sigma.sbrf.ru/km-cib-app/rs/auth")
                ;
        response.then().log().all();

        cookiUFS = response.then().extract().cookie("AMWEBJCT!%2Fefs-mobile!UFS-SESSION");
        cookiPD = response.then().extract().cookie("PD-S-SESSION-ID");


        return response;
    }

    public Response getTree(String body){
        Response response = given()
                .cookie("UFS-SESSION", cookiUFS)
                .cookie("PD-S-SESSION-ID", cookiPD)
                .contentType("application/json")
                .body(body)
                .post("http://sbt-orefs-091.sigma.sbrf.ru/km-cib-app/rs/file-base/tree")
                ;
        response.then().log().all();


        return  response;
    }
}
