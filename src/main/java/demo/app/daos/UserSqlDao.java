package demo.app.daos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.app.model.User;

@Repository
public interface UserSqlDao extends CrudRepository<User, String> {

}
