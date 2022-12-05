package com.mct.practical.data;

import com.mct.practical.domain.entities.Book;
import com.mct.practical.domain.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book getOne(long id) {
        return bookRepository.getReferenceById(id);
    }

    public Book insert(Book book) {
        return bookRepository.save(book);
    }

    public void update(long id, Book book) {
        Optional<Book> findBook = bookRepository.findById(id);
        if (findBook.isPresent()) {
            Book mBook = findBook.get();
            mBook.setAuthorId(book.getAuthorId());
            mBook.setName(book.getName());
            mBook.setPrice(book.getPrice());
            mBook.setQuantity(book.getQuantity());
            bookRepository.save(mBook);
        }
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }

    public void delete(long id) {
        bookRepository.deleteById(id);
    }
}
