package parking.utils;

public class TitlePrintUtils {
    public static void printMenu() {
        processStartTitle();
        StringBuffer menu = new StringBuffer()
                .append(Log.getMenu(0, "메뉴 출력\n"))
                .append(Log.getMenu(1, "입차량 추가\n"))
                .append(Log.getMenu(2, "입차 차량 단건 출력\n"))
                .append(Log.getMenu(3, "입차 차량 전체 출력\n"))
                .append(Log.getMenu(4, "입차 차량 정보 수정\n"))
                .append(Log.getMenu(5, "입차 차량 정보 삭제\n"))
                .append(Log.getMenu(6, "프로세스 종료"));

        System.out.println(menu.toString());
    }

    public static void printSelectMenu() {
        System.out.println("없는 메뉴입니다.");
    }

    public static void processStartTitle() {
        System.out.println(Log.getYelloLog("차량 목록 관리 시스템"));
        System.out.println(Log.getYelloLog("--------------------------------------------------------------------"));
    }

    public static void terminalClear() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    public static void outputTitle() {
        System.out.println(
                String.format("%s\t%s\t%s\t%s\t%s\t%s",
                        "번호", "차량번호", "휴대폰 전화번호", "주차 시작 시간", "주차비용", "결제여부"));
        System.out.println("--------------------------------------------------------------------");
    }
}
