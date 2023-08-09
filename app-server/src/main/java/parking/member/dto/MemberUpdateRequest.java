package parking.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import parking.member.domain.Member;
import parking.member.type.MemberType;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateRequest {
    private long uid;
    private String password;
    private MemberType memberType;
    private LocalDateTime updatedAt;

    //MemberType이 존재하지 않는 경우 무조건 STAFF가 입력된다.
    public static Member updateMemberVO(MemberUpdateRequest m) {
        return Member.builder()
                .id(m.getUid())
                .password(m.getPassword())
                .memberType(m.getMemberType())
                .updatedAt(m.getUpdatedAt())
                .build();
    }
}
