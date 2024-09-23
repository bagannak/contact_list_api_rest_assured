package user.getuser.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserResponseBody{
	private String firstName;
	private String lastName;
	@JsonProperty("__v")
	private int v;
	@JsonProperty("_id")
	private String id;
	private String email;
}