package org.luckyether.server.exception;

/**
 * @author Andre on July 2017.
 */
public class InternalServerException extends BaseException {

    private static final long serialVersionUID = 3635219033961737501L;

    public InternalServerException(int code, String description) {
        super(code, description);
    }
}
