package parking.member.handler;

import java.util.List;

import parking.member.domain.Member;
import parking.session.LoginSession;
import parking.utils.listener.ActionListener;

// 사용자 추가, 삭제, 수정, 조회 단, 리스트
public abstract class AbstractMemberListener implements ActionListener {
	protected List<Member> list;

	protected static void printMemberInfo(Member member) {
		System.out.printf("%d\t%s\t%s\t%s\n",
			member.getNo(),
			member.getId(),
			member.getPassword(),
			member.getMemberType()
		);
	}

	public AbstractMemberListener(List<Member> list) {
		this.list = list;
	}

	public Member findByUserId(String userId) {
		return list.stream().filter(member -> member.getId().equals(userId)).findFirst().orElse(null);
	}

	public void logout() {
		LoginSession.getInstance().setLoginMember(null);
	}

	public Member getLoginMember() {
		return LoginSession.getInstance().getLoginMember();
	}

	public boolean isHaveLoginInSession() {
		return LoginSession.getInstance().getLoginMember() != null;
	}

	public boolean isAbleNeedLogin() {
		if (!isHaveLoginInSession()) {
			System.out.println("[ERROR] 로그인을 해야 가능한 기능입니다.");
			return false;
		}
		return true;
	}

}
