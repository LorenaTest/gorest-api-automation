package client;

import config.Configuration;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;


public class RequestPost implements IRequest {

    @Override
    public Response send(RequestInfo info) {

        return given()
                .header("Authorization", "Bearer " + Configuration.TOKEN)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(info.getBody())
                .log()
                .all()

                .when()
                .post(info.getUrl());
    }
}