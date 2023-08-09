package parking.park.dao;

import parking.park.domain.ParkSubscribe;

import java.util.List;
import java.util.Optional;

public interface ParkingSubscribeDao {
    void insert(ParkSubscribe p);

    int updateById(ParkSubscribe p);

    int updateByCarNumber(ParkSubscribe p);

    int deleteById(long id);

    int deleteByCarNumber(String carNumber);

    List<ParkSubscribe> findAll();

    List<ParkSubscribe> findExpiredList();

    List<ParkSubscribe> findNotExpiredList();

    Optional<ParkSubscribe> findById(long id);

    Optional<ParkSubscribe> findByCarNumber(String carNumber);

    List<ParkSubscribe> findByPhoneNumber(String phoneNumber);

}
