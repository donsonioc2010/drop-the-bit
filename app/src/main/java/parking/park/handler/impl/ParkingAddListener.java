package parking.park.handler.impl;

import java.util.List;

import parking.park.domain.ParkingCar;
import parking.park.handler.AbstractParkingListener;
import parking.utils.common.console.BreadcrumbPrompt;

/**
 * 차량 추가
 */
public class ParkingAddListener extends AbstractParkingListener {
	public ParkingAddListener(List<ParkingCar> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		String carNumber = null;
		while (true) {
			carNumber = prompt.getInputString("차량번호 입력 : ");
			if (this.findParkingCarByCarNumber(carNumber) == null) {
				break;
			}
			System.out.println("이미 입차, 주차 된 차량번호 입니다.");
		}

		String phoneNumber = prompt.getInputString("전화번호 입력 :");
		boolean isPayment = prompt.getInputString("계산 여부 입력 ?(y/N) : ").equalsIgnoreCase("y") ? true : false;

		parkingInfoList.add(
			ParkingCar.createParkingInfo(carNumber, phoneNumber, isPayment)
		);
	}
}
