package com.yulece.app.management.zuul.controller;

import com.yulece.app.management.zuul.properties.ZuulProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
public class WebRestController {

    @Autowired
    private ZuulProperties zuulProperties;

    @GetMapping("/me")
    public  Map<String, Object> getCurrentUser(Authentication user, HttpServletRequest request) throws UnsupportedEncodingException {
        String header = request.getHeader("Authorization");
        String toke = StringUtils.substringAfter(header, "bearer ");

        Map<String, Object> jwtClaims =
                Jwts.parser().setSigningKey(zuulProperties.getOauth().getoAuth2SigningKey().getBytes("UTF-8")).parseClaimsJws(toke).getBody();
        return jwtClaims;

    }
}
