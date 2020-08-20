package pl.kzochowski.knowledgeTest.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.kzochowski.knowledgeTest.KnowledgeTestApplication;
import pl.kzochowski.knowledgeTest.model.Category;
import pl.kzochowski.knowledgeTest.model.Exam;
import pl.kzochowski.knowledgeTest.model.ExamApproach;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Transactional
@SpringBootTest(classes = KnowledgeTestApplication.class)
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

    private Category tempCategory(){
        Category category = new Category();
        category.setId(1);
        category.setDescription("Blabl");
        category.setName("Some name");
        List<Exam> exams = new ArrayList<>();
        List<ExamApproach> examApproaches = new ArrayList<>();
        category.setExamList(exams);
        category.setExamApproachList(examApproaches);
        return category;
    }
}
