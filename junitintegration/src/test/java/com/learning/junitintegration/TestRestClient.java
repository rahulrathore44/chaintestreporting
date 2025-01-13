package com.learning.junitintegration;

import com.aventstack.chaintest.plugins.ChainTestExecutionCallback;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.fluent.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@Tag("smoke")
@ExtendWith(ChainTestExecutionCallback.class)
public class TestRestClient {

    private static WireMockServer server;

    @BeforeAll
    public static void setUpServer() {
        server = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        server.start();
    }


    // 200 OK

    @Test
    public void testForSuccessFullResponse() throws IOException {
        server.stubFor(WireMock.get("/test").willReturn(WireMock.aResponse().withStatus(HttpStatus.SC_OK)));
        Response response = Request.get("http://localhost:" + server.port() + "/test").execute();
        Assertions.assertEquals(HttpStatus.SC_OK, response.returnResponse().getCode());

    }

    @Test
    public void failedTest() {
        //Assertions.fail("Failed for the reporting");
    }

    // 404
    // 500
    // 400

    @AfterAll
    public static void stopServer() {
        if (server != null && server.isRunning()) {
            server.stop();
        }
    }

}
