package hu.luterdav.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.luterdav.model.SchoolUser;

public interface SchoolUserRepository extends JpaRepository<SchoolUser, Long> {

	Optional<SchoolUser> findByUsername(String username);
}

