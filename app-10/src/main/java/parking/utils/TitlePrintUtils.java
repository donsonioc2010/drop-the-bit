package parking.utils;

public class TitlePrintUtils {
	public static void printMenu() {
		processStartTitle();
		System.out.println("0. 메뉴 출력");
		System.out.println("1. 입차량 추가");
		System.out.println("2. 입차 차량 단건 출력");
		System.out.println("3. 입차 차량 전체 출력");
		System.out.println("4. 입차 차량 정보 수정");
		System.out.println("5. 입차 차량 정보 삭제");
		System.out.println("6. 프로세스 종료");
	}

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

	public static void outputTitle() {
		System.out.println(
			String.format("%s\t%s\t%s\t%s\t%s\t%s",
				"번호", "차량번호", "휴대폰 전화번호", "주차 시작 시간", "주차비용", "결제여부"));
		System.out.println("--------------------------------------------------------------------");
	}
}
