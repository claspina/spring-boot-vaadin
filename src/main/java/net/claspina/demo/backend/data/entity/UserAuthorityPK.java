package net.claspina.demo.backend.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UserAuthorityPK implements Serializable {

	@ManyToOne
    @JoinColumn(name="username", nullable=false)
	private User user;

	@NotNull
	@NotEmpty
	@Size(min = 4, max = 255)
	private String authority;

	public UserAuthorityPK() {
		// An empty constructor is needed for all beans
	}

	public UserAuthorityPK(User user, String authority) {
		Objects.requireNonNull(user);
		Objects.requireNonNull(authority);

		this.user = user;
		this.authority = authority;
	}
}
