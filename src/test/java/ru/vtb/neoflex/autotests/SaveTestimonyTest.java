package ru.vtb.neoflex.autotests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.neoflex.controllers.RequestTestController;
import ru.neoflex.dao.MySqlConnector;
import ru.neoflex.model.CurrentTestimony;
import ru.neoflex.model.RequestSaveTestimony;
import ru.neoflex.model.ResponseSaveTestimony;

import java.sql.SQLException;

public class SaveTestimonyTest {
  static String saveTestimonyURI = "http://localhost:8080/services/testimony/save";

  @Test
  public void checkCodeSuccessTest() throws SQLException {
    RequestSaveTestimony requestSaveTestimony = new RequestSaveTestimony();
    CurrentTestimony currentTestimony = new CurrentTestimony();

    requestSaveTestimony.setDate("02-2020");
    currentTestimony.setColdWater(30);
    currentTestimony.setHotWater(40);
    currentTestimony.setGas(50);
    currentTestimony.setElectricity(60);
    requestSaveTestimony.setCurrentTestimony(currentTestimony);

    int actualStatusCode = RequestTestController.getRequestCode(saveTestimonyURI, requestSaveTestimony);
    System.out.println("statusCode : " + actualStatusCode);
    Assertions.assertEquals(200, actualStatusCode);
   MySqlConnector.selectAllFrommBilling();
  }


  @Test
  public void checkFaultCodeSuccessTest()  {
    RequestSaveTestimony requestSaveTestimony = new RequestSaveTestimony();
    CurrentTestimony currentTestimony = new CurrentTestimony();

    requestSaveTestimony.setDate("02-2020");
    currentTestimony.setColdWater(30);
    currentTestimony.setHotWater(40);
    currentTestimony.setGas(50);
    currentTestimony.setElectricity(60);
    requestSaveTestimony.setCurrentTestimony(currentTestimony);

    ResponseSaveTestimony responseSaveTestimony = RequestTestController.getResponseBodySave(saveTestimonyURI, requestSaveTestimony);
    String resultCode = responseSaveTestimony.getFaultcode().getResultCode();
    String resultText = responseSaveTestimony.getFaultcode().getResultText();
    System.out.println(resultCode);
    System.out.println(resultText);
    Assertions.assertEquals("0", resultCode);
    Assertions.assertEquals("success", resultText);

  }
}