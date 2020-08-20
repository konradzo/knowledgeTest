package pl.kzochowski.knowledgeTest.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class CategoryList {
    private List<Category> categories;

    public CategoryList(){}

    public CategoryList(List<Category> categories) {
        this.categories = categories;
    }

}
