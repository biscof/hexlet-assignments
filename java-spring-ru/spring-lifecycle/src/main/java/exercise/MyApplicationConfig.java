package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Daytime;
import exercise.daytimes.Morning;
import exercise.daytimes.Day;
import exercise.daytimes.Evening;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class MyApplicationConfig {

    @Bean
    public Daytime getDaytime() {
        int currentHour = LocalDateTime.now().getHour();;
        if (currentHour < 6) {
            return new Night();
        } else if (currentHour < 12) {
            return new Morning();
        } else if (currentHour < 18) {
            return new Day();
        } else if (currentHour < 23) {
            return new Evening();
        } else {
            return new Night();
        }
    }
}
// END
