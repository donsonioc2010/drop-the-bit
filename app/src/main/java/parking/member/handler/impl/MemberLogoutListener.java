package parking.member.handler.impl;

import java.util.List;

import parking.member.domain.Member;
import parking.member.handler.AbstractMemberListener;
import parking.utils.common.console.BreadcrumbPrompt;
import parking.utils.log.Log;

public class MemberLogoutListener extends AbstractMemberListener {

	public MemberLogoutListener(List<Member> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		if (getLoginMember() == null) {
			System.out.println(Log.error("현재 로그인이 되어 있지 않습니다"));
			return;
		}
		System.out.println(Log.info(getLoginMember().getId() + " 사용자 로그아웃"));

		logout();
	}
}
