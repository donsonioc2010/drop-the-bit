package parking.member.handler.impl;

import java.util.List;

import parking.member.domain.Member;
import parking.member.handler.AbstractMemberListener;
import parking.utils.common.console.BreadcrumbPrompt;

public class MemberLogoutListener extends AbstractMemberListener {

	public MemberLogoutListener(List<Member> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		if (getLoginMember() == null) {
			System.out.println("[ERROR] 현재 로그인이 되어 있지 않습니다.");
			return;
		}
		System.out.printf("[INFO] %s 사용자 로그아웃\n", getLoginMember().getId());

		logout();
	}
}
