package pl.kzochowski.knowledgeTest.model;

import lombok.*;

import java.util.List;

@Data
public class CategoryList {
    private final long size;
    private final List<Category> categories;
}
