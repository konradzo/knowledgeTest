package pl.kzochowski.knowledgeTest.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import pl.kzochowski.knowledgeTest.KnowledgeTestApplication;
import pl.kzochowski.knowledgeTest.model.Category;
import pl.kzochowski.knowledgeTest.model.Exam;
import pl.kzochowski.knowledgeTest.model.ExamApproach;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest(classes = KnowledgeTestApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void shouldReturnCategoryByName() {
        // given
        Category category = tempCategory();

        // when
        categoryService.createCategory(category);
        Category result = categoryService.fetchCategoryByName(category.getName());

        // then
        assertThat(category, equalTo(result));
    }

    @Test
    public void shouldThrownExceptionWhenCategoryNotFoundByName() {
        // then
        assertThrows(CategoryService.CategoryDoesNotExistException.class, () -> categoryService.fetchCategoryByName("Test name"));
    }

    @Test
    public void shouldReturnCategoryById() {
        // given
        Category category = tempCategory();

        // when
        categoryService.createCategory(category);
        Category result = categoryService.fetchCategoryById(1);

        // then
        assertThat(category, equalTo(result));
    }

    @Test
    public void shouldThrownExceptionWhenCategoryNotFoundById() {
        // then
        assertThrows(CategoryService.CategoryDoesNotExistException.class, () -> categoryService.fetchCategoryById(1));
    }

    @Test
    public void shouldRemovedCategory() {
        // given
        Category category = tempCategory();

        // when
        categoryService.createCategory(category);
        Category result = categoryService.removeCategory(category.getName());

        // then
        assertThat(category, equalTo(result));
        assertThrows(CategoryService.CategoryDoesNotExistException.class, () -> categoryService.fetchCategoryByName(category.getName()));

    }

    private Category tempCategory() {
        Category category = new Category();
        category.setId(1);
        category.setDescription("Sample category's description");
        category.setName("Some name");
        List<Exam> exams = new ArrayList<>();
        List<ExamApproach> examApproaches = new ArrayList<>();
        category.setExamList(exams);
        category.setExamApproachList(examApproaches);
        return category;
    }
}
