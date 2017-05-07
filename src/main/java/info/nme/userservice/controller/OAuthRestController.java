package info.nme.userservice.controller;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by chakrabhandari on 7/05/2017.
 */

@RestController
public class OAuthRestController {

    @RequestMapping("/api/bearer")
    public Token getBearerCode(@RequestBody ClientDetails clientDetails) {

        RestTemplate restTemplate = new RestTemplate();

        String plainCreds = clientDetails.getClientId() + ":" + clientDetails.getSecret();
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String refreshUrl = "https://obscure-fjord-89635.herokuapp.com/api/oauth/token?username="+clientDetails.getUsername()
                +"&password="+clientDetails.getPassword()+"&grant_type="+clientDetails.getGrantType();

        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<Token> refreshResponse = restTemplate.exchange(refreshUrl, HttpMethod.GET, request, Token.class);

        Token refreshToken = refreshResponse.getBody();

        String bearerUrl = "https://obscure-fjord-89635.herokuapp.com/api/oauth/token?grant_type=refresh_token&refresh_token="+refreshToken.getRefresh_token();

        ResponseEntity<Token> bearerResponse = restTemplate.exchange(bearerUrl, HttpMethod.GET, request, Token.class);

        Token bearerToken = bearerResponse.getBody();

        return bearerToken; // bearer
    }

}
