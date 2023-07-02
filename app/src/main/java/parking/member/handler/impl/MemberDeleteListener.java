package parking.member.handler.impl;

import java.util.List;

import parking.member.domain.Member;
import parking.member.handler.AbstractMemberListener;
import parking.member.type.MemberType;
import parking.utils.common.console.BreadcrumbPrompt;

public class MemberDeleteListener extends AbstractMemberListener {
	public MemberDeleteListener(List<Member> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		if (!isAbleNeedLogin()) {
			return;
		}

		Member deleteMember = findByUserId(prompt.getInputString("삭제 ID > "));
		if (deleteMember == null) {
			System.out.println("[WARN] 삭제할 사용자가 존재하지 않습니다.");
			return;
		}

		if (deleteMember.getId().equals("admin")) {
			System.out.println("[ERROR] 삭제가 불가능한 ID 입니다.");
			return;
		}

		if (getLoginMember().equals(deleteMember)) {
			System.out.println("[WARN] 현재 로그인한 사용자 세션이 종료됩니다.");
			logout();
			list.remove(deleteMember);
			System.out.println("[INFO] 삭제가 완료되었습니다.");
			return;
		}

		if (MemberType.ADMIN.equals(getLoginMember().getMemberType())) {
			System.out.println("[WARN] 관리자 기능을 통한 삭제가 이뤄집니다. 삭제를 진행하는 ID :" + getLoginMember().getId());
			list.remove(deleteMember);
			System.out.println("[INFO] 삭제가 완료되었습니다.");
			return;
		}

		System.out.println("[ERROR] 삭제 권한이 존재하지 않습니다.");
	}
}
