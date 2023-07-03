package parking.park.utils;

import parking.utils.log.Log;

public class ParkingConstants {
	public static final String NO_DATA = "입력된 데이터가 없습니다.";

	public static void outPutNoData() {
		System.out.println(Log.error(NO_DATA));
	}

	public static void outPutTitle() {
		System.out.println("--------------------------------------------------------------------");
		System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n",
			"번호", "차량번호", "휴대폰 전화번호", "주차 시작 시간", "주차비용", "결제여부");
		System.out.println("--------------------------------------------------------------------");
	}
}
