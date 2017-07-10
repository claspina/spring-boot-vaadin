package net.claspina.demo.backend.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity(name = "groups")
public class Group implements Serializable {

	@Id
	@SequenceGenerator(name="pk_group",sequenceName="seq_groups", allocationSize=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="pk_group")
	@NotNull
	@NotEmpty
	private Integer id;

	@NotNull
	@NotEmpty
	@Size(min = 4, max = 255)
	private String groupName;

	public Group() {
		// An empty constructor is needed for all beans
	}

	public Group(Integer id, String groupName) {
		Objects.requireNonNull(id);
		Objects.requireNonNull(groupName);

		this.id = id;
		this.groupName = groupName;
	}
}
