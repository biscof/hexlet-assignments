package exercise.controller;

import exercise.dto.CommentDto;
import exercise.model.Comment;
import exercise.repository.CommentRepository;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    // BEGIN
    @GetMapping(path = "/{postId}/comments")
    public List<Comment> getAllCommentsForPost(@PathVariable Long postId) {
        return commentRepository.findAllByPostId(postId);
    }

    @GetMapping(path = "/{postId}/comments/{commentId}")
    public Comment getCommentForPost(@PathVariable Long postId, @PathVariable Long commentId) {
        return commentRepository
                .findCommentByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No comment with ID " + commentId + " was found for the post with ID " + postId)
                );
    }

    @PostMapping(path = "/{postId}/comments")
    public List<Comment> createCommentForPost(@PathVariable Long postId, @RequestBody CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setContent(commentDto.content());
        comment.setPost(postRepository
                .findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No post with ID " + postId + " was found")
                )
        );
        commentRepository.save(comment);

        return commentRepository.findAllByPostId(postId);
    }

    @PatchMapping(path = "/{postId}/comments/{commentId}")
    public void updateComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentDto commentDto) {
        Comment comment = commentRepository
                .findCommentByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No comment with ID " + commentId + " was found for the post with ID " + postId)
                );

        if (commentDto.content() != null) {
            comment.setContent(commentDto.content());
        }

        commentRepository.save(comment);
    }

    @DeleteMapping(path = "/{postId}/comments/{commentId}")
    public void deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        Comment comment = commentRepository
                .findCommentByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No comment with ID " + commentId + " was found for the post with ID " + postId)
                );

        commentRepository.delete(comment);
    }
    // END
}
