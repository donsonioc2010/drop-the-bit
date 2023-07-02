package parking.session;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import parking.member.domain.Member;

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
}
