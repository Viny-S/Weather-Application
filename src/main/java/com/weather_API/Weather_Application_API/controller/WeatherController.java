package com.weather_API.Weather_Application_API.controller;

import com.weather_API.Weather_Application_API.domain.WeatherRequestDetails;
import com.weather_API.Weather_Application_API.entity.WeatherResponse;
import com.weather_API.Weather_Application_API.service.WeatherService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    final private WeatherService weatherService;


    public WeatherController(final WeatherService weatherService){
        this.weatherService = weatherService;
    }


    @GetMapping("/{city}")
    public WeatherResponse weatherResponse(@PathVariable("city") String city) throws Exception {
        final WeatherRequestDetails weatherRequestDetails = WeatherRequestDetails.builder()
                .city(city)
                .build();
        return weatherService.getWeather(weatherRequestDetails);
    }
}
