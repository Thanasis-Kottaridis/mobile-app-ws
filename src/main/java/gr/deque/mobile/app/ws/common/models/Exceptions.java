package gr.deque.mobile.app.ws.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exceptions {
   private int total;
   private List<Errors> errors;

}

