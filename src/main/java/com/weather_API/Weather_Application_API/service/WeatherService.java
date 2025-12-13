package com.weather_API.Weather_Application_API.service;

import com.weather_API.Weather_Application_API.domain.CityCooridanates;
import com.weather_API.Weather_Application_API.domain.CityWeather;
import com.weather_API.Weather_Application_API.domain.WeatherRequestDetails;
import com.weather_API.Weather_Application_API.entity.WeatherResponse;
import com.weather_API.Weather_Application_API.providers.GeoCodingProvider;
import com.weather_API.Weather_Application_API.providers.WeatherProvider;
import com.weather_API.Weather_Application_API.transformers.GeoCodingCoordinatesTransformer;
import com.weather_API.Weather_Application_API.transformers.OpenWeatherTransformer;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    final private GeoCodingProvider geoCodingProvider;
    final private GeoCodingCoordinatesTransformer geoCodingCoordinatesTransformer;
    final private WeatherProvider weatherProvider;
    final private OpenWeatherTransformer openWeatherTransformer;

    public WeatherService(GeoCodingProvider geoCodingProvider,
                          GeoCodingCoordinatesTransformer geoCodingCoordinatesTransformer,
                          WeatherProvider weatherProvider,
                          OpenWeatherTransformer openWeatherTransformer){
        this.geoCodingProvider = geoCodingProvider;
        this.geoCodingCoordinatesTransformer = geoCodingCoordinatesTransformer;
        this.weatherProvider = weatherProvider;
        this.openWeatherTransformer = openWeatherTransformer;
    }

    public WeatherResponse getWeather(final WeatherRequestDetails weatherRequestDetails) throws Exception {

        final CityCooridanates cityCooridanates = geoCodingCoordinatesTransformer
                .transformToDomain(
                        geoCodingProvider.getCoordinates(weatherRequestDetails));

        final CityWeather cityWeather = openWeatherTransformer
                .transformToDomain(
                        weatherProvider.getWeather(cityCooridanates));


        return openWeatherTransformer.transformToEntity(cityWeather);
    }
}
