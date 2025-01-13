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
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

@Tags({@Tag("regression"), @Tag("integration")})
@ExtendWith(ChainTestExecutionCallback.class)
public class TestRestClientErrorCase {

    private static WireMockServer server;

    @BeforeAll
    public static void setUpServer() {
        server = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        server.start();
    }


    // 404

    @Test
    public void testForNotFoundResponse() throws IOException {
        server.stubFor(WireMock.get("/test").willReturn(WireMock.aResponse().withStatus(HttpStatus.SC_NOT_FOUND)));
        Response response = Request.get("http://localhost:" + server.port() + "/test").execute();
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.returnResponse().getCode());
    }


    // 500

    @Test
    public void testForInternalServerErrorResponse() throws IOException {
        server.stubFor(WireMock.get("/test").willReturn(WireMock.aResponse().withStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR)));
        Response response = Request.get("http://localhost:" + server.port() + "/test").execute();
        Assertions.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.returnResponse().getCode());
    }

    // 400

    @Test
    public void testForBadRequestResponse() throws IOException {
        server.stubFor(WireMock.get("/test").willReturn(WireMock.aResponse().withStatus(HttpStatus.SC_BAD_REQUEST)));
        Response response = Request.get("http://localhost:" + server.port() + "/test").execute();
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.returnResponse().getCode());
    }

    @AfterAll
    public static void stopServer() {
        if (server != null && server.isRunning()) {
            server.stop();
        }
    }

}
