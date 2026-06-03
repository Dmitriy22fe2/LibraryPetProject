package org.example.services;

import org.example.model.Book;
import org.example.model.Person;
import org.example.repositories.BookRepository;
import org.example.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BookService(BookRepository bookRepository, PeopleRepository peopleRepository) {
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOneById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> findAllByReaderId(int personid) {
        return bookRepository.findAllByReader_Personid(personid);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Person bookOwner (int bookid) {
        return bookRepository.findById(bookid).get().getReader();
    }

    @Transactional
    public void assignBook(int bookid, int personid) {
        bookRepository.findById(bookid).get().setReader(peopleRepository.findById(personid).get());
    }

    @Transactional
    public void releaseBook(int bookid) {
        bookRepository.findById(bookid).get().setReader(null);
    }
}
