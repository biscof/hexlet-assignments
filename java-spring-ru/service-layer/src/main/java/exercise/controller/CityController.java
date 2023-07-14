package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public Map<String, String> getWeatherByCityId(
            @PathVariable(name = "id") Long cityId) throws JsonProcessingException
    {
        return weatherService.getWeather(cityId);
    }

    @GetMapping(path = "/search")
    public Iterable<Map<String, String>> getAllCitiesWhichStartWith(
            @RequestParam(name = "name", required = false) String nameStartsWith
    ) {

        List<City> cities = nameStartsWith == null
                ? cityRepository.findAllByOrderByNameAsc()
                : cityRepository.findCitiesByNameIgnoreCaseStartsWith(nameStartsWith);

        return cities.stream()
                .map(c -> weatherService.getShortWeather(c.getName()))
                .toList();
    }
    // END
}
