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
    Category create(@RequestBody @Valid Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Category fetch(@PathVariable Integer id) {
        return categoryService.fetchCategoryById(id);
    }

    @DeleteMapping("/{categoryName}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    Category remove(@PathVariable("categoryName") String categoryName) {
        return categoryService.removeCategory(categoryName);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    CategoryList listAll() {
        return categoryService.listAllCategories();
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    CategoryList searchByName(@RequestParam String query){
        return categoryService.searchCategoriesByQuery(query);
    }
}
