package parking.member.handler.impl;

import java.util.List;

import parking.member.domain.Member;
import parking.member.handler.AbstractMemberListener;
import parking.member.type.MemberType;
import parking.utils.common.console.BreadcrumbPrompt;
import parking.utils.log.Log;

public class MemberAddListener extends AbstractMemberListener {
	private BreadcrumbPrompt prompt;

	public MemberAddListener(List<Member> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		this.prompt = prompt;

		String type = prompt.getInputString("사용자 추가 타입(1. 관리자, 2. 직원, 3. 정비사, [4. 게스트])").trim();
		boolean result = true;

		if (type.equalsIgnoreCase("1") || type.equalsIgnoreCase("관리자")) {
			result = addMemberProcess(MemberType.ADMIN);
		} else if (type.equalsIgnoreCase("2") || type.equalsIgnoreCase("직원")) {
			result = addMemberProcess(MemberType.STAFF);
		} else if (type.equalsIgnoreCase("2") || type.equalsIgnoreCase("직원")) {
			result = addMemberProcess(MemberType.TECHNICIAN);
		} else if (type.equalsIgnoreCase("4") || type.equalsIgnoreCase("게스트")) {
			result = addMemberProcess(MemberType.GUEST);
		}

		if (!result) {
			System.out.println(Log.error("계정 생성에 실패하였습니다."));
		} else {
			System.out.println(Log.info("계정 생성에 성공하였습니다."));
		}
	}

	private boolean addMemberProcess(MemberType createType) {
		if (!MemberType.GUEST.equals(createType)) {
			// 관리자, 직원, 정비사인 경우
			if (!isHaveLoginInSession()) {
				System.out.println(Log.error("사용자 로그인이 되어 있지 않으면 게스트를 제외하고 추가가 불가능합니다"));
				return false;
			}

			Member loginMember = getLoginMember();
			if (loginMember.getMemberType() != MemberType.ADMIN) {
				System.out.println(Log.error("관리자 사용자로 로그인을 하지 않으면 사용자의 추가가 불가능합니다"));
				return false;
			}
		}
		addMember(createType);
		return true;
	}

	private void addMember(MemberType createType) {
		String id = null;
		while (true) {
			id = this.prompt.getInputString("ID > ");
			if (findByUserId(id) == null) {
				break;
			}
			System.out.println(Log.warn("이미 사용중인 ID 입니다"));
		}
		String password = prompt.getInputString("Password > ");
		list.add(Member.createMember(id, password, createType));
	}
}
