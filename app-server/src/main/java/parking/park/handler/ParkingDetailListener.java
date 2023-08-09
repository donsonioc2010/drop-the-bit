/*
package parking.park.handler;

import parking.park.domain.ParkingCar;
import parking.utils.common.console.BreadcrumbPrompt;

import java.util.List;

*/
/**
 * 주차 차량 정보 세부 조회
 *//*

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
*/
