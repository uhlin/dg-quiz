package com.eq.house;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//This will be AUTO IMPLEMENTED by Spring
//CRUD refers Create, Read, Update, Delete

public interface QuestionImageRepo extends CrudRepository<QuestionImage, Long> {

	List<QuestionImage> findByQuizId(String quizId);

	void deleteByQuizId(String quizId);
	void deleteByQuizIdAndQuestionNum(String quizId, Integer questionNum);

}
