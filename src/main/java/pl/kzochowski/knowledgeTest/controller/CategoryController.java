package pl.kzochowski.knowledgeTest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.kzochowski.knowledgeTest.model.Category;
import pl.kzochowski.knowledgeTest.service.CategoryService;

import javax.validation.Valid;

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

}
