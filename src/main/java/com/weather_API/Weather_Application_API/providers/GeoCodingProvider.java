package com.weather_API.Weather_Application_API.providers;


import com.weather_API.Weather_Application_API.domain.WeatherRequestDetails;
import com.weather_API.Weather_Application_API.entity.GeoCodingCoordinatesEntity;
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
public class GeoCodingProvider {
    @Value("${api.key}")
    private String apiKey;
    @Value("${geocoding.url}")
    private String geoCodingUrl;


    public GeoCodingCoordinatesEntity getCoordinates(final WeatherRequestDetails weatherRequestDetails) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<GeoCodingCoordinatesEntity[]> responseEntity;

        HttpEntity<String> requestEntity = new HttpEntity<>(null, new HttpHeaders());

        UriComponents uriBuilder = UriComponentsBuilder.fromUriString(geoCodingUrl)
                .queryParam("q",weatherRequestDetails.getCity())
                .queryParam("limit", "1")
                .queryParam("appid", apiKey).build();

        try{
            responseEntity = restTemplate
                    .exchange(uriBuilder.
                        toUriString(),
                        HttpMethod.GET,
                        requestEntity,
                        GeoCodingCoordinatesEntity[].class);
        }catch (HttpStatusCodeException e){
            throw new Exception(e.getMessage());
        }

        return responseEntity.getBody()[0];
    }
}
