package parking.domain;

import java.time.LocalDateTime;

/**
 * * ### 관리 데이터 리스트
 * * - id : Long
 * * - CarNumber : String
 * * - PhoneNumber : String
 * * - NearInTime\[최근 입차시간\] : LocalDateTime
 * * - Amount : Int
 * * - isPayment : Boolean
 */
public class ParkingCar {
    private long id;
    private String carNumber;
    private String phoneNumber;
    private LocalDateTime parkingStartTime;
    private int amount;
    private boolean isPayment;

    public ParkingCar() {
    }

    public ParkingCar(long id, String carNumber, String phoneNumber, LocalDateTime parkingStartTime, int amount,
                      boolean isPayment) {
        this.id = id;
        this.carNumber = carNumber;
        this.phoneNumber = phoneNumber;
        this.parkingStartTime = parkingStartTime;
        this.amount = amount;
        this.isPayment = isPayment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getParkingStartTime() {
        return parkingStartTime;
    }

    public void setParkingStartTime(LocalDateTime parkingStartTime) {
        this.parkingStartTime = parkingStartTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isPayment() {
        return isPayment;
    }

    public void setPayment(boolean payment) {
        isPayment = payment;
    }
}
