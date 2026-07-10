package Project;


import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GitHubSSHKeyTest {

    private RequestSpecification requestSpec;

    // SSH public key to be added to GitHub
    private String sshKey =
            "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIGZrDek7Rzs2gkaqitv/DwrZKatPePmaXwzjNbOuBvP8 azuread\\nujellababuvaraprasa@IBM-PF5NFY43";

    // ID returned after key creation
    private int keyId;

    @BeforeClass
    public void setup() {

        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "token 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")
                .setBaseUri("https://api.github.com")
                .build();
    }

    @Test(priority = 1)
    public void addSSHKey() {

        String requestBody = "{\n" +
                "  \"title\": \"TestAPIKey\",\n" +
                "  \"key\": \"" + sshKey + "\"\n" +
                "}";

        Response response =
                RestAssured.given()
                        .spec(requestSpec)
                        .body(requestBody)
                        .when()
                        .post("/user/keys");

        response.prettyPrint();

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 201,
                "SSH key creation failed");

        Assert.assertEquals(response.jsonPath().getString("title"),
                "TestAPIKey");

        // Extract key id
        keyId = response.jsonPath().getInt("id");

        Assert.assertTrue(keyId > 0,
                "Generated key id should be greater than zero");

        Reporter.log("Created SSH Key ID: " + keyId, true);
    }

    @Test(priority = 2, dependsOnMethods = "addSSHKey")
    public void getSSHKey() {

        Response response =
                RestAssured.given()
                        .spec(requestSpec)
                        .pathParam("keyId", keyId)
                        .when()
                        .get("/user/keys/{keyId}");

        String responseBody = response.asPrettyString();

        System.out.println(responseBody);
        Reporter.log(responseBody, true);

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200,
                "Failed to retrieve SSH key");

        Assert.assertEquals(response.jsonPath().getInt("id"), keyId);

        Assert.assertEquals(response.jsonPath().getString("title"),
                "TestAPIKey");
    }

    @Test(priority = 3, dependsOnMethods = "getSSHKey")
    public void deleteSSHKey() {

        Response response =
                RestAssured.given()
                        .spec(requestSpec)
                        .pathParam("keyId", keyId)
                        .when()
                        .delete("/user/keys/{keyId}");

        Reporter.log("Delete Response Status: "
                + response.getStatusCode(), true);

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 204,
                "Failed to delete SSH key");
    }
}