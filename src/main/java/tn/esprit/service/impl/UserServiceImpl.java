package tn.esprit.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entites.User;
import tn.esprit.repository.UserRepository;
import tn.esprit.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User add(User user) {
		if (user != null & !user.getEmail().isEmpty() & EmailValidator.getInstance().isValid(user.getEmail())
				& !userRepository.existsByEmail(user.getEmail()) & !user.getPassword().isEmpty()
				& user.getPassword().length() > 5) {
			return userRepository.save(user);
		}
		return null;
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User getById(Long id) {
		if (id != null) {
			Optional<User> user = userRepository.findById(id);
			if (user.isPresent()) {
				return user.get();
			}
		}
		return null;
	}

	@Override
	public User edit(User user) {
		if (user != null & user.getId() != null && userRepository.existsById(user.getId())) {
			return userRepository.save(user);
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		userRepository.deleteById(id);
		return !userRepository.existsById(id);
	}

}
