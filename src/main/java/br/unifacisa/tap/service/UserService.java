package br.unifacisa.tap.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.mongodb.WriteResult;

import br.unifacisa.tap.entity.User;
import br.unifacisa.tap.repository.UserRepository;

/**
 * 
 * @author jaircavalcante
 *
 */

@Service
@Validated
public class UserService {

    @Autowired
    private UserRepository repository;

    public User getById(String id) {
    	return repository.findOne(id);
    }
    
	public List<User> listAllUsers() {
		return repository.findAll();
	}
	
    @Transactional
    public User save(@NotNull User usuario) {
    	
        User existing = repository.findByName(usuario.getName());

        if(existing == null)
        	existing = repository.save(usuario);
        
        return existing;
    }

    @Transactional
    public void delete(@NotNull User usuario) {
    	User existing = repository.findOne(usuario.getId());
    	if(existing != null) {
    		repository.delete(usuario);
    	}
    }
    
    @Transactional
    public User update(@NotNull User usuario) {
    	User existing = repository.findOne(usuario.getId());
    	if(existing != null) {
    		repository.delete(usuario);
    		existing = repository.save(usuario);
    	}
    	
    	return existing;
    }
    
    
    MongoTemplate mongoTemplate;
    
    /**
	 * Updates a {@link Tree} name for a particular id.
	 */
    @Transactional
	public WriteResult update(String id, String name) {
		return mongoTemplate.updateFirst(
				new Query(Criteria.where("id").is(id)),
				Update.update("name", name), User.class);
	}
    
    
	public UserRepository getRepository() {
		return repository;
	}

}
