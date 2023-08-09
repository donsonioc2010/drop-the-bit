package parking.park.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import parking.park.domain.ParkSubscribe;

import java.util.List;
import java.util.Optional;

public class MySQLParkingSubscribeDao implements ParkingSubscribeDao {
    private static final String NAMESPACE = "parking.park.dao.ParkingSubscribeDao.";
    SqlSessionFactory sqlSessionFactory;

    public MySQLParkingSubscribeDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(ParkSubscribe p) {
        sqlSessionFactory.openSession(false)
                .insert(NAMESPACE + "insert", p);
    }

    @Override
    public int updateById(ParkSubscribe p) {
        return sqlSessionFactory.openSession(false)
                .update(NAMESPACE + "updateById", p);
    }

    @Override
    public int updateByCarNumber(ParkSubscribe p) {
        return sqlSessionFactory.openSession(false)
                .update(NAMESPACE + "updateByCarNumber", p);
    }

    @Override
    public int deleteById(long id) {
        return sqlSessionFactory.openSession(false)
                .delete(NAMESPACE + "deleteById", id);
    }

    @Override
    public int deleteByCarNumber(String carNumber) {
        return sqlSessionFactory.openSession(false)
                .delete(NAMESPACE + "deleteByCarNumber", carNumber);
    }

    @Override
    public List<ParkSubscribe> findAll() {
        return sqlSessionFactory.openSession(false)
                .selectList(NAMESPACE + "findAll");
    }

    @Override
    public List<ParkSubscribe> findExpiredList() {
        return sqlSessionFactory.openSession(false)
                .selectList(NAMESPACE + "findExpiredList");
    }

    @Override
    public List<ParkSubscribe> findNotExpiredList() {
        return sqlSessionFactory.openSession(false)
                .selectList(NAMESPACE + "findNotExpiredList");
    }

    @Override
    public Optional<ParkSubscribe> findById(long id) {
        return Optional.ofNullable(
                sqlSessionFactory.openSession(false)
                        .selectOne(NAMESPACE + "findById", id)
        );
    }

    @Override
    public Optional<ParkSubscribe> findByCarNumber(String carNumber) {
        return Optional.ofNullable(
                sqlSessionFactory.openSession(false)
                        .selectOne(NAMESPACE + "findByCarNumber", carNumber)
        );
    }

    @Override
    public List<ParkSubscribe> findByPhoneNumber(String phoneNumber) {
        return sqlSessionFactory.openSession(false)
                .selectList(NAMESPACE + "findByPhoneNumber", phoneNumber);
    }
}
