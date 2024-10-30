package hackaton.swing.domain.user.passenger.repository;

import hackaton.swing.domain.user.passenger.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, String> {
}
