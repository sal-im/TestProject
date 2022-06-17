package tn.esprit.entites;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.enums.UserRole;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3, max = 50)
	private String firstName;

	@NotNull
	@Size(min = 3, max = 50)
	private String lastName;

	@NotNull
	@Column(length = 50, unique = true)
	private String username;

	@NotNull
	@Email
	@Column(length = 20, unique = true)
	private String email;

	@NotNull
	@Size(min = 6, max = 100)
	private String password;

	@Enumerated(EnumType.STRING)
	private UserRole role;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonBackReference(value="commands-movement")
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Command> commands;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	@JsonBackReference(value="products-movement")
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<Product> products;
}
