package exercise.controller;
import com.querydsl.core.types.dsl.BooleanExpression;
import exercise.model.QUser;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.service.SearchCriteria;
import exercise.service.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
    public Iterable<User> getUsersByParams(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "lastName", required = false) String lastName
    ) {

        BooleanExpression predicate = null;

        if (firstName != null) {
            predicate = QUser.user.firstName.lower().contains(firstName.toLowerCase());
        }

        if (lastName != null) {
            predicate = predicate != null
                    ? predicate.and(QUser.user.lastName.lower().contains(lastName.toLowerCase()))
                    : QUser.user.lastName.lower().contains(lastName.toLowerCase());
        }

        if (gender != null) {
            predicate = predicate != null
                    ? predicate.and(QUser.user.gender.lower().eq(gender.toLowerCase()))
                    : QUser.user.gender.lower().eq(gender.toLowerCase());
        }

        return predicate == null ? new ArrayList<>() : userRepository.findAll(predicate);
    }
    // END
}

