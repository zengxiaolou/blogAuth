package com.ruler.auth.infrastructure.Captcha;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class GoogleRecaptchaService {
    @Value("${capthca.secretkey}")
    private String secretKey;
    @Value("${capthca.verifyUrl}")
    private String verifyUrl;

    public boolean verify(String token) {
        try {
           String url = verifyUrl, params = "secret=" + secretKey + "&response=" + token;
            HttpURLConnection http =(HttpURLConnection) new URL(url+ "?" + params).openConnection();
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/json");
            http.setDoOutput(true);
            int responseCode = http.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String jsonResponse = new BufferedReader(new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
                JSONObject jsonObject = new JSONObject(jsonResponse);
                return jsonObject.getBoolean("success");
            }else {
                System.out.println("reCAPTCHA request failed with response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
