package org.luckyether.server.exception;

/**
 * @author Andre on July 2017.
 */
public class ForbiddenException extends BaseException {

    private static final long serialVersionUID = -6955452383921571349L;

    public ForbiddenException(int code, String description) {
        super(code, description);
    }
}
