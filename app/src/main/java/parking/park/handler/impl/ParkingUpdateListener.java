package parking.park.handler.impl;

import java.util.List;

import parking.park.domain.ParkingCar;
import parking.park.handler.AbstractParkingListener;
import parking.park.utils.ParkingConstants;
import parking.utils.common.console.BreadcrumbPrompt;

public class ParkingUpdateListener extends AbstractParkingListener {
	public ParkingUpdateListener(List<ParkingCar> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		ParkingCar updateCar = findParkingCarByCarNumber(prompt.getInputString("수정 희망 차량번호 >"));

		if (updateCar == null) {
			ParkingConstants.outPutNoData();
			return;
		}

		int idx = parkingInfoList.indexOf(updateCar);

		String carNumber = null;
		while (true) {
			carNumber = prompt.getInputString("수정희망 차량번호 : ");
			if (this.findParkingCarByCarNumber(carNumber) == null) {
				break;
			}
			System.out.println("이미 입차, 주차 된 차량번호 입니다. 재입력 해주시기 바랍니다.");
		}
		String phoneNumber = prompt.getInputString("수정희망 휴대폰 번호 : ");
		boolean isPayment = prompt.getInputString("정산 여부 (y/N) : ").equalsIgnoreCase("y") ? true : false;

		parkingInfoList.set(idx, ParkingCar.updateParkingCarInfo(updateCar, carNumber, phoneNumber, isPayment));
	}
}
