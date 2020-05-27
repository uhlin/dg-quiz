package com.eq.house;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

//This will be AUTO IMPLEMENTED by Spring
//CRUD refers Create, Read, Update, Delete

public interface AnswerRepo extends CrudRepository<Answer, Long> {

	List<Answer> findByPlayerId(String playerId);

	void deleteByPlayerId(String playerId);

}
