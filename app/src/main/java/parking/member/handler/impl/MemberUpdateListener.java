package parking.member.handler.impl;

import java.util.List;

import parking.member.domain.Member;
import parking.member.handler.AbstractMemberListener;
import parking.member.type.MemberType;
import parking.utils.common.console.BreadcrumbPrompt;

/**
 * 패스워드 수정기능만 존재함
 */
public class MemberUpdateListener extends AbstractMemberListener {
	private BreadcrumbPrompt prompt;

	public MemberUpdateListener(List<Member> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		if (!isAbleNeedLogin()) {
			return;
		}

		this.prompt = prompt;

		Member updateMember = findByUserId(prompt.getInputString("수정 ID > "));
		if (updateMember == null) {
			System.out.println("[WARN] 수정 진행을 할 사용자가 존재하지 않습니다.");
			return;
		}

		if (updateMember.equals(getLoginMember())) {
			editProcess(updateMember, getLoginMember().getMemberType());
			return;
		}

		if (MemberType.ADMIN.equals(getLoginMember().getMemberType())) {
			System.out.println("[WARN] 관리자 기능을 통한 수정이 이뤄집니다. 수정을 진행하는 ID :" + getLoginMember().getId());
			editProcess(updateMember, getLoginMember().getMemberType());
			return;
		}
		System.out.println("[ERROR] 수정권한이 존재하지 않습니다.");
	}

	public void editProcess(Member updateMember, MemberType requestMemberType) {
		String password = this.prompt.getInputString("변경 PWD > ");
		updateMember.setPassword(password);
		System.out.println("[SUCCESS] 패스워드 수정이 완료되었습니다.");
	}
}
