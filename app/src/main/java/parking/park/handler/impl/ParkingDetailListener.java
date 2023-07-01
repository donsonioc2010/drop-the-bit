package parking.park.handler.impl;

import java.util.List;

import parking.park.domain.ParkingCar;
import parking.park.handler.AbstractParkingListener;
import parking.park.utils.ParkingConstants;
import parking.utils.common.console.BreadcrumbPrompt;

/**
 * 주차 차량 정보 세부 조회
 */
public class ParkingDetailListener extends AbstractParkingListener {

	public ParkingDetailListener(List<ParkingCar> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		ParkingCar carInfo = this.findParkingCarByCarNumber(prompt.getInputString("조회차량번호 >"));

		if (carInfo == null) {
			ParkingConstants.outPutNoData();
			return;
		}

		printParkInfo(carInfo);
	}
}
