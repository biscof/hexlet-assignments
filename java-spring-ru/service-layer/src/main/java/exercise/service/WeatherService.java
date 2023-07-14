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

    public Map<String, String> getWeather(Long cityId) throws JsonProcessingException {
        String cityName = cityRepository
                .findById(cityId)
                .orElseThrow(() -> new CityNotFoundException("City with ID " + cityId + " doesn't exist"))
                .getName();

        return this.getWeatherAsMap(cityName);
    }

    public Map<String, String> getShortWeather(String cityName) throws JsonProcessingException {
        Map<String, String> weather = this.getWeatherAsMap(cityName);
        Map<String, String> shortWeather = new HashMap<>();
        shortWeather.put("temperature", weather.get("temperature"));
        shortWeather.put("name", weather.get("name"));

        return shortWeather;
    }

    private Map<String, String> getWeatherAsMap(String cityName) throws JsonProcessingException {
        String weatherStr = client.get(String.format("http://weather/api/v2/cities/%s", cityName));
        return mapper.readValue(weatherStr, new TypeReference<HashMap<String, String>>() {});
    }
    // END
}
