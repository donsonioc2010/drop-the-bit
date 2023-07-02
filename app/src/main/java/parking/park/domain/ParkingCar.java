package parking.park.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import parking.utils.LocalDateFormat;
import parking.utils.common.domain.CsvObject;

/**
 *  * ### 관리 데이터 리스트
 *  * - id : Long
 *  * - CarNumber : String
 *  * - PhoneNumber : String
 *  * - NearInTime\[최근 입차시간\] : LocalDateTime
 *  * - Amount : Int
 *  * - isPayment : Boolean
 */
@Data
@NoArgsConstructor
public class ParkingCar implements Serializable, CsvObject {
	private static final long serialVersionUID = 1L;

	private static long parkingId = 1;
	private long id;
	private String carNumber;
	private String phoneNumber;
	private LocalDateTime parkingStartTime;
	private int amount;
	private boolean isPayment;

	@Builder
	public ParkingCar(long id, String carNumber, String phoneNumber, LocalDateTime parkingStartTime, int amount,
		boolean isPayment) {
		this.id = id;
		this.carNumber = carNumber;
		this.phoneNumber = phoneNumber;
		this.parkingStartTime = parkingStartTime;
		this.amount = amount;
		this.isPayment = isPayment;
	}

	//사용자 생성
	public static ParkingCar createParkingInfo(String carNumber, String phoneNumber, boolean isPayment) {
		return ParkingCar.builder()
			.id(parkingId++)
			.carNumber(carNumber)
			.phoneNumber(phoneNumber)
			.parkingStartTime(LocalDateTime.now())
			.amount(1000)
			.isPayment(isPayment)
			.build();
	}

	public static ParkingCar updateParkingCarInfo(ParkingCar original, String carNumber, String phoneNumber,
		boolean isPayment) {
		return ParkingCar.builder()
			.id(original.getId())
			.carNumber(carNumber)
			.phoneNumber(phoneNumber)
			.parkingStartTime(original.getParkingStartTime())
			.amount(original.getAmount())
			.isPayment(isPayment)
			.build();
	}

	public static String getPaymentStatusToString(boolean isPayment) {
		return isPayment ? "정산완료" : "미정산";
	}

	@Override
	public String toCsvString() {
		return String.format("%d,%s,%s,%s,%d,%s",
			getId(),
			getCarNumber(),
			getPhoneNumber(),
			LocalDateFormat.getTimeToStringByLocalDateTime(getParkingStartTime()),
			getAmount(),
			isPayment()
		);
	}

	public static ParkingCar fromCsvString(final String csv) {
		String[] csvSplit = csv.split(",");

		ParkingCar info = ParkingCar.builder()
			.id(Long.parseLong(csvSplit[0]))
			.carNumber(csvSplit[1])
			.phoneNumber(csvSplit[2])
			.parkingStartTime(LocalDateFormat.getLocalDateTimeByString(csvSplit[3]))
			.amount(Integer.parseInt(csvSplit[4]))
			.isPayment(Boolean.valueOf(csvSplit[5]))
			.build();

		if (info.getId() >= parkingId) {
			parkingId = info.getId() + 1L;
		}

		return info;
	}
}
