package pl.kzochowski.knowledgeTest.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kzochowski.knowledgeTest.model.Category;
import pl.kzochowski.knowledgeTest.model.CategoryList;
import pl.kzochowski.knowledgeTest.service.CategoryService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryEndpoint {
    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody @Valid Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Category fetch(@PathVariable Integer id) {
        return categoryService.fetchCategoryById(id);
    }

    @DeleteMapping("/{categoryName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Category remove(@PathVariable("categoryName") String categoryName) {
        return categoryService.removeCategory(categoryName);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAll() {
        categoryService.removeAllCategories();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryList listAll() {
        return categoryService.listAllCategories();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public CategoryList searchByName(@RequestParam String query) {
        return categoryService.searchCategoriesByQuery(query);
    }
}
