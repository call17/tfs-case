import com.jayway.restassured.response.Response;
import http.RequestFabric;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

import static helpers.ResponseHelper.getAttributeArrayFromResponseByFilter;
/**
 * Created by sbt-yakimov-vi on 07.07.2017.
 */
public class TempTest {



    @Test
    public void testAvtorisation(){
        RequestFabric requestFabric = new RequestFabric();

        requestFabric.auth("", "");
        Response response = requestFabric.getTree("{\"traverseTree\":{\"target\":\"/CIB\",\"depth\":99}}");

       String jsonpath = "$..document.id";

        List list = new ArrayList();
        list = getAttributeArrayFromResponseByFilter(response, jsonpath);

        System.out.println(list.get(5));
    }
}
