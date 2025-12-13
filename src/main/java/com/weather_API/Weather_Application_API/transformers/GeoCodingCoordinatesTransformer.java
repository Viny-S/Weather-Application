package com.weather_API.Weather_Application_API.transformers;

import com.weather_API.Weather_Application_API.domain.CityCooridanates;
import com.weather_API.Weather_Application_API.entity.GeoCodingCoordinatesEntity;
import org.springframework.stereotype.Service;

@Service
public class GeoCodingCoordinatesTransformer {
    public CityCooridanates transformToDomain(final GeoCodingCoordinatesEntity entity){
        return CityCooridanates.builder()
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();
    }
}
