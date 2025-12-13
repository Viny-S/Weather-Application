package com.weather_API.Weather_Application_API.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OpenWeatherResponseEntity {
    @JsonProperty("weather")
    private WeatherEntity[] weather;
}
