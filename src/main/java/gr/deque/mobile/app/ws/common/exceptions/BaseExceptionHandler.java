package gr.deque.mobile.app.ws.common.exceptions;

import gr.deque.mobile.app.ws.common.models.ErrorDetails;
import gr.deque.mobile.app.ws.common.models.ErrorResponse;
import gr.deque.mobile.app.ws.common.models.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.ZonedDateTime;
import java.util.List;

import static gr.deque.mobile.app.ws.common.models.BusinessError.BAD_DATA;
import static gr.deque.mobile.app.ws.common.models.BusinessError.GENERIC_ERROR;
import static org.springframework.http.HttpStatus.*;

@Slf4j // this annotation provides us with logger => log
@ControllerAdvice
public class BaseExceptionHandler {
    public static final String DEFAULT_ERROR_TITLE = "Error";
    public static final String DEFAULT_ERROR_MESSAGE = "Something went wrong.";

    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public GenericResponse<Object> handleException(Exception exception, WebRequest request) {
        if (exception instanceof NullPointerException) {
            log.error(exception.getMessage(), exception);
        } else {
            log.error(exception.getMessage());
        }

        var body = GenericResponse.builder()
                .exceptions(
                        ErrorResponse.builder()
                                .errors(List.of(
                                        ErrorDetails.builder()
                                                .code(GENERIC_ERROR.getHttpStatus().value())
                                                .title(exception.getClass().getSimpleName())
                                                .description(request.getDescription(false))
                                                .exceptionCode(GENERIC_ERROR.getBusinessCode())
                                                .datetime(ZonedDateTime.now())
                                                .build()))
                                .build())
                .build();

        return body;
    }


    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler({
            HttpMediaTypeNotSupportedException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            MissingRequestHeaderException.class,
            MissingServletRequestParameterException.class,
            HttpMediaTypeNotAcceptableException.class,
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class
    })
    public GenericResponse<Object> handleBadRequests(final Exception ex, WebRequest request) {
        log.warn(ex.getMessage());
        return GenericResponse.builder()
                .exceptions(
                        ErrorResponse.builder()
                                .errors(List.of(
                                        ErrorDetails.builder()
                                                .title(ex.getClass().getSimpleName())
                                                .description(request.getDescription(false))
                                                .code(BAD_DATA.getHttpStatus().value())
                                                .exceptionCode(BAD_DATA.getBusinessCode())
                                                .datetime(ZonedDateTime.now())
                                                .build()))
                                .build())
                .build();
    }
}
