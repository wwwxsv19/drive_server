package hackaton.swing.domain.user.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Keywords {
    N1(1, "안전 운전"),
    N2(2, "향기"),
    N3(3, "정숙"),
    N4(4, "비흡연"),
    N5(5, "빠른 응대"),
    N6(6, "청결"),
    N7(7, "전기차"),
    N8(8, "시간 배려"),
    N9(9, "음악"),
    N10(10, "경로 배려"),
    N11(11, "따뜻한 온도");

    private final int key;
    private final String value;
}
