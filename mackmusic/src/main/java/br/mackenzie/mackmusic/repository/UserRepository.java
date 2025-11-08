package br.mackenzie.mackmusic.repository;

import br.mackenzie.mackmusic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> { //Tipo e ID
}
