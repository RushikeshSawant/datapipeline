package demo.app.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.app.daos.UserRedisDao;
import demo.app.daos.UserSqlDao;
import demo.app.dtos.UserBuilder;
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
	private final UserSqlDao sqlDao;

	@Autowired
	public UserDataService(UserRedisDao redisDao, UserSqlDao sqlDao) {
		this.redisDao = redisDao;
		this.sqlDao = sqlDao;
	}

	@Override
	public long process(UserDto user) {
		LOG.info("[PROCESSING_USER_DATA]: " + user);
		// For these resources we just hand over the data to dao.
		return redisDao.save(user);
	}
	
	public List<UserDto> findAllUsers(){
	    return StreamSupport.stream(sqlDao.findAll().spliterator(), false)
	            .map(UserBuilder::buildUserDto)
	            .collect(Collectors.toList());
	}

}
