package parking.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import parking.domain.Member;
import parking.type.MemberType;
import parking.utils.Log;
import parking.utils.Prompt;

/**
 * 해당 프로세스는 관리자 또는 게스트를 구분하기 위함
 * 관리자 : 모든 처리, 입력 권한이 존재한다.
 * 게스트 : 특정 1인에 대한 조회만 가능함.
 * <p>
 * 사용자에 대한 추가는 관리자만 가능하다
 */
public class MemberProcess {

    private static final int MEMBER_LIST_SIZE = 50;
    private int listLength = 0;
    private Member[] memberList;
    private Prompt prompt;
    private Member loginUser;

    public MemberProcess(Prompt prompt) {
        memberList = new Member[MEMBER_LIST_SIZE];
        this.prompt = prompt;
        adminAListAdd();
    }

    /**
     * 현재 사용자 정보 확인을 하기 위한 메소드
     *
     * @return
     */
    public Member getLoginUser() {
        return loginUser;
    }

    /**
     * 사용자 로그인이며, 관리자, 유저로 로그인이 성공한 경우 True, 로그인에 실패하였거나 혹은 게스트권한으로 로그인한 경우에는
     * False를 Return한다
     *
     * @return
     */
    public boolean login() {
        String loginRequestId = prompt.getInputString(Log.getFontCyanToKeyword("[로그인 아이디]") + " > ");
        String loginRequestPassword = prompt.getInputString(Log.getFontCyanToKeyword("[로그인 패스워드]") + " > ");

        if (loginRequestId.isBlank() || loginRequestPassword.isBlank()) {
            System.out.println("로그인 요청을 " + Log.getFontBlueToKeyword(MemberType.GUEST.name()) + "으로 진행하셨습니다.");
            this.loginUser = new Member(null, null, MemberType.GUEST);
            return false;
        }

        this.loginUser = isContainUser(loginRequestId, loginRequestPassword);
        if (loginUser != null) {
            return true;
        }

        this.loginUser = new Member("", "", MemberType.GUEST);
        return false;
    }

    /**
     * Member의 No값을 기준으로 삭제처리를 진행한다
     */
    private void memberDelete() {
        if (!isExistUser()) {
            return;
        }

        int idx = prompt.getInputInt(Log.getFontCyanToKeyword("[삭제를 희망하는 사용자 NO]") + " > ");
        //try , catch 에서 오류로 잡혔기 때문
        if (idx == -1) {
            return;
        }

        // 인덱스 조회 이후 없는 결과이기에 경고 안내 후 종료
        idx = indexOf(idx);
        if (idx == -1) {
            System.out.println(Log.getWarn("해당 사용자는 " + Log.getFontRedToKeyword("존재")
                + "하지 않거나 또는 정보가 일치하지 않아 로그인이 불가능합니다.\n작업을 종료합니다."));
            return;
        }

        if (listLength == (idx - 1)) {
            memberList[listLength--] = null;
            return;
        }

        for (int i = idx; i < listLength; i++) {
            memberList[i] = memberList[i + 1];
        }
        listLength--;
    }

    /**
     * 사용자 추가
     *
     * @param member
     */
    public void listAddObject(Member member) {
        if (isIndexAvailable() == -1) {
            System.out.println(Log.getError("유저 리스트의 데이터가 전부 소진되어, 더 이상 유저의 추가가 불가능합니다.\n"
                + "관리자에게 요청 바랍니다."));
        }
        memberList[listLength++] = member;
    }

    /**
     * 관리자 용 Process
     *
     * @param menuNo
     */
    public void adminRunProcess(int menuNo) {
        if (!loginUserIsAdmin()) {
            return;
        }

        if (menuNo == 900) {
            memberAdd();
        } else if (menuNo == 901) {
            memberDelete();
        } else if (menuNo == 902) {
            memberUpdate();
        } else if (menuNo == 903) {
            memberAll();
        } else if (menuNo == 904) {
            memberFindByNo();
        } else {
            System.out.println(Log.getError("존재하지 않은 메뉴입니다."));
        }

    }

    /**
     * 사용자 정보 수정
     */
    private void memberUpdate() {
        int idx = indexOf(prompt.getInputInt(Log.getFontCyanToKeyword("조회하고자 하는 사용자 NO") + " > "));
        if (idx == -1) {
            System.out.println(Log.getError("해당 사용자는 존재하지 않습니다."));
            return;
        }

        String id = prompt.getInputString(Log.getFontCyanToKeyword("[수정 희망 ID]") + " > ");
        String password = prompt.getInputString(Log.getFontCyanToKeyword("[수정 희망 PW]") + " > ");
        MemberType memberType = getSelectAuth();

        memberList[idx].setId(id);
        memberList[idx].setPassword(password);
        memberList[idx].setMemberType(memberType);
        System.out.println(Log.bgBlueFgBlack("수정이 완료되었습니다."));
    }


