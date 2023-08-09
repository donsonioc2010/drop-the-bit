package parking.common;

import parking.common.exception.AbstractBaseException;

public class InitException extends AbstractBaseException {
    private static final String MESSAGE = "Init 초기화 중 오류 발생";

    public InitException(Exception e) {
        super(MESSAGE, e);
    }
}
