package tn.esprit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entites.Command;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long>{

}
