package parking.park.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ParkingInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String carNumber;           // 차량번호
    private LocalDateTime parkStAt;  // 입차시간
    private LocalDateTime parkEdAt;    // 출차시간
    private int amount;                 // 주차금액, 출차이후 기록 , 구독권이 존재하는 경우에는 0으로 기록된다.
}
