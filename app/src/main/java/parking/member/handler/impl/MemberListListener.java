package parking.member.handler.impl;

import java.util.List;

import parking.member.domain.Member;
import parking.member.handler.AbstractMemberListener;
import parking.member.type.MemberType;
import parking.member.utils.MemberConstants;
import parking.utils.common.console.BreadcrumbPrompt;

public class MemberListListener extends AbstractMemberListener {
	public MemberListListener(List<Member> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		if (getLoginMember() == null ||
			!(MemberType.ADMIN.equals(getLoginMember().getMemberType()) ||
				MemberType.STAFF.equals(getLoginMember().getMemberType()))
		) {
			System.out.println("[ERROR] 조회 권한이 없습니다.");
			return;
		}

		MemberConstants.outPutTitle();
		list.stream().forEach(member -> printMemberInfo(member));
	}
}
