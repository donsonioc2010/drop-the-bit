package parking.park.handler.impl;

import java.util.List;

import parking.park.domain.ParkingCar;
import parking.park.handler.AbstractParkingListener;
import parking.park.utils.ParkingConstants;
import parking.session.LoginSession;
import parking.utils.common.console.BreadcrumbPrompt;

public class ParkingDeleteListener extends AbstractParkingListener {
	public ParkingDeleteListener(List<ParkingCar> list) {
		super(list);
	}

	@Override
	public void service(BreadcrumbPrompt prompt) {
		if (LoginSession.getInstance().isAbleUseFunctionByAdminAndStaff()) {
			ParkingCar removeCar = findParkingCarByCarNumber(prompt.getInputString("삭제희망 차량 ID >"));

			if (removeCar == null) {
				ParkingConstants.outPutNoData();
				return;
			}
			parkingInfoList.remove(removeCar);
		}

		System.out.println("[INFO] 직원또는 관리자만 데이터의 삭제가 가능합니다. 로그인 해주시기 바랍니다");
	}
}
