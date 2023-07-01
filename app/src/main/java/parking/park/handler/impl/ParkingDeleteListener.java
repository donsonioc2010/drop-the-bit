package parking.park.handler.impl;

import java.util.List;

import parking.park.domain.ParkingCar;
import parking.park.handler.AbstractParkingListener;
import parking.park.utils.ParkingConstants;
import parking.utils.common.console.BreadcrumbPrompt;

public class ParkingDeleteListener extends AbstractParkingListener {
	public ParkingDeleteListener(List<ParkingCar> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		ParkingCar removeCar = findParkingCarByCarNumber(prompt.getInputString("삭제희망 차량 ID >"));

		if (removeCar == null) {
			ParkingConstants.outPutNoData();
			return;
		}
		parkingInfoList.remove(removeCar);
	}
}
