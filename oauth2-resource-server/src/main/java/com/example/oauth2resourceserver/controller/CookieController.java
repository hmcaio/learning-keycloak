package com.example.oauth2resourceserver.controller;

import com.example.oauth2resourceserver.dto.CookieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class CookieController {

    private final List<CookieDto> cookies = new ArrayList<>();

    @PostMapping("/cookie")
    public ResponseEntity<CookieDto> createCookie(@RequestBody CookieDto cookieDto) {
        cookies.add(cookieDto);
        log.debug("Added cookie {}", cookieDto);
        return ResponseEntity.ok(cookieDto);
    }

    @GetMapping("/cookies")
    public ResponseEntity<List<CookieDto>> getCookie() {
        return ResponseEntity.ok(cookies);
    }

    @DeleteMapping("/cookies")
    public ResponseEntity<Void> deleteAllCookies() {
        cookies.clear();
        return ResponseEntity.ok(null);
    }
}
