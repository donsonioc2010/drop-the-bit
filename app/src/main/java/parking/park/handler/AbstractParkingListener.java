package parking.park.handler;

import java.util.List;

import parking.park.domain.ParkingCar;
import parking.utils.LocalDateFormat;
import parking.utils.listener.ActionListener;

public abstract class AbstractParkingListener implements ActionListener {

	protected List<ParkingCar> parkingInfoList;

	public AbstractParkingListener(List<ParkingCar> list) {
		this.parkingInfoList = list;
	}

	protected static void printParkInfo(ParkingCar parkInfo) {
		System.out.printf("%04d\t%s\t%s\t%s\t%s\t%s\n",
			parkInfo.getId(),
			parkInfo.getCarNumber(),
			parkInfo.getPhoneNumber(),
			LocalDateFormat.getTimeToStringByLocalDateTime(parkInfo.getParkingStartTime()),
			parkInfo.getAmount(),
			ParkingCar.getPaymentStatusToString(parkInfo.isPayment())
		);
	}

	protected ParkingCar findParkingCarByCarNumber(String carNumber) {
		return parkingInfoList.stream()
			.filter(car -> car.getCarNumber().endsWith(carNumber))
			.findFirst()
			.orElse(null);
	}

}
