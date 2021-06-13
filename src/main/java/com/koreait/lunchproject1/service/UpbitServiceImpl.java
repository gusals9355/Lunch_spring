package com.koreait.lunchproject1.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service;
import sun.net.www.http.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.UUID;

@Service
public class UpbitServiceImpl implements UpbitService{
    String accessKey = "6uYt9UHKbM8e6kPi7hdSC519ARiqG8mx0Ca1qotx";
    String secretKey = "KGNFMmXdXvE6224VWRotZGQGLiD1GzA7xrpDXBuZ";
    String serverUrl = "https://api.upbit.com";
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    String jwtToken = JWT.create()
            .withClaim("access_key", accessKey)
            .withClaim("nonce", UUID.randomUUID().toString())
            .sign(algorithm);

    String authenticationToken = "Bearer " + jwtToken;

    @Override
    public String GetAccounts() {

        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            System.out.println(EntityUtils.toString(entity, "UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
