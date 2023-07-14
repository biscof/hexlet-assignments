package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import exercise.HttpClient;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    private final ObjectMapper mapper = new ObjectMapper();

    public Map<String, String> getWeather(Long cityId) {
        String cityName = cityRepository
                .findById(cityId)
                .orElseThrow(() -> new CityNotFoundException("City with ID " + cityId + " doesn't exist"))
                .getName();

        return this.getWeatherAsMap(cityName);
    }

    public Map<String, String> getShortWeather(String cityName) {
        Map<String, String> weather = this.getWeatherAsMap(cityName);
        return Map.of(
                "temperature", weather.get("temperature"),
                "name", weather.get("name")
        );
    }

    private Map<String, String> getWeatherAsMap(String cityName) {
        String weatherStr = client.get(String.format("http://weather/api/v2/cities/%s", cityName));
        try {
            return mapper.readValue(weatherStr, new TypeReference<HashMap<String, String>>() {});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // END
}
