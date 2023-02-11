package sv.gob.bandesal.blog.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReaderRequest {
	private Long id;

	@NotNull(message = "Nombre es requerido")
	@NotBlank(message = "Nombre es requerido")
	@Max(message = "El maximo debe ser 8 caracteres", value = 6)
	private String name;
}
