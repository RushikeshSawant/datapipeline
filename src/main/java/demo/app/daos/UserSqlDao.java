package demo.app.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.app.model.User;

/**
 * Makes use Spring for simple CRUD ops.
 * 
 * @author rushikesh
 *
 */
@Repository
public interface UserSqlDao extends CrudRepository<User, String> {

}
