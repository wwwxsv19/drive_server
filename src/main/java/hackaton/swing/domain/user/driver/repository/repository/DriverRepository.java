package hackaton.swing.domain.user.driver.repository.repository;

import hackaton.swing.domain.user.driver.repository.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, String> {
}
