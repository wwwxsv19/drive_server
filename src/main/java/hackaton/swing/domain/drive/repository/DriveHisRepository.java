package hackaton.swing.domain.drive.repository;

import hackaton.swing.domain.drive.DriveHis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriveHisRepository extends JpaRepository<DriveHis, Integer> {
    List<DriveHis> findAllByPassengerId(String passengerId);
}
