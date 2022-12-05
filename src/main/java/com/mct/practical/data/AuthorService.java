package com.mct.practical.data;

import com.mct.practical.domain.entities.Author;
import com.mct.practical.domain.repository.AuthorRepository;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author getOne(long id) {
        return authorRepository.getReferenceById(id);
    }

    public Author insert(Author book) {
        return authorRepository.save(book);
    }


    public void update(long id, Author book) {
        Optional<Author> findAuthor = authorRepository.findById(id);
        if (findAuthor.isPresent()) {
            Author mAuthor = findAuthor.get();
            mAuthor.setName(book.getName());
            mAuthor.setEmail(book.getEmail());
            mAuthor.setGender(book.getGender());
            authorRepository.save(mAuthor);
        }
    }

    public void delete(Author book) {
        authorRepository.delete(book);
    }

    public void delete(long id) {
        authorRepository.deleteById(id);
    }
}
