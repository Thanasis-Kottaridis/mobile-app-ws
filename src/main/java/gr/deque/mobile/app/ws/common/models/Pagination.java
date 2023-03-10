package gr.deque.mobile.app.ws.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagination {
    private int next;
    private int prev;
    private int self;
    private int total;

}
