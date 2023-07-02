package parking.member.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import parking.member.type.MemberType;
import parking.utils.common.domain.CsvObject;

/**
 * 사용자는 ID, PWD만 받는다
 */
@Data
@NoArgsConstructor
public class Member implements Serializable, CsvObject {

	private static final long serialVersionUID = 1L;

	// Count 감소 ㄴㄴ
	private static long noCnt = 1;
	private long no;
	private String id;
	private String password;
	private MemberType memberType;

	@Builder
	public Member(long no, String id, String password, MemberType memberType) {
		this.no = no;
		this.id = id;
		this.password = password;
		this.memberType = memberType;
	}

	public static Member createMember(String id, String password, MemberType memberType) {
		return Member.builder()
			.no(noCnt++)
			.id(id)
			.password(password)
			.memberType(memberType)
			.build();
	}

	@Override
	public String toCsvString() {
		return null;
	}

	public static String fromCsvString(String csv) {
		return null;
	}
}