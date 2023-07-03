package parking.park.handler.impl;

import java.util.List;

import parking.park.domain.ParkingCar;
import parking.park.handler.AbstractParkingListener;
import parking.park.utils.ParkingConstants;
import parking.utils.common.console.BreadcrumbPrompt;

/**
 * 전체 주차 차량 리스트 조회
 */
public class ParkingListListener extends AbstractParkingListener {
	public ParkingListListener(List<ParkingCar> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		if (parkingInfoList.size() == 0) {
			ParkingConstants.outPutNoData();
		}

		ParkingConstants.outPutTitle();
		for (ParkingCar tempCar : parkingInfoList) {
			printParkInfo(tempCar);
		}
	}
}
