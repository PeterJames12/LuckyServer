package org.luckyether.server.controller;

import org.luckyether.server.dto.ExceptionDTO;
import org.luckyether.server.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Andre on July 2017.
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ExceptionDTO handleBadRequestException(RequestException ex) {
        return handleBaseException(ex);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ExceptionDTO handleUnauthorizedException(UnauthorizedException ex) {
        return handleBaseException(ex);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ExceptionDTO handleForbiddenException(ForbiddenException ex) {
        return handleBaseException(ex);
    }

    @ExceptionHandler(InternalServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionDTO handleInternalException(InternalServerException ex) {
        return handleBaseException(ex);
    }

    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ExceptionDTO handleBaseException(BaseException ex) {
        return new ExceptionDTO(ex.getCode().getCode(), ex.getCode().toString());
    }
}
