package client;

import client.IRequest;
import client.RequestInfo;
import config.Configuration;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestGet implements IRequest {

    @Override
    public Response send(RequestInfo info) {

        return given()
                .header("Authorization", "Bearer " + Configuration.TOKEN)
                .header("Accept", "application/json")
                .log()
                .all()

                .when()
                .get(info.getUrl());
    }
}