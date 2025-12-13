package com.weather_API.Weather_Application_API.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class WeatherRequestDetails {
    private String city;
}
