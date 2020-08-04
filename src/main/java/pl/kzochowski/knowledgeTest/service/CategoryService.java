package pl.kzochowski.knowledgeTest.service;

import pl.kzochowski.knowledgeTest.model.Category;

public interface CategoryService {

    Category createCategory(Category category);

    class CategoryAlreadyExistsException extends RuntimeException {
        public CategoryAlreadyExistsException(String categoryName) {
            super(String.format("Category with name %s already exists!", categoryName));
        }
    }
}
