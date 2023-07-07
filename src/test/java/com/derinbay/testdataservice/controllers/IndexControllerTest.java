package com.derinbay.testdataservice.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class IndexControllerTest {

    private final HttpServletResponse response = mock(HttpServletResponse.class);
    private final IndexController indexController = new IndexController();

    @Test
    void testRedirectToSwaggerUi() {
        RedirectView result = indexController.redirectToSwaggerUi(response);
        assertEquals("/swagger-ui/index.html", result.getUrl());
        verify(response).setHeader(eq("Cache-Control"), eq("no-cache"));
    }

    @Test
    void testHealth() {
        ResponseEntity<HttpStatus> result = indexController.health();
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
