package org.luckyether.server.dto;

/**
 * @author Andre on July 2017.
 */
public class ExceptionDTO {

    private int code;
    private String error;

    public ExceptionDTO() {
    }

    public ExceptionDTO(int code, String error) {
        super();
        this.code = code;
        this.error = error;
    }
}
