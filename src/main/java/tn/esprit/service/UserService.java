package tn.esprit.service;

import java.util.List;

import tn.esprit.entites.User;

public interface UserService {

	public User add(User user);

	public List<User> getAll();

	public User getById(Long id);

	public User edit(User user);

	public boolean delete(Long id);
}
