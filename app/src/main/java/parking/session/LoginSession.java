package parking.session;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import parking.member.domain.Member;
import parking.member.type.MemberType;

/**
 * 현재 접속되어있는 로그인 정보
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginSession {

	private static LoginSession instance = null;

	private Member loginMember = null;

	public static LoginSession getInstance() {
		if (instance == null) {
			instance = new LoginSession();
		}
		return instance;
	}

	public Member getLoginMember() {
		return loginMember;
	}

	public void setLoginMember(Member loginMember) {
		this.loginMember = loginMember;
	}

	public boolean isAbleUseFunctionByAdminAndStaff() {
		return loginMember != null &&
			(MemberType.ADMIN.equals(loginMember.getMemberType()) ||
				MemberType.STAFF.equals(loginMember.getMemberType()));
	}
}
