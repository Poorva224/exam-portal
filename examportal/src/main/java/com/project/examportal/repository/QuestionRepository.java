package com.project.examportal.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.examportal.model.exam.Question;
import com.project.examportal.model.exam.Quiz;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	
	Set<Question> findByQuiz(Quiz quiz);
}
