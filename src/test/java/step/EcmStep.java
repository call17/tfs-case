package step;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jayway.restassured.response.Response;
import org.testng.Assert;
import ru.sbt.http.RequestFabric;
import ru.sbt.http.model.CachePolicy;
import ru.sbt.http.model.RequestTransferFile;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;
import java.util.Random;

import static com.jayway.jsonpath.JsonPath.parse;
import static ru.sbt.http.helpers.ResponseHelper.getAttributeArrayFromResponseByFilter;

/**
 * Created by sbt-yakimov-vi on 11.07.2017.
 */
public class EcmStep {
    private RequestFabric requestFabric;
    private Response response;
    private ObjectWriter objectWriter;

    public EcmStep(){
        requestFabric = new RequestFabric();
    }

    @Step("Авторизация")
    public  void login(String userName, String password){
        response = requestFabric.auth(userName, password);
        response.then().statusCode(200);
    }

    @Step("Получение дерева файлов")
    public String checkTree(String json){
        response = requestFabric.getTree(json);
        response.then().statusCode(200);

        String jsonpath = "$..document.id";

        List list;
        list = getAttributeArrayFromResponseByFilter(response, jsonpath);

        return (String) list.get(new Random().nextInt(list.size()));
    }

    @Step("Запрос на перекладку файла")
    public String sendRequestTransferFile(String fileId){


        CachePolicy  cachePolicy = new CachePolicy();
        cachePolicy.setType("CACHE");
        cachePolicy.setMaxTTL(600);


        objectWriter = new ObjectMapper().writer();
        RequestTransferFile requestTransferFile = new RequestTransferFile();
        requestTransferFile.setFileId(fileId);
        requestTransferFile.setCachePolicy(cachePolicy);
        requestTransferFile.setScenarioId("32370800");
        requestTransferFile.setUser("ecm_support_4_efs");
        String request = null;
        try {
            request = objectWriter.writeValueAsString(requestTransferFile);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        response = requestFabric.getTransferFile(request);

        return parse(response.then().extract().body().asString()).read("$.body.operationId");

    }

    @Step("Статус перекладки файла")
    public void getStatusFile(String operationId){
        response = requestFabric.getTransferStatus(operationId);
        String status = parse(response.then().extract().body().asString()).read("$.body.statusCode");
        Assert.assertNotEquals(status,"EXPIRED","TimeOut");
        Assert.assertNotEquals(status,"FAILED_TFS","TFS");
        Assert.assertNotEquals(status,"FAILED_ECM","ECM");
        Assert.assertEquals(status,"SUCCEEDED");
    }

}
