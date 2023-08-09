package parking.member.exception;

import parking.common.exception.AbstractBaseException;

//생각해보니 지금단게에서 Exception 쓰면 안되네...?
public class InvalidUserException extends AbstractBaseException {
    private static final String MESSAGE = "일치하지 않는 사용자 정보입니다";

    public InvalidUserException() {
        super(MESSAGE);
    }
}