    /**
     * 사용자 No를 받아 해당 사용자의 정보만 확인가능하다
     */
    private void memberFindByNo() {
        int no = indexOf(prompt.getInputInt(Log.getFontCyanToKeyword("[조회하고자 하는 사용자 NO]") + " > "));
        if (no == -1) {
            System.out.println(Log.getError("해당 사용자는 존재하지 않습니다."));
            return;
        }
        printMember(memberList[no]);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<String> list = new HashSet<>();
        System.out.println();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            if (line[1].equals("enter")) {
                list.add(line[0]);
            } else {
                list.remove(line[0]);
            }
        }
        StringBuffer sb = new StringBuffer();
        List<String> answer = list.stream().sorted(Comparator.reverseOrder()).toList();
        for (String name : list) {
            sb.append(name + "\n");
        }
        System.out.println(sb);
    }

    /**
     * 현재 추가된 전체 멤버 조회
     */
    private void memberAll() {
        if (!isExistUser()) {
            return;
        }
        System.out.println(Log.bgBlueFgBlack("NO\tID\tAuth"));
        for (int i = 0; i < listLength; i++) {
            printMember(memberList[i]);
        }
    }

    private static void printMember(Member member) {
        System.out.printf("%s\t%s\t%s\n", member.getNo(), member.getId(), member.getMemberType());
    }

    /**
     * MemberList의 No Field값을 기반으로 하여 동일한 값이 존재하는 경우 해당 MemberList의 index를 반환하며,
     * 존재하지 않으면 -1을 반환한다
     *
     * @param memberNo
     * @return
     */
    private int indexOf(int memberNo) {
        if (!isExistUser()) {
            return -1;
        }

        for (int i = 0; i < listLength; i++) {
            if (memberList[i].getNo() == memberNo) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 사용자 추가 Process
     */
    private void memberAdd() {
        String id = prompt.getInputString(Log.getFontCyanToKeyword("[아이디]") + " > ");
        String password = prompt.getInputString(Log.getFontCyanToKeyword("[패스워드]") + " > ");
        MemberType memberType = getSelectAuth();
        listAddObject(new Member(id, password, memberType));
    }

    /**
     * 사용자 권한 선택 메뉴
     *
     * @return
     */
    private MemberType getSelectAuth() {
        while (true) {
            String tempType = prompt.getInputString(Log.getFontCyanToKeyword("[사용자권한(사용자/관리자)]") + " > ");
            if ("사용자".equals(tempType.trim())) {
                return MemberType.USER;
            }
            if ("관리자".equals(tempType.trim())) {
                return MemberType.ADMIN;
            }
            System.out.println(Log.getError("케이스에 없는 값이 입력되었습니다. 다시 기입해 주십시오."));
        }
    }

    /**
     * 관리자 권한 Process실행시 확인하며 현재 Instance에 존재하는 사용자가 관리자인지를 판별한다
     *
     * @return
     */
    private boolean loginUserIsAdmin() {
        if (loginUser != null && MemberType.ADMIN.equals(loginUser.getMemberType())) {
            return true;
        }
        System.out.println(Log.getError("관리자가 아니면 해당 기능의 사용이 불가능합니다."));
        return false;
    }

    /**
     * MemberArray의 추가를 위하여 Index확인, 더이상 추가가 불가능한 경우 -1을 반환한다.
     *
     * @return
     */
    private int isIndexAvailable() {
        return listLength < MEMBER_LIST_SIZE ? listLength : -1;
    }

    /**
     * 현재 MemberArray에 사용자 자체가 존재하는지 확인한다.
     *
     * @return
     */
    private boolean isExistUser() {
        if (listLength > 0) {
            return true;
        }
        System.out.println(Log.getError("현재 추가된 사용자가 존재하지 않습니다."));
        return false;
    }

    /**
     * Id, password를 받아서 두 정보가 일치하는 사용자가 존재 하는가를 확인한다.
     *
     * @param id
     * @param password
     * @return
     */
    private Member isContainUser(String id, String password) {
        if (!isExistUser()) {
            return null;
        }
        for (int i = 0; i < listLength; i++) {
            if (memberList[i].isLoginAble(id, password)) {
                return memberList[i];
            }
        }
        return null;
    }

    /**
     * 테스트용 데이터, admin추후 DB를 사용하게 될 경우에는 admin만 기초값으로 제공한다.
     */
    private void adminAListAdd() {
        listAddObject(Member.getMemberObject("admin", "admin", MemberType.ADMIN));
        listAddObject(Member.getMemberObject("user", "user", MemberType.USER));
    }

}
