package com.devonfw.mts.cucumber.api;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.devonfw.mts.cucumber.data.MailInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.FatalStartupException;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import jakarta.annotation.PreDestroy;

@Component
public class MailMockServer {
    private static final Logger LOG = LoggerFactory.getLogger(MailMockServer.class);

    private WireMockServer wireMockServer;
    private final String mailserviceUrl;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MailMockServer(@Value("${mailservice.url:http://localhost:8088}") String mailserviceUrl) {
        this.mailserviceUrl = mailserviceUrl;
        try {
            initialize();
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Wiremock initialization failed", e);
        }
    }

    public void initialize() throws MalformedURLException {
        URL url = new URL(mailserviceUrl);
        wireMockServer = new WireMockServer(wireMockConfig().port(url.getPort()));
        try {
            wireMockServer.start();
        } catch (FatalStartupException exc) {
            LOG.warn("Wiremock already running");
            LOG.trace("Cannot start Wiremock", exc);

        }
        WireMock.configureFor(url.getHost(), url.getPort());
    }

    public void mockEmailSuccess() {
        WireMock.stubFor(post(urlEqualTo("/mail"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)));
    }

    public List<MailInfo> getEmails(String email) throws IOException {
        List<LoggedRequest> requests = wireMockServer.findAll(RequestPatternBuilder.allRequests());
        List<MailInfo> mails = new ArrayList<>();

        for (LoggedRequest request : requests) {
            MailInfo info = objectMapper.readValue(request.getBody(), MailInfo.class);
            if (email.equals(info.getRecipient())) {
                mails.add(info);
            }
        }

        return mails;
    }

    public void reset() {
        WireMock.reset();
    }

    @PreDestroy
    public void shutdown() {
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }
}
