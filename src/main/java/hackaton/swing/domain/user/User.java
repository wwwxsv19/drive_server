package hackaton.swing.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Builder
@Entity
@Table(name = "serviceName_passenger")
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    private String passengerId;

    private String passengerName;

    private String passengerPassword;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> passengerKeywords;
}
