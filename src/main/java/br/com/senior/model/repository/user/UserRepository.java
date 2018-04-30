package br.com.senior.model.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.senior.model.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u where username = :username AND password = :userpass")
	User findUserByUsernameAndPassword(@Param(value = "username") String user,@Param(value = "userpass") String password);

}
