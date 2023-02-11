package sv.gob.bandesal.blog.services;

import java.util.List;

import sv.gob.bandesal.blog.domain.Reader;

public interface IReaderService {

	public List<Reader> getAllReaders();
	public Reader getEmptyModel();
	public Reader getReader(Long id);
	public Reader saveReader(Reader reader);
	public void removeReader(Reader reader);
}
