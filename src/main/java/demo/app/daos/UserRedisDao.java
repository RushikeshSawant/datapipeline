package demo.app.daos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.app.dtos.UserDto;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * DAO to handle redis persistence
 * 
 * @author rushikesh
 *
 */
@Repository
public class UserRedisDao {

	private static final Logger LOG = LoggerFactory.getLogger(UserRedisDao.class);
	private final JedisPool pool;
	private final ObjectMapper mapper;
	public static final String REDIS_KEY = "USER_DATA";

	@Autowired
	public UserRedisDao(JedisPool pool, ObjectMapper mapper) {
		this.pool = pool;
		this.mapper = mapper;
	}


	public long save(UserDto userDto) {
		Jedis jedis = pool.getResource();
		String userJson = "";
		try {
			userJson = mapper.writeValueAsString(userDto);
		} catch (JsonProcessingException e) {
			LOG.error("[JACKSON_FAILED]: ", e);
		}
		Long id = null;
		try {
			id = jedis.rpush(REDIS_KEY, userJson);
		} finally {
			jedis.close();
		}
		LOG.info("[PUSHED_TO_REDIS]: " + userJson);
		return id.longValue();
	}

}
