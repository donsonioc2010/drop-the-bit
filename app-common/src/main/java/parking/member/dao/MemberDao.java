package parking.member.dao;

import parking.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberDao {
    void insert(Member m);

    int update(Member m);

    int deleteById(long id);

    int deleteByMember(Member m);

    List<Member> findAll();

    int findByUserIdCount(String userId);

    Optional<Member> findById(long id);

    Optional<Member> findByUserIdAndPassword(Member m);
}
