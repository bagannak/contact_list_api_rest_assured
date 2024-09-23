package user.createuser.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor@AllArgsConstructor
public class User{
	private String firstName;
	private String lastName;
	@JsonProperty("__v")
	private int v;
	@JsonProperty("_id")
	private String id;
	private String email;
}