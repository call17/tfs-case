package http.model.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import step.EcmStep;

/**
 * Created by sbt-yakimov-vi on 07.07.2017.
 */
public class ECMTest {

    EcmStep ecmStep;


    @BeforeMethod
    public void init(){
        ecmStep = new EcmStep();
    }

    @Features("Проверка ЕСМ")
    @Stories("Перекладка файла")
    @Test
    public void testEcm(){

        ecmStep.login("testcrmmanager3", "q12345678");
        String id = ecmStep.checkTree("{\"traverseTree\":{\"target\":\"/CIB\",\"depth\":99}}");
        String operationId = ecmStep.sendRequestTransferFile(id);
        ecmStep.getStatusFile(operationId);
    }
}
