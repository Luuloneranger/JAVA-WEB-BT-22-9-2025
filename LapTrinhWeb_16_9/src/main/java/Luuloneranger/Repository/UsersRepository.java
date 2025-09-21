package Luuloneranger.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Luuloneranger.Entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
	 	Optional<Users> findByUsername(String username);
	    Page<Users> findByUsernameContainingIgnoreCase(String username, Pageable pageable);
	    Optional<Users> findByUsernameAndPassword(String username, String password);
	    List<Users> findByUsernameContaining(String name);
	    
}
