package com.weather_API.Weather_Application_API.providers;

import com.weather_API.Weather_Application_API.domain.CityCooridanates;
import com.weather_API.Weather_Application_API.entity.OpenWeatherResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherProvider {

    @Value("${api.key}")
    private String apiKey;

    @Value("${weather.url}")
    private String weatherUrl;



    public OpenWeatherResponseEntity getWeather(final CityCooridanates cooridanates) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<OpenWeatherResponseEntity> responseEntity;

        HttpEntity<String> requestEntity = new HttpEntity<>(null, new HttpHeaders());

        UriComponents uriBuilder = UriComponentsBuilder.fromUriString(weatherUrl)
                .queryParam("lat", cooridanates.getLatitude())
                .queryParam("lon", cooridanates.getLongitude())
                .queryParam("appid", apiKey).build();

        try{
            responseEntity = restTemplate
                    .exchange(uriBuilder.
                                    toUriString(),
                            HttpMethod.GET,
                            requestEntity,
                            OpenWeatherResponseEntity.class);
        }catch (HttpStatusCodeException e){
            throw new Exception(e.getMessage());
        }

        return responseEntity.getBody();
    }
}
