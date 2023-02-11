package sv.gob.bandesal.blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sv.gob.bandesal.blog.domain.Reader;
import sv.gob.bandesal.blog.repository.ReaderRepository;

@Service
public class ReaderServiceImpl implements IReaderService {

	@Autowired
	ReaderRepository readerRepository;

	@Override
	public List<Reader> getAllReaders() {
		return readerRepository.findAll();
	}

	@Override
	public Reader getEmptyModel() {
		return new Reader();
	}

	@Override
	public Reader saveReader(Reader reader) {
		readerRepository.save(reader);
		return reader;
	}

	@Override
	public Reader getReader(Long id) {
		Optional<Reader> reader =readerRepository.findById(id); 
		return reader.isPresent() ? reader.get() : null;
	}

	@Override
	public void removeReader(Reader reader) {
		readerRepository.delete(reader); 
	}

	@Override
	public List<Reader> getAllReadersNotInBlog(Long id) {
		return readerRepository.getReadersNotInBlog(id);
	}

}
