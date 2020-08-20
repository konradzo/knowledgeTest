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
public class CategoryEndpoint {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Category createCategory(@RequestBody @Valid Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Category fetchCategory(@PathVariable Integer id) {
        return categoryService.fetchCategoryById(id);
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

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    CategoryList searchCategoriesByName(@RequestParam String query){
        List<Category> categories = categoryService.searchCategoriesByQuery(query);
        log.info("Categories list size: {}", categories.size());
        return new CategoryList(categories);
    }
}
