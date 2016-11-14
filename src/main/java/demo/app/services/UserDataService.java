package demo.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.app.daos.UserRedisDao;
import demo.app.dtos.UserDto;

/**
 * Implements {@link IProcessor} to process user data.
 * 
 * @author rushikesh
 *
 */
@Service
public class UserDataService implements IProcessor<UserDto> {

	private final UserRedisDao redisDao;

	@Autowired
	public UserDataService(UserRedisDao redisDao) {
		this.redisDao = redisDao;
	}

	@Override
	public long process(UserDto user) {
		return redisDao.save(user);
	}

}
