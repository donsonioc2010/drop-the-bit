package parking.member.handler.impl;

import java.util.List;

import parking.member.domain.Member;
import parking.member.handler.AbstractMemberListener;
import parking.member.type.MemberType;
import parking.member.utils.MemberConstants;
import parking.utils.common.console.BreadcrumbPrompt;
import parking.utils.log.Log;

public class MemberDetailListener extends AbstractMemberListener {
	public MemberDetailListener(List<Member> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		if (getLoginMember() == null ||
			!(MemberType.ADMIN.equals(getLoginMember().getMemberType()) ||
				MemberType.STAFF.equals(getLoginMember().getMemberType()))
		) {
			System.out.println(Log.error("조회 권한이 없습니다."));
			return;
		}

		String id = prompt.getInputString("조회 ID >").trim();

		if (id.isBlank()) {
			System.out.println(Log.error("입력된 정보가 없습니다"));
			return;
		}

		Member searchMember = findByUserId(id);
		if (searchMember == null) {
			System.out.println(Log.error("사용자 정보가 존재하지 않습니다."));
			return;
		}
		MemberConstants.outPutTitle();
		printMemberInfo(searchMember);
	}
}
