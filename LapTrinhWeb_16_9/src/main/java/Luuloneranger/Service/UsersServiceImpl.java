package Luuloneranger.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Luuloneranger.Entity.Category;
import Luuloneranger.Entity.Users;
import Luuloneranger.Repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersRepository us;
	
	@Override
	public Page<Users> findAll(Pageable pageable) {
		return us.findAll(pageable);
	}

	@Override
	public Optional<Users> findById(Long id) {
		
		return us.findById(id);
	}

	@Override
	public Users save(Users user) {
		return us.save(user);
	}

	@Override
	public void deleteById(Long id) {
		us.deleteById(id);
	}

	@Override
	public Optional<Users> findByUsername(String username) {
		return us.findByUsername(username);
	}

	@Override
	public List<Users> findAll() {
		return us.findAll();
	}

	@Override
	public List<Users> findByUsernameContaining(String username) {
		return us.findByUsernameContaining(username);
	}

}
