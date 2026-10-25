package runner;

import client.FactoryRequest;
import client.RequestInfo;
import config.Configuration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class MyStepdefs {

    Response response;
    RequestInfo info;

    Map<String, String> variables = new HashMap<>();

    @When("send {} request {string} with")
    public void sendRequestWith(
            String method,
            String url,
            String body) {

        String finalUrl =
                Configuration.BASE_URL + replaceVar(url);

        info = new RequestInfo(finalUrl, body);

        response = FactoryRequest.make(method).send(info);
    }

    @Then("code should be {int}")
    public void codeShouldBe(int expectedCode) {

        response.then()
                .statusCode(expectedCode);
    }

    @And("the {string} should be {string}")
    public void theShouldBe(
            String attribute,
            String value) {

        response.then()
                .body(attribute, equalTo(value));
    }

    @And("save {string} in {string}")
    public void saveAttribute(
            String attribute,
            String variable) {

        Object value = response.then()
                .extract()
                .path(attribute);

        variables.put(variable, String.valueOf(value));

        System.out.println(
                "Variable " + variable + " = " + value
        );
    }

    private String replaceVar(String value) {

        for (Map.Entry<String, String> entry
                : variables.entrySet()) {

            value = value.replace(
                    entry.getKey(),
                    entry.getValue()
            );
        }

        return value;
    }
}