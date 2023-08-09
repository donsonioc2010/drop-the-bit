package parking.member.type;

import lombok.Getter;

import java.util.Arrays;

// 직원, 관리자 정비사만 계정이 존재하면 된다.
@Getter
public enum MemberType {
    STAFF,
    ADMIN,
    TECHNICIAN;

    public static MemberType defaultStaffValueOf(String type) {
        return Arrays.stream(MemberType.values()).filter(memberType -> memberType.name().equals(type)).findFirst().orElse(STAFF);
    }

    public static boolean isADMIN(MemberType diff) {
        return ADMIN.equals(diff);
    }

    public static boolean isSTAFF(MemberType diff) {
        return STAFF.equals(diff);
    }
}