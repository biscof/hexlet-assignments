package exercise.repository;

import com.querydsl.core.types.dsl.StringExpression;
import exercise.model.QUser;
import exercise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

// BEGIN
@Repository
public interface UserRepository
        extends JpaRepository<User, Long>,
        QuerydslPredicateExecutor<User>,
        QuerydslBinderCustomizer<QUser> {

    @Override
    default void customize(QuerydslBindings bindings, QUser user) {
        bindings.bind(user.firstName).first(((path, value) -> path.containsIgnoreCase(value)));
        bindings.bind(user.lastName).first(((path, value) -> path.containsIgnoreCase(value)));
        bindings.bind(user.email).first((StringExpression::containsIgnoreCase));
        bindings.bind(user.profession).first(((path, value) -> path.containsIgnoreCase(value)));
        bindings.bind(user.gender).first(((path, value) -> path.equalsIgnoreCase(value)));
    }
}
// END
