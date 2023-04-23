package com.example.demo.survey.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Survey {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private int type; // DTO 에서 Integer로 받고 String으로 DB 저장
    private String description;
    @OneToMany(mappedBy = "survey_id", fetch = FetchType.LAZY)
    // todo 주관식, 찬부식일 경우 null 에러 무시
    private List<Question> questionList;

    @Builder
    public Survey(Long id, String title, int type, String description, List<Question> questionList) {
        this.title = title;
        this.type = type;
        this.description = description;
        this.questionList = questionList;
    }

    // 문항 list 에 넣어주기
    public void setQuestion(Question question) {
        this.questionList.add(question);
    }
}
