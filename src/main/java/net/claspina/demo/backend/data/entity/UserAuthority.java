package net.claspina.demo.backend.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "authorities")
public class UserAuthority implements Serializable {

	@EmbeddedId
	private UserAuthorityPK authorityPK;

	public UserAuthority() {
		// An empty constructor is needed for all beans
	}

	public UserAuthority(UserAuthorityPK authorityPK) {
		Objects.requireNonNull(authorityPK);

		this.authorityPK = authorityPK;
	}
}
