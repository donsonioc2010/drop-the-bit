package parking.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import parking.member.domain.Member;
import parking.member.type.MemberType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddRequest {
    private String userId;
    private String password;
    private MemberType memberType;

    //MemberType이 존재하지 않는 경우 무조건 STAFF가 입력된다.
    public static Member addMemberVO(MemberAddRequest m) {
        return Member.builder()
                .userId(m.getUserId())
                .password(m.getPassword())
                .memberType(m.getMemberType())
                .build();
    }
}
