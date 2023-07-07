package exercise.controller;

import exercise.dto.ArticleDto;
import exercise.exception.NoArticleFoundException;
import exercise.model.Article;
import exercise.model.Category;
import exercise.repository.ArticleRepository;

import exercise.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping(path = "")
    public Iterable<Article> getArticles() {
        return articleRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable long id) {
        articleRepository.deleteById(id);
    }

    // BEGIN
    @PostMapping(path = "")
    public void createArticle(@RequestBody ArticleDto articleDto) {
        Article article = new Article();
        Category category = categoryRepository.findById(articleDto.getCategory().getId());

        article.setName(articleDto.getName());
        article.setBody(articleDto.getBody());
        article.setCategory(category);

        articleRepository.save(article);
    }

    @PatchMapping(path = "/{id}")
    public void updateArticle(@RequestBody ArticleDto articleDto, @PathVariable Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            article.setName(articleDto.getName());
            article.setBody(articleDto.getBody());
            article.setCategory(categoryRepository.findById(articleDto.getCategory().getId()));
            articleRepository.save(article);
        } else {
            throw new NoArticleFoundException("No article with ID " + id + " found.");
        }
    }

    @GetMapping(path = "/{id}")
    public Article Article(@PathVariable Long id) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            return article;
        } else {
            throw new NoArticleFoundException("No article with ID " + id + " found.");
        }
    }
    // END
}
