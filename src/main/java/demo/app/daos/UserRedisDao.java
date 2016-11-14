package demo.app.daos;

import org.springframework.stereotype.Repository;

import demo.app.dtos.UserDto;

/**
 * DAO to handle redis persistence
 * @author rushikesh
 *
 */
@Repository
public class UserRedisDao {

	public long save(UserDto user) {
		return 0;
	}

}
