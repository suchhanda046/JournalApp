package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.constants.Placeholders;
import net.engineeringdigest.journalApp.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class WeatherTrackerService {
    @Value("${weather.api.key}")
    private String weather_api_key;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AppCache appCache;

//    String url = "http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    public WeatherResponse getTemp(String city){
        String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(Placeholders.API_KEY,weather_api_key).replace(Placeholders.CITY,city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
        return response.getBody();
    }

}
