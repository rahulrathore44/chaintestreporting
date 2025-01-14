package com.learning.testngintegration;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

//@Listeners(ChainTestListener.class)
public class TestRestClient {

    private static WireMockServer server;

    @BeforeClass
    public static void setUpServer() {
        server = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        server.start();
    }


    // 200 OK

    @Test
    public void testForSuccessFullResponse() throws IOException {
        server.stubFor(WireMock.get("/test").willReturn(WireMock.aResponse().withStatus(HttpStatus.SC_OK)));
        Response response = Request.get("http://localhost:" + server.port() + "/test").execute();
        Assert.assertEquals(response.returnResponse().getCode(), HttpStatus.SC_OK);
    }

    @Test
    public void failedTest() {
        //Assert.fail("Failed for the reporting");
    }

    // 404
    // 500
    // 400

    @AfterClass
    public static void stopServer() {
        if (server != null && server.isRunning()) {
            server.stop();
        }
    }

}
