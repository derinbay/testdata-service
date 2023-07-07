package com.derinbay.testdataservice.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class IndexController {
    @Hidden
    @RequestMapping("/")
    public RedirectView redirectToSwaggerUi(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        return new RedirectView("/swagger-ui/index.html");
    }

    @GetMapping(value = "/_monitoring/health")
    public ResponseEntity<HttpStatus> health() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
