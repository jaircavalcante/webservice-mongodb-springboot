package br.unifacisa.tap.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.unifacisa.tap.entity.User;

/**
 * 
 * @author jaircavalcante
 *
 */
public interface UserRepository extends MongoRepository<User, String> {

	public User findByName(String name);	
	
	
}
