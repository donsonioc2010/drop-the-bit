package parking.domain;

import parking.type.MemberType;
import parking.utils.Log;

/**
 * 사용자는 ID, PWD만 받는다
 */
public class Member {
    // Count 감소 ㄴㄴ
    private static int noCnt = 1;
    private int no;
    private String id;
    private String password;
    private MemberType memberType;

    public Member(String id, String password) {
        this.no = noCnt;
        this.id = id;
        this.password = password;
        addCount();
    }

    public Member(String id, String password, MemberType memberType) {
        this(id, password);
        this.memberType = memberType;
    }

    public void changeMemgerType(MemberType memberType) {
        this.memberType = memberType;
    }

    public static Member getMemberObject(String id, String password, MemberType requestMemberType) {
        return new Member(id, password, requestMemberType);
    }

    public boolean isLoginAble(String id, String password) {
        if (this.id == null || this.password == null || id == null || password == null) {
            System.out.println(Log.getError("아이디 또는 패스워드가 기입되지 않았습니다."));
            return false;
        }
        if (id.isBlank() && password.isBlank()) {
            return false;
        }

        if (this.id.equals(id) && this.password.equals(password)) {
            return true;
        }
        return false;
    }

    public static void addCount() {
        noCnt++;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public int getNo() {
        return no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
