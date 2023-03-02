package gr.deque.mobile.app.ws.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Errors {
    private int code;
    private String debug;
    private String title;
    private String description;
    private String redirect;
    private String datetime;
    private String exceptionCode;
}
