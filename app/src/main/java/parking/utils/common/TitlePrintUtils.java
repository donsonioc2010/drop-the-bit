package parking.utils.common;

public class TitlePrintUtils {
	public static void printSelectMenu() {
		System.out.println("없는 메뉴입니다.");
	}

	public static void processStartTitle() {
		System.out.println("차량 목록 관리 시스템");
		System.out.println("--------------------------------------------------------------------");
	}

	public static void terminalClear() {
		for (int i = 0; i < 30; i++) {
			System.out.println();
		}
	}
}
