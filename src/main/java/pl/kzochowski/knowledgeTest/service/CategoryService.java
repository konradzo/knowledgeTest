package pl.kzochowski.knowledgeTest.service;

import pl.kzochowski.knowledgeTest.model.Category;
import pl.kzochowski.knowledgeTest.model.CategoryList;

public interface CategoryService {

    Category createCategory(Category category);

    Category fetchCategoryByName(String categoryName);

    Category fetchCategoryById(Integer id);

    Category removeCategory(String categoryName);

    void removeAllCategories();

    CategoryList listAllCategories();

    CategoryList searchCategoriesByQuery(String query);

    class CategoryAlreadyExistsException extends RuntimeException {
        public CategoryAlreadyExistsException(String categoryName) {
            super(String.format("Category with name %s already exists!", categoryName));
        }
    }

    class CategoryDoesNotExistException extends RuntimeException {
        public CategoryDoesNotExistException(String categoryName) {
            super(String.format("Category with name %s does not exist!", categoryName));
        }

        public CategoryDoesNotExistException(Integer id) {
            super(String.format("Category with id %d does not exist", id));
        }
    }
}
