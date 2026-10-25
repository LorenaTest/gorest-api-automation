package client;

import config.Configuration;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestDelete implements IRequest {

    @Override
    public Response send(RequestInfo info) {

        return given()
                .header("Authorization", "Bearer " + Configuration.TOKEN)
                .header("Accept", "application/json")
                .log()
                .all()

                .when()
                .delete(info.getUrl());
    }
}