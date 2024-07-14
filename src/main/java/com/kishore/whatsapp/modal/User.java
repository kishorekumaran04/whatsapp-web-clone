package com.kishore.whatsapp.modal;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "full_name")
	private String fullName;
	private String email;
	@Column(name = "profile_picture")
	private String profilePicture;
	private String password;
	
	@Override
	public int hashCode() {
		return Objects.hash(email, fullName, id, password, profilePicture);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(fullName, other.fullName)
				&& Objects.equals(id, other.id) && Objects.equals(password, other.password)
				&& Objects.equals(profilePicture, other.profilePicture);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", email=" + email + ", profilePicture=" + profilePicture
				+ ", password=" + password + "]";
	}
	
	
//	@OneToMany(mappedBy = "user", cascade= CascadeType.ALL)
//	private List<Notification> notifications = new ArrayList<>();
	
	

}
