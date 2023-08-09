package parking.park.dao;

import org.apache.ibatis.session.SqlSessionFactory;

public class MySQLParkingDao implements ParkingDao {
    SqlSessionFactory sqlSessionFactory;

    public MySQLParkingDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
