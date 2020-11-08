package pl.sda.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.projects.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("select count(u) > 0 from User u where u.email = ?1")
    boolean checkIfMailExists(String email);

    @Query("select count(u) > 0 from User u where u.name = ?1")
    boolean checkIfNameExists(String name);

}
