package parking.member.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import parking.member.domain.Member;

import java.util.List;
import java.util.Optional;

public class MySQLMemberDao implements MemberDao {
    SqlSessionFactory sqlSessionFactory;
    private static final String NAMESPACE = "parking.member.dao.MemberDao.";

    public MySQLMemberDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Member m) {
        sqlSessionFactory.openSession(false).insert(NAMESPACE + "insert", m);
    }

    @Override
    public int update(Member m) {
        return sqlSessionFactory.openSession(false).update(NAMESPACE + "update", m);
    }

    @Override
    public int deleteById(long id) {
        return sqlSessionFactory.openSession(false).delete(NAMESPACE + "deleteById", id);
    }

    @Override
    public int deleteByMember(Member m) {
        return sqlSessionFactory.openSession(false).delete(NAMESPACE + "deleteByMember", m);
    }

    @Override
    public List<Member> findAll() {
        return sqlSessionFactory.openSession(false)
                .selectList(NAMESPACE + "findAll");
    }

    @Override
    public int findByUserIdCount(String userId) {
        return sqlSessionFactory.openSession(false)
                .selectOne(NAMESPACE + "findByUserIdCount", userId);
    }

    @Override
    public Optional<Member> findById(long id) {
        return Optional.ofNullable(
                sqlSessionFactory.openSession(false).selectOne(NAMESPACE + "findById", id)
        );
    }

    @Override
    public Optional<Member> findByUserIdAndPassword(Member m) {
        return Optional.ofNullable(
                sqlSessionFactory.openSession(false).selectOne(NAMESPACE + "findByUserIdAndPassword", m)
        );
    }
}
