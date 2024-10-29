package hackaton.swing.domain.drive;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "serviceName_drive_his")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DriveHis {
    @Id
    @GeneratedValue
    private int driverHisId;

    private LocalDateTime createdTime = LocalDateTime.now();

    private String passengerId;

    private String driverId;

    private int driveCost;
}
