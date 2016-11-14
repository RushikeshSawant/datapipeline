package demo.app.daos;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cedarsoftware.util.io.JsonWriter;

import demo.app.dtos.UserDto;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

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
	public static final String REDIS_KEY = "USER_DATA";
	private Map<String, Object> jsonWriterArgs;

	@Autowired
	public UserRedisDao(JedisPool pool) {
		this.pool = pool;
	}

	@PostConstruct
	private void configureJsonWriter() {
		// Going with simple json-io library
		jsonWriterArgs = new HashMap<>();
		jsonWriterArgs.put(JsonWriter.TYPE, false);
	}

	public Long save(UserDto userDto) {
		Jedis jedis = pool.getResource();
		String userJson = JsonWriter.objectToJson(userDto, jsonWriterArgs);
		Response<Long> resp = null;
		try {
			Pipeline pipline = jedis.pipelined();
			resp = pipline.rpush(REDIS_KEY, userJson);
			pipline.sync();
		} finally {
			jedis.close();
		}
		LOG.info("[PUSHED_TO_REDIS]: " + userJson);
		return resp.get();
	}

}
