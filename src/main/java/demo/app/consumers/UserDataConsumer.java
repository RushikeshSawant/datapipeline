package demo.app.consumers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.app.daos.UserSqlDao;
import demo.app.model.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class UserDataConsumer implements IConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(UserDataConsumer.class);
	private final JedisPool pool;
	private final UserSqlDao dao;
	private final ObjectMapper mapper;
	public static final String REDIS_KEY = "USER_DATA";

	@Autowired
	public UserDataConsumer(JedisPool pool, UserSqlDao dao, ObjectMapper mapper) {
		this.pool = pool;
		this.dao = dao;
		this.mapper = mapper;
	}

	@Override
	@Scheduled(fixedDelay = 1000, initialDelay = 3000)
	public void consume() {
		final Jedis jedis = pool.getResource();
		List<String> data = Collections.emptyList();
		try {
			data = jedis.blpop(1, REDIS_KEY);
		} finally {
			jedis.close();
		}
		data.stream().filter(elm -> !elm.equals(REDIS_KEY))
		.peek(elm -> {
			LOG.info("[USER_DATA_REDIS]: " + elm);
		}).map(elm -> mapToUser(elm))
		.peek(elm -> {
			LOG.info("TEST: " + elm.getClass().getCanonicalName());
		}).forEach(elm -> {
			publishToWebSocket(elm);
			saveToDataStore(elm);
		});
	}

	private User mapToUser(String jsonUser) {
		User user = null;
		try {
			user = mapper.readValue(jsonUser, User.class);
		} catch (IOException e) {
			LOG.error("[JACKSON_FAILED]: ", e);
		}
		return user;
	}

	private void publishToWebSocket(User user) {
	}

	private void saveToDataStore(User user) {
		LOG.info("[PUSH_TO_DATASTORE]: " + user.getEmail());
		dao.save(user);
	}

}
