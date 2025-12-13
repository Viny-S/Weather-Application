package com.weather_API.Weather_Application_API.transformers;

import com.weather_API.Weather_Application_API.domain.CityWeather;
import com.weather_API.Weather_Application_API.entity.OpenWeatherResponseEntity;
import com.weather_API.Weather_Application_API.entity.WeatherResponse;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherTransformer {

    public CityWeather transformToDomain(final OpenWeatherResponseEntity entity){
        return CityWeather.builder()
                .weather(entity.getWeather()[0].getMain())
                .details(entity.getWeather()[0].getDescription())
                .build();
    }

    public WeatherResponse transformToEntity(final CityWeather cityWeather){
        return WeatherResponse.builder()
                .weather(cityWeather.getWeather())
                .details(cityWeather.getDetails())
                .build();
    }
}
