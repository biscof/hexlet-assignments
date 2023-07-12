package exercise.controller;
import com.querydsl.core.types.dsl.BooleanExpression;
import exercise.model.QUser;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.service.SearchCriteria;
import exercise.service.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Зависимости для самостоятельной работы
 import org.springframework.data.querydsl.binding.QuerydslPredicate;
 import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserRepository userRepository;

    // BEGIN
    @GetMapping(path = "")
    public Iterable<User> getUsersByParams(
            @QuerydslPredicate(root = User.class) Predicate predicate,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "profession", required = false) String profession,
            @RequestParam(name = "gender", required = false) String gender
    ) {
        return userRepository.findAll(predicate);
    }
    // END
}

