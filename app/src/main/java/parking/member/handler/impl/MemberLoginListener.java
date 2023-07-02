package parking.member.handler.impl;

import java.util.List;

import parking.member.domain.Member;
import parking.member.handler.AbstractMemberListener;
import parking.session.LoginSession;
import parking.utils.common.console.BreadcrumbPrompt;
import parking.utils.log.Log;

public class MemberLoginListener extends AbstractMemberListener {
	public MemberLoginListener(List<Member> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		if (isHaveLoginInSession()) {
			System.out.println(Log.error("현재 다른 사용자가 로그인 되어 있습니다. 로그아웃 이후 로그인 해주시기 바랍니다"));
		}

		Member searchMember = findByUserId(prompt.getInputString("ID > "));
		if (searchMember == null) {
			System.out.println(Log.error("해당 사용자는 존재하지 않습니다"));
			return;
		}

		String password = prompt.getInputString("PWD > ");

		if (searchMember.getPassword().equals(password)) {
			LoginSession.getInstance().setLoginMember(searchMember);
			return;
		}

		System.out.println(Log.error("패스워드가 일치하지 않습니다"));

	}
}
