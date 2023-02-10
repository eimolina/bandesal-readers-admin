package sv.gob.bandesal.blog.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "APPLICATION_USER")
public class ApplicationUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	@JsonView
	private Long id;

	@Column(name = "NAME", length = 50, nullable = false)
	@JsonView
	String name;

	@Column(name = "USERNAME", length = 50, nullable = false)
	@JsonView
	String username;

	@Column(name = "PASSWORD", length = 300, nullable = false)
	@JsonView
	String password;

	@Column(name = "SESSIONCODE", length = 50, nullable = true)
	@JsonView
	String sessioncode;
	
	@Column(name = "STATUS", length = 20, nullable = true)
	@JsonView
	String status;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<UserRole> userroles;
}
