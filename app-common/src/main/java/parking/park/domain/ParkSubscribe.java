package parking.park.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ParkSubscribe implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String carNumber;           // 차주 차량 번호
    private String phoneNumber;         // 차주 폰번호
    private LocalDateTime subscribeStAt; // 주차권 구독 시작일
    private LocalDateTime subscribeEdAt; // 주차권 구독 종료일
}
