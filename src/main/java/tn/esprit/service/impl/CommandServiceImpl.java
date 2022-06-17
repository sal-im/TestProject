package tn.esprit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.entites.Command;
import tn.esprit.repository.CommandRepository;
import tn.esprit.repository.ProductRepository;
import tn.esprit.repository.UserRepository;
import tn.esprit.service.CommandService;
import tn.esprit.service.ProductService;
import tn.esprit.service.UserService;

@Service
public class CommandServiceImpl implements CommandService {

	@Autowired
	CommandRepository commandRepository;

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductService productService;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Command add(Command command) {
		if (command != null & command.getUser().getId() != null & command.getProduct().getId() != null) {
			if (userRepository.existsById(command.getUser().getId())
					& productRepository.existsById(command.getProduct().getId())) {
				if (productService.getById(command.getProduct().getId()).getQuantity() > 0) {
					return commandRepository.save(command);
				}
			}
		}
		return null;
	}

	@Override
	public List<Command> getAll() {
		return commandRepository.findAll();
	}

	@Override
	public Command getById(Long id) {
		if (id != null) {
			Optional<Command> optional = commandRepository.findById(id);
			if (optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}

	@Override
	public boolean delete(Long id) {
		commandRepository.deleteById(id);
		return !commandRepository.existsById(id);
	}

}
