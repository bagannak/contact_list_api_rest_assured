package user.createuser.response;

import lombok.*;

@Builder
@Data
@NoArgsConstructor@AllArgsConstructor
public class CreateUserResponseBody{
	private User user;
	private String token;
	private int statuscode;
}