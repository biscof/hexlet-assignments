package exercise.controllers;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.DB;
import io.javalin.apibuilder.CrudHandler;
import io.javalin.http.Context;
import io.javalin.validation.ValidationError;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class UserController implements CrudHandler {

    public void getAll(Context ctx) {
        // BEGIN
        List<User> users = new QUser()
                .orderBy()
                    .id.asc()
                .findList();

        String usersJson = DB.json().toJson(users);
        ctx.json(usersJson);
        // END
    };

    public void getOne(Context ctx, String id) {

        // BEGIN
        User user = new QUser()
                .id.eq(Integer.parseInt(id))
                .findOne();

        String userJson = DB.json().toJson(user);
        ctx.json(userJson);
        // END
    };

    public void create(Context ctx) {

        // BEGIN
        User user = ctx.bodyValidator(User.class)
                .check(u -> !u.getFirstName().isEmpty(), "Необходимо ввести имя")
                .check(u -> !u.getLastName().isEmpty(), "Необходимо ввести фамилию")
                .check(u -> EmailValidator.getInstance().isValid(u.getEmail()), "Введен некорректный email")
                .check(u -> u.getPassword().length() >= 8, "Минимальная длина пароля - 8 символов")
                .get();

        user.save();
        // END
    };

    public void update(Context ctx, String id) {
        // BEGIN
        String userJson = ctx.body();
        User user = DB.json().toBean(User.class, userJson);
        user.setId(id);
        user.update();
        // END
    };

    public void delete(Context ctx, String id) {
        // BEGIN
        User user = new QUser()
                .id.equalTo(Integer.parseInt(id))
                .findOne();

        if (user == null) {
            ctx.status(404);
        } else {
            user.delete();
        }
        // END
    };
}
