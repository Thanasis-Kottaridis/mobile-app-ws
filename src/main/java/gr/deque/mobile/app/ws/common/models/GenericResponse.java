package gr.deque.mobile.app.ws.common.models;

import gr.deque.mobile.app.ws.common.exceptions.BaseExceptionHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static gr.deque.mobile.app.ws.common.models.BusinessError.BAD_DATA;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {
    private T data;
    private ErrorResponse exceptions;
    private Pagination pagination;

    public static GenericResponse<SuccessOperation> buildSuccess(boolean result) {
        return GenericResponse.<SuccessOperation>builder().data(SuccessOperation.builder().isSuccess(result).build()).build();
    }

    public static GenericResponse<SuccessOperation> buildSuccess(Runnable runnable) {
        runnable.run();
        return buildSuccess(true);
    }

    public static <T> GenericResponse<T> fromErrorDetails(ErrorDetails... errorDetails) {
        return GenericResponse.<T>builder()
                .exceptions(ErrorResponse.builder().errors(Arrays.stream(errorDetails).collect(Collectors.toList())).build())
                .build();
    }

    public static <T> GenericResponse<T> fromBusinessError(String title, String description, BusinessError error) {
        return GenericResponse.<T>builder()
                .exceptions(
                        ErrorResponse.builder()
                                .errors(List.of(
                                        ErrorDetails.builder()
                                                .title(title)
                                                .description(description)
                                                .code(error.getHttpStatus().value())
                                                .exceptionCode(error.getBusinessCode())
                                                .datetime(ZonedDateTime.now())
                                                .build()))
                                .build())
                .build();
    }

    public static <T> GenericResponse<T> fromBusinessError(BusinessError error) {
        return fromBusinessError(
                BaseExceptionHandler.DEFAULT_ERROR_TITLE,
                BaseExceptionHandler.DEFAULT_ERROR_MESSAGE,
                error
        );
    }


        public static <T> GenericResponse<T> fromData(T data) {
        return GenericResponse.<T>builder()
                .data(data)
                .build();
    }

}


