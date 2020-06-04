package com.eq.house;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//This will be AUTO IMPLEMENTED by Spring
//CRUD refers Create, Read, Update, Delete

public interface QuestionSoundRepo extends CrudRepository<QuestionSound, Long> {

	List<QuestionSound> findByQuizId(String quizId);

	void deleteByQuizId(String quizId);

}
