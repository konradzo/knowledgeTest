package pl.kzochowski.knowledgeTest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kzochowski.knowledgeTest.model.Category;
import pl.kzochowski.knowledgeTest.model.CategoryList;
import pl.kzochowski.knowledgeTest.service.CategoryService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Category createCategory(@RequestBody @Valid Category category) {
        return categoryService.createCategory(category);
    }

    //todo get by id
    // todo find by category name - another endpoint?

    @GetMapping("/{categoryName}")
    @ResponseStatus(HttpStatus.OK)
    Category fetchCategory(@PathVariable("categoryName") String categoryName) {
        return categoryService.fetchCategoryByName(categoryName);
    }

    @DeleteMapping("/{categoryName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Category removeCategory(@PathVariable("categoryName") String categoryName) {
        return categoryService.removeCategory(categoryName);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    CategoryList listCategories() {
        List<Category> categories = categoryService.listAllCategories();
        log.info("Categories list size: {}", categories.size());
        return new CategoryList(categories);
    }

}
