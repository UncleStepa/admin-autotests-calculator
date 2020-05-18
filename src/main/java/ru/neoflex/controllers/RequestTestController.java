package ru.neoflex.controllers;
import io.restassured.http.ContentType;
import ru.neoflex.model.RequestSaveTestimony;
import ru.neoflex.model.ResponseSaveTestimony;

import static io.restassured.RestAssured.given;

public class RequestTestController {

    //Метод для отправки Post запроса на микросервис.

    public static int getRequestCode(String uRL, RequestSaveTestimony requestSaveTestimony) {

        return given().
                contentType(ContentType.JSON).
                body(requestSaveTestimony).
                when().
                post(uRL).
                then().
                extract().
                response().
                getStatusCode();
    }

    public static int getRequestCodeGetMethod(String uRL) {
        return given().
                when().
                get(uRL).
                then().
                extract().
                response().
                getStatusCode();
    }

    public static ResponseSaveTestimony getResponseBodySave(String uRL, RequestSaveTestimony requestSaveTestimony) {

        return  given().
                contentType(ContentType.JSON).
                body(requestSaveTestimony).
                when().
                post(uRL).
                then().
                extract().
                response().
                as(ResponseSaveTestimony.class);
    }
}
