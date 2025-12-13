package com.weather_API.Weather_Application_API.entity;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {
    private String weather;
    private String details;
}
