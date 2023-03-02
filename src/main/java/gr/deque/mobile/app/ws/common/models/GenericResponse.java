package gr.deque.mobile.app.ws.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {
    private T data;
    private Exceptions exceptions;
    private Pagination pagination;
}


