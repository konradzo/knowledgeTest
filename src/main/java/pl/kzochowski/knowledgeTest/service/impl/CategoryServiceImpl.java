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
        Optional<Category> temp = categoryRepository.findByName(category.getName());
        if (temp.isPresent())
            throw new CategoryAlreadyExistsException(category.getName());

        categoryRepository.save(category);
        log.info("Category {} created", category.getName());
        return category;
    }

    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }
}
