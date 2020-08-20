package pl.kzochowski.knowledgeTest.model;

import lombok.*;

import java.util.List;

@Data
public class ExamList {
    private final long size;
    private final List<Exam> exams;
}
