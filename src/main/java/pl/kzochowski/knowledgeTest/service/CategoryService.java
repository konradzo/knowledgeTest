package pl.kzochowski.knowledgeTest.service;

import pl.kzochowski.knowledgeTest.model.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> listAllCategories();

    class CategoryAlreadyExistsException extends RuntimeException {
        public CategoryAlreadyExistsException(String categoryName) {
            super(String.format("Category with name %s already exists!", categoryName));
        }
    }
}
