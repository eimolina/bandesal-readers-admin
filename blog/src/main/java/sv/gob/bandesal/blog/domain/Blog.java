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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "BLOGS")
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	@JsonView
	private Long id;

	@Column(name = "TITLE", length = 50, nullable = false)
	@JsonView
	@NotNull(message = "El campo titulo es requerido")
	@NotBlank(message = "El campo titulo es requerido")
	@Size(min = 1, max = 50, message = "El titulo debe tener como maximo 50 caracteres")
	private String title;

	@Column(name = "DESCRIPTION", length = 4000, nullable = false)
	@JsonView
	@NotNull(message = "La descripción nombre es requerido")
	@NotBlank(message = "El campo descripción es requerido")
	@Size(min = 1, max = 4000, message = "La descripción tiene un maximo de 4000 caracteres")
	private String description;
	
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BlogReader> blogsreaders;
}
