package Luuloneranger.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Luuloneranger.Entity.Users;

public interface UsersService {
    Page<Users> findAll(Pageable pageable);
    
    Optional<Users> findById(Long id);
    Users save(Users user);
    void deleteById(Long id);
    
    Optional<Users> findByUsername(String username);
    
    List<Users> findAll();
    List<Users> findByUsernameContaining(String username);
}
