package tn.esprit.service;

import java.util.List;

import tn.esprit.entites.Command;

public interface CommandService {

	public Command add(Command command);

	public List<Command> getAll();

	public Command getById(Long id);

	public boolean delete(Long id);

}
