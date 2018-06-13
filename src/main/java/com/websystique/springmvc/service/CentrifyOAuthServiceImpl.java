package com.websystique.springmvc.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;

@Service("centrifyService")
public class CentrifyOAuthServiceImpl {

    public boolean authenticate(String userName, String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = getHeaders(userName, password);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("https://eotss-dev.my.centrify.com/oauth2/token/Tiger_TestOAuth", request, String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
        String accessToken = null;
        if (statusCode == HttpStatus.ACCEPTED) {
            String responseString = responseEntity.getBody();
            System.out.println("OAuth2SecurityConfiguration.globalUserDetails " + responseString);
           /* try {
                JSONObject response = new JSONObject(responseString);
                if (response.has("access_token") && response.get("access_token") != null) {
                    accessToken = response.getString("access_token");
                }
            } catch (Exception e) {

            }*/
        }
        if (accessToken != null) {
            System.out.println("OAuth2SecurityConfiguration.globalUserDetails, got  accessToken" + accessToken);
            return true;
        } else {
            System.out.println("OAuth2SecurityConfiguration.globalUserDetails, didn't get  accessToken");
            return false;
        }
    }

    private static HttpHeaders getHeaders(String userName, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        try {
            byte[] message = (userName + ":" + password).getBytes("UTF-8");
            String base64ClientCredentials = DatatypeConverter.printBase64Binary(message);
            headers.add("Authorization", "Basic " + base64ClientCredentials);
        } catch (Exception e) {

        }

        return headers;
    }

}
