package pl.kzochowski.knowledgeTest.model;

import lombok.Data;

import java.util.List;

@Data
public class ExamApproachList {
    private final long size;
    private final List<ExamApproach> examApproaches;
}
