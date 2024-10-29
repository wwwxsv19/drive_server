package hackaton.swing.domain.user.repository;

import hackaton.swing.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
