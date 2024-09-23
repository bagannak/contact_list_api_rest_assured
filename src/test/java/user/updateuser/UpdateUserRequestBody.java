package user.updateuser;

import lombok.Data;

@Data
public class UpdateUserRequestBody{
	private String firstName;
	private String lastName;
	private String password;
	private String email;
}