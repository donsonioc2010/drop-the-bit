package parking.member.exception;

import parking.common.exception.AbstractBaseException;

public class NotFoundUserNo extends AbstractBaseException {
    private static final String MESSAGE = "존재하지 않는 사용자 ID : ";

    public NotFoundUserNo(int no) {
        super(MESSAGE + no);
    }

    public NotFoundUserNo(int no, Throwable cause) {
        super(MESSAGE + no, cause);
    }
}
