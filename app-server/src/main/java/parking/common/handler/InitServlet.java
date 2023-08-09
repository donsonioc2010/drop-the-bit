package parking.common.handler;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import parking.common.InitException;
import parking.common.util.SqlSessionFactoryProxy;
import parking.member.dao.MemberDao;
import parking.member.dao.MySQLMemberDao;
import parking.park.dao.MySQLParkingDao;
import parking.park.dao.MySQLParkingSubscribeDao;
import parking.park.dao.ParkingDao;
import parking.park.dao.ParkingSubscribeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(
        value = "/init",
        loadOnStartup = 1
)
public class InitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static SqlSessionFactory sqlSessionFactory;
    private static MemberDao memberDao;
    private static ParkingDao parkingDao;
    private static ParkingSubscribeDao parkingSubscribeDao;

    @Override
    public void init() throws ServletException {
        System.out.println("InitServlet.Init()");
        try {
            this.sqlSessionFactory = new SqlSessionFactoryProxy(
                    new SqlSessionFactoryBuilder()
                            .build(Resources.getResourceAsStream("infra/config/db/mybatis-config.xml"))
            );

            this.memberDao = new MySQLMemberDao(this.sqlSessionFactory);
            this.parkingDao = new MySQLParkingDao(this.sqlSessionFactory);
            this.parkingSubscribeDao = new MySQLParkingSubscribeDao(this.sqlSessionFactory);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InitException(e);
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static MemberDao getMemberDao() {
        return memberDao;
    }

    public static ParkingDao getParkingDao() {
        return parkingDao;
    }

    public static ParkingSubscribeDao getParkingSubscribeDao() {
        return parkingSubscribeDao;
    }
}
