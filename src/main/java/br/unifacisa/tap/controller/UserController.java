package br.unifacisa.tap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.unifacisa.tap.entity.User;
import br.unifacisa.tap.service.UserService;

/**
 * 
 * @author jaircavalcante
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	/***
	 * List All Users
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {

		return new ResponseEntity<List<User>>(userService.listAllUsers(), HttpStatus.OK);

	}

	/***
	 * List user with sending of id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable String id) {

		User user = userService.getById(id);

		return user == null ? new ResponseEntity<User>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable String id) {
		User user = userService.getById(id);
		if(user != null) {
			userService.delete(user);
		}

		return user == null ? new ResponseEntity<User>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody User usuario) {
		User user = userService.update(usuario);

		return user == null ? new ResponseEntity<User>(HttpStatus.NOT_FOUND)
				: new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/***
	 * Insert User in DataBase of type User
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<String> createUser(@RequestBody User user) {

		try {
			userService.save(user);
			return new ResponseEntity<String>(HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public UserService getUserService() {
		return userService;
	}
}
