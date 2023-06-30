package parking.utils;

import parking.type.MemberType;

public class TitlePrintUtils {
    public static void printMenu(MemberType memberType) {
        processStartTitle();
        StringBuffer menu = new StringBuffer(Log.getFontCyanToKeyword("======공통======\n"))
            .append(Log.getMenu(0, "메뉴 출력\n"))
            .append(Log.getMenu(1, "입차량 추가\n"))
            .append(Log.getMenu(2, "입차 차량 단건 출력\n"))
            .append(Log.getMenu(3, "입차 차량 전체 출력\n"))
            .append(Log.getMenu(4, "입차 차량 정보 수정\n"))
            .append(Log.getMenu(5, "입차 차량 정보 삭제\n"))
            .append(Log.getMenu(6, "프로세스 종료\n"))
            .append(Log.getMenu(7, "사용자 변경\n"));

        if (MemberType.USER.equals(memberType) || MemberType.ADMIN.equals(memberType)) {
            menu.append("\n" + Log.getFontCyanToKeyword("======유저======\n"))
                .append(Log.getMenu(701, "정기권 발급\n"))
                .append(Log.getMenu(702, "정보 수정" + (MemberType.USER.equals(memberType) ? "" : "\n")));
        }

        if (MemberType.ADMIN.equals(memberType)) {
            menu.append("\n" + Log.getFontCyanToKeyword("======관리자======\n"))
                .append(Log.getMenu(900, "사용자 추가\n"))
                .append(Log.getMenu(901, "사용자 삭제\n"))
                .append(Log.getMenu(902, "사용자 수정\n"))
                .append(Log.getMenu(903, "사용자 리스트\n"))
                .append(Log.getMenu(904, "사용자 정보 단건 확인"));
        }

        System.out.println(menu);
    }

    public static void printSelectMenu() {
        System.out.println("없는 메뉴입니다.");
    }

    public static void processStartTitle() {
        System.out.println(Log.getYellowLog("차량 목록 관리 시스템"));
        System.out.println(Log.getYellowLog("--------------------------------------------------------------------"));
    }

    public static void terminalClear() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public static void outputTitle() {
        System.out.println(
            Log.bgBlueFgBlack(
                String.format(
                    "%s\t%s\t%s\t%s\t%s\t%s",
                    "번호", "차량번호", "휴대폰 전화번호", "주차 시작 시간", "주차비용", "결제여부")
            )
        );
    }
}
