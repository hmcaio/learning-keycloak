package com.example.oauth2resourceserver.controller;

import com.example.oauth2resourceserver.dto.CookieDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class CookieController {

    private final List<CookieDto> cookies = new ArrayList<>();

    @PostMapping("/cookie")
    public ResponseEntity<CookieDto> createCookie(
            Principal principal,
            Authentication authentication,
            JwtAuthenticationToken jwtAuthenticationToken,
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody CookieDto cookieDto) {

        cookies.add(cookieDto);

        log.debug("SecurityContextHolder.getContext().getAuthentication()={}", SecurityContextHolder.getContext().getAuthentication());
        log.debug("principal={}", principal);
        log.debug("authentication={}", authentication);
        log.debug("jwtAuthenticationToken={}", jwtAuthenticationToken);
        log.debug("JWT={}", jwtAuthenticationToken.getPrincipal());
        log.debug("userDetails={}", userDetails);

        log.debug("User {} added cookie {}", jwtAuthenticationToken.getName(), cookieDto);

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
