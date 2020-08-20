package pl.kzochowski.knowledgeTest.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.kzochowski.knowledgeTest.model.Category;
import pl.kzochowski.knowledgeTest.repository.CategoryRepository;
import pl.kzochowski.knowledgeTest.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        Optional<Category> result = categoryRepository.findByName(category.getName());
        result.ifPresent(tempCategory -> {
            throw new CategoryAlreadyExistsException(tempCategory.getName());
        });

        categoryRepository.save(category);
        log.info("Category {} created", category.getName());
        return category;
    }

    @Override
    public Category fetchCategoryByName(String categoryName) {
        Category category = checkCategoryExistence(categoryName);
        log.info("Fetched category: {}", categoryName);
        return category;
    }

    @Override
    public Category fetchCategoryById(Integer id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent())
            throw new CategoryDoesNotExistException(id);
        log.info("Fetched category: {}", category.get().getName());
        return category.get();
    }

    @Override
    public Category removeCategory(String categoryName) {
        Category category = checkCategoryExistence(categoryName);
        categoryRepository.deleteByName(categoryName);
        log.info("Category {} removed", categoryName);
        return category;
    }

    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategoriesByQuery(String query) {
        return categoryRepository.findByNameContainingIgnoreCase(query);
    }

    private Category checkCategoryExistence(String categoryName) {
        Optional<Category> tempCategory = categoryRepository.findByName(categoryName);
        if (!tempCategory.isPresent())
            throw new CategoryDoesNotExistException(categoryName);

        return tempCategory.get();
    }
}
