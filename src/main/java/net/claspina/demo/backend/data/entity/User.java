package net.claspina.demo.backend.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "users")
public class User implements Serializable {

	@Id
	@NotNull
	@NotEmpty
	@Size(max = 255)
	private String username;

	@NotNull
	@NotEmpty
	@Size(min = 4, max = 255)
	private String password;

	@NotNull
	private boolean enabled = false;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "authorityPK.user", cascade= CascadeType.ALL)
	private List<UserAuthority> authorities = new ArrayList<>();

	public User() {
		// An empty constructor is needed for all beans
	}

	public User(String username, String password, boolean enabled) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		Objects.requireNonNull(enabled);

		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
}
