package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public Iterable<Course> getAllPrerequisites(@PathVariable long id) {
        Course course = courseRepository.findById(id);
        String path = course.getPath();
        Iterable<Course> prerequisites = new ArrayList<>();

        if (path != null) {
            List<Long> ids = Stream.of(path.split("\\."))
                    .map(Long::parseLong)
                    .toList();
            prerequisites = (courseRepository.findAllById(ids));
        }
        return prerequisites;
    }
    // END

}
