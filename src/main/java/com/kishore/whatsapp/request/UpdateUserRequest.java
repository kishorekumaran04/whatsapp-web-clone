package com.kishore.whatsapp.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateUserRequest {
	
	private String fullName;
	private String profilePicture;
	
	@Override
	public String toString() {
		return "UpdateUserRequest [fullName=" + fullName + ", profilePicture=" + profilePicture + "]";
	}
}
