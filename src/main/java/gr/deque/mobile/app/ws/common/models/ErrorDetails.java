package gr.deque.mobile.app.ws.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    private int code;
    private String debug;
    private String title;
    private String description;
    private String redirect;
    private ZonedDateTime datetime;
    private String exceptionCode;
}
