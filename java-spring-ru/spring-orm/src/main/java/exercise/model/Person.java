package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@Getter
@Setter
// END
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // BEGIN
    private String firstName;

    private String lastName;

    public Person() {}

//    public Person(String firstName, String lastName) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//    }
    // END
}
