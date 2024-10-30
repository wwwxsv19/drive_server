package hackaton.swing.domain.user.driver.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Builder
@Entity
@Table(name = "driver")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Driver {
    @Id
    private String driverId;

    private String driverName;

    private String driverPassword;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> driverKeywords;

    private String driverCarNumber;
}
