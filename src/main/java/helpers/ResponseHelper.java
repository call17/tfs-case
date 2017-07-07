package helpers;

import com.jayway.restassured.response.Response;

import java.util.List;

import static com.jayway.jsonpath.JsonPath.parse;
/**
 * Created by sbt-yakimov-vi on 07.07.2017.
 */
public class ResponseHelper {


    public static List getAttributeArrayFromResponseByFilter(Response response, String jsonpath){

        return parse(response.then().extract().body().asString()).read(jsonpath, List.class);
    }

}
