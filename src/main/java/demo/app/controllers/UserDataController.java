package demo.app.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import demo.app.dtos.UserDto;
import demo.app.exceptions.UserDataProcessingException;
import demo.app.services.UserDataService;

/**
 * REST controller to handle user data related requests
 * 
 * @author rushikesh
 *
 */
@RestController
@RequestMapping("/")
public class UserDataController {

	private static final Logger LOG = LoggerFactory.getLogger(UserDataController.class);
	private final UserDataService userService;

	@Autowired
	public UserDataController(UserDataService service) {
		this.userService = service;
	}

	@RequestMapping(path = "users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public long receiveData(@RequestBody @Valid UserDto userDto, Errors errors) {
		if (errors.hasErrors()) {
			throw new UserDataProcessingException(userDto);
		}
		LOG.info("[PROCESSING_PAYLOAD]: " + userDto);
		return userService.process(userDto);
	}

	@ExceptionHandler(UserDataProcessingException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public String handleUserDataProcessingException(UserDataProcessingException ex) {
		return ex.getMessage();
	}
}
