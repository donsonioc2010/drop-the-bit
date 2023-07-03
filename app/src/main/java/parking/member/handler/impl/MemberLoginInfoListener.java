package parking.member.handler.impl;

import java.util.List;

import parking.member.domain.Member;
import parking.member.handler.AbstractMemberListener;
import parking.utils.common.console.BreadcrumbPrompt;
import parking.utils.log.Log;

public class MemberLoginInfoListener extends AbstractMemberListener {
	public MemberLoginInfoListener(List<Member> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		if (!isHaveLoginInSession()) {
			System.out.println(Log.error("현재 로그인한 사용자 정보가 없습니다"));
			return;
		}

		Member member = getLoginMember();
		StringBuilder sb = new StringBuilder()
			.append("[사용자 UID]" + member.getNo())
			.append("\n[사용자 ID]" + member.getId())
			.append("\n[사용자 권한]" + member.getMemberType());

		System.out.println(sb);
	}
}
