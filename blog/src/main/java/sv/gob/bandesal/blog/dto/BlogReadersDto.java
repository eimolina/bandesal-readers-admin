package sv.gob.bandesal.blog.dto;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlogReadersDto {
	
	private final Long id;
	private final String title;
	private final String description;
	private final Long readers;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlogReadersDto)) return false;
        BlogReadersDto that = (BlogReadersDto) o;
        return Objects.equals(getTitle(), that.getTitle()) &&
               Objects.equals(getDescription(), that.getDescription()) &&
               Objects.equals(getReaders(), that.getReaders());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(
            getTitle(),
            getDescription(),
            getReaders()
        );
    }

}
