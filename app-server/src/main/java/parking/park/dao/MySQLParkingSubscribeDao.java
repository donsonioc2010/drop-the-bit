package parking.park.dao;

import org.apache.ibatis.session.SqlSessionFactory;

public class MySQLParkingSubscribeDao implements ParkingSubscribeDao {
    SqlSessionFactory sqlSessionFactory;

    public MySQLParkingSubscribeDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
