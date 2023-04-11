package exercise;

import java.lang.reflect.Field;
import java.util.*;

// BEGIN
public class Validator {
    public static List<String> validate(Object o) {
        List<String> nullFields = new ArrayList<>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                     if (field.get(o) == null) {
                        nullFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    System.out.println(e. getMessage());
                }
            }
        }
        return nullFields;
    }

    public static Map<String, List<String>> advancedValidate(Object o) {
        Map<String, List<String>> exceptions = new TreeMap<>();
        List<String> nulls = validate(o);

        nulls.stream()
                .forEach(i -> exceptions.put(i, new ArrayList<>(List.of("can not be null"))));

        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MinLength.class)) {
                MinLength len = field.getAnnotation(MinLength.class);
                field.setAccessible(true);
                try {
                    String fieldValue = (String) field.get(o);
                    if (fieldValue.length() < len.minLength()) {
                        if (exceptions.containsKey(field.getName())) {
                            exceptions.get(field.getName()).add(String.format("length less than %d", len.minLength()));
                        } else {
                            exceptions.put(
                                    field.getName(),
                                    new ArrayList<>(List.of(String.format("length less than %d", len.minLength())))
                            );
                        }
                    }
                } catch (IllegalAccessException e) {
                    System.out.println(e. getMessage());
                }
            }
        }
        return exceptions;
    }
}
// END
