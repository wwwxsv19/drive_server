package hackaton.swing.domain.drive;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Entity
@Table(name = "drive_his")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DriveHis {
    @Id
    @GeneratedValue
    private int driverHisId;

    @Column(columnDefinition = "date")
    private String createdTime;

    private String passengerId;

    private String driverId;

    private String driverCarNumber;

    private int driveCost;

    private int driveDistance;
}
