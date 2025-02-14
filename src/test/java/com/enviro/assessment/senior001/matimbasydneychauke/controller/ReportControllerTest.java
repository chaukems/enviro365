package com.enviro.assessment.senior001.matimbasydneychauke.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration
@AutoConfigureMockMvc
@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${auth.jwt.secret}")
    private String secret;

    @Value("${auth.jwt.username}")
    private String username;

    @Autowired
    private MockMvc mockMvc;

    private final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


    @Test
    public void unauthorized_throws401() throws Exception {
        mockMvc.perform(get("/report-service/1234")
                        .accept(MediaType.APPLICATION_JSON)
                        .accept(objectMapper.writeValueAsString("")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void execute_successfully() throws Exception {
        mockMvc.perform(post("/report-service")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", format("Bearer %s", generateToken()))
                        .content(objectMapper.writeValueAsString("{}")))
                .andExpect(status().isOk());
    }


    private String generateToken() throws Exception {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 1000))
                .signWith(getSigningKey()).compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
