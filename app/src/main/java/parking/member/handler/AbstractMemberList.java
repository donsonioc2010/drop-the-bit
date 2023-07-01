package parking.member.handler;

import java.util.List;

import parking.member.domain.Member;
import parking.utils.listener.ActionListener;

public abstract class AbstractMemberList implements ActionListener {
	protected List<Member> list;
}
