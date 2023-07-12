package exercise.controller;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.service.SearchCriteria;
import exercise.service.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserRepository userRepository;

    // BEGIN
    @GetMapping(path = "")
    public List<User> getUsersByParams(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "lastName", required = false) String lastName
            ) {

        List<Specification<User>> specifications = new ArrayList<>();

        if (firstName != null) {
            specifications.add(new UserSpecification(new SearchCriteria<String>("firstName", firstName.toLowerCase())));
        }

        if (lastName != null) {
            specifications.add(new UserSpecification(new SearchCriteria<String>("lastName", lastName.toLowerCase())));
        }

        if (gender != null) {
            specifications.add(new UserSpecification(new SearchCriteria<String>("gender", gender.toLowerCase())));
        }

        Specification<User> accumulatedSpecification = specifications.stream()
                .reduce(null, (accumulatedSpec, specification) -> {
                    if (accumulatedSpec == null) {
                        return specification;
                    }
                    return accumulatedSpec.and(specification);
                });

        return userRepository.findAll(accumulatedSpecification);
    }
    // END
}

