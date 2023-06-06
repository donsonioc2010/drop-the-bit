package parking.utils;

public class TitlePrintUtils {
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
