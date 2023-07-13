package exercise.repository;

import exercise.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    // BEGIN
    List<Comment> findAllByPostId(Long postId);
    Optional<Comment> findCommentByIdAndPostId(Long commentId, Long postId);
    // END
}
