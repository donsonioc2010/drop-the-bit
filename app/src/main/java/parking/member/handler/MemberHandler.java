package parking.member.handler;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import parking.member.domain.Member;
import parking.utils.common.console.Prompt;

// 직원, 손님, 관리자, 정비사
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MemberHandler {
	private Prompt prompt;
	private List<Member> memberList;

}
