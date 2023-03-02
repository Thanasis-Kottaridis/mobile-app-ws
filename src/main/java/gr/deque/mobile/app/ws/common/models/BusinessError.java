package gr.deque.mobile.app.ws.common.models;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum BusinessError {
    /**
     * INTERNAL_SERVER_ERROR (500)
     */
    GENERIC_ERROR("GENERIC_ERROR", INTERNAL_SERVER_ERROR),
    /**
     * BAD_REQUEST (400)
     */
    BAD_DATA("BAD_DATA", BAD_REQUEST),
    /**
     * NOT_FOUND (404)
     */
    MISSING_RESOURCE("NOT_FOUND", NOT_FOUND);


    private final String businessCode;
    private final HttpStatus httpStatus;

    BusinessError(String businessCode, HttpStatus httpStatus) {
        this.businessCode = businessCode;
        this.httpStatus = httpStatus;
    }

    }
