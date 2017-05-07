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
    public String getBearerCode(@RequestBody ClientDetails clientDetails) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String str = clientDetails.getClientId() + ":" + clientDetails.getSecret();

        byte[]   bytesEncoded = Base64.encodeBase64(str .getBytes());
        System.out.println("ecncoded value is " + new String(bytesEncoded ));

        headers.set("Authorization", "Basic " + bytesEncoded);

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<JSONWrappedObject> result = restTemplate.exchange(
                "https://obscure-fjord-89635.herokuapp.com/api/oauth/token?username="+clientDetails.getUsername()
                        +"&password="+clientDetails.getPassword()+"&grant_type="+clientDetails.getGrantType(),
                HttpMethod.GET, null, JSONWrappedObject.class);

        System.out.println("body " + result.getBody());

        return "200";
    }

}
