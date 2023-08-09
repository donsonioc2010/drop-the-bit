package parking.member.exception;

import parking.common.exception.AbstractBaseException;

public class IsNotAbleDeleteAdmin extends AbstractBaseException {
    private static final String MESSAGE = "관리자 계정의 삭제는 불가능합니다";

    public IsNotAbleDeleteAdmin() {
        super(MESSAGE);
    }
}
