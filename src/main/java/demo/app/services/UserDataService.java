package demo.app.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOG = LoggerFactory.getLogger(UserDataService.class);
	private final UserRedisDao redisDao;

	@Autowired
	public UserDataService(UserRedisDao redisDao) {
		this.redisDao = redisDao;
	}

	@Override
	public long process(UserDto user) {
		LOG.info("[PROCESSING_USER_DATA]: " + user);
		// For these resources we just hand over the data to dao.
		return redisDao.save(user);
	}

}
