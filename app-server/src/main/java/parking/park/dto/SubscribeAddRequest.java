package parking.park.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import parking.park.domain.ParkSubscribe;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeAddRequest {

    private String carNumber;
    private String phoneNumber;
    private LocalDateTime subscribeStAt;
    private long expireMonth;

    public static ParkSubscribe addRequestVO(SubscribeAddRequest req) {
        return ParkSubscribe.builder()
                .carNumber(req.getCarNumber())
                .phoneNumber(req.phoneNumber)
                .subscribeStAt(req.getSubscribeStAt())
                .subscribeEdAt(req.getSubscribeStAt().plusMonths(req.expireMonth))
                .build();
    }
}
