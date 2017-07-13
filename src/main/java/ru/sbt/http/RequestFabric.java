package ru.sbt.http;

import com.jayway.restassured.response.Response;
import org.apache.commons.codec.binary.Base64;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by sbt-yakimov-vi on 07.07.2017.
 */
public class RequestFabric {

    private String cookiUFS;
    private String cookiPD;


    public Response auth(String login, String password){
        String auth = login + "-ext:" + password;

        byte[] encodedBytes = Base64.encodeBase64(auth.getBytes());
        auth = "Basic " + new String(encodedBytes);

        Response response = given()
                .header("Authorization", auth)
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

    public Response getTransferFile(String body){
        Response response =  given()
                .cookie("UFS-SESSION", cookiUFS)
                .cookie("PD-S-SESSION-ID", cookiPD)
                .contentType("application/json")
                .body(body)
                .post("http://sbt-orefs-091.sigma.sbrf.ru/ufs-tfs-bh/rs/file/download")
                ;
        response.then().log().all();

        return  response;
    }

    public Response getTransferStatus(String operationId){
        Response response = given()
                .cookie("UFS-SESSION", cookiUFS)
                .cookie("PD-S-SESSION-ID", cookiPD)
                .contentType("application/json")
                .get("http://sbt-orefs-091.sigma.sbrf.ru/ufs-tfs-bh/rs/file/status/635465cb-252b-44a5-98ad-c12a091f6667")
                ;
        response.then().log().all();

        return response;

    }
}
