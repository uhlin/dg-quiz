package com.eq.house;

import org.springframework.data.repository.CrudRepository;

//This will be AUTO IMPLEMENTED by Spring
//CRUD refers Create, Read, Update, Delete

public interface QuestionTextRepo extends CrudRepository<QuestionText, Long> {
	;
}
