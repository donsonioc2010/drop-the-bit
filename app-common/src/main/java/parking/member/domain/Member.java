package parking.member.domain;

import lombok.*;
import parking.member.type.MemberType;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Member implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;            // UID
    private String userId;      // 로그인 ID
    private String password;    // PASSWORD
    private MemberType memberType;  //MemberType, 계정 권한
    private LocalDateTime createdAt; // 계정 생성일
    private LocalDateTime updatedAt; // 계정 마지막 수정일
}
