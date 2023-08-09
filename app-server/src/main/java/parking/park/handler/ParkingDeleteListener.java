/*
package parking.park.handler;

import parking.park.domain.ParkingCar;
import parking.session.LoginSession;
import parking.utils.common.console.BreadcrumbPrompt;
import parking.utils.log.Log;

import java.util.List;

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
            System.out.println(Log.info(Log.getFontRedToKeyword(removeCar.getCarNumber()) + " 차량이 출차로 인한 데이터 삭제"));
        } else {
            System.out.println(Log.error("직원또는 관리자만 데이터의 삭제가 가능합니다. 로그인 해주시기 바랍니다"));
        }
    }
}
*/
