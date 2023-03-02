package gr.deque.mobile.app.ws.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsRequestDto {
    @NotNull(message = "email is mandatory")
    @Email(message = "Not a valid Email")
    private String email;
    @NotNull(message = "FirstName is mandatory")
    private String firstName;
    @NotNull(message = "lastName is mandatory")
    private String lastName;
    @NotNull(message = "password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 chars")
    private String password;
}
