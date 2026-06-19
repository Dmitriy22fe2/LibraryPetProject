package org.example.service;

import org.example.exception.BookNotFoundException;
import org.example.exception.PersonNotFoundException;
import org.example.model.Book;
import org.example.model.Person;
import org.example.repository.BookRepository;
import org.example.repository.PeopleRepository;
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
        Book existingBook = bookRepository.findById(book.getBookid()).orElseThrow(() -> new BookNotFoundException("Книга не найдена!"));
        existingBook.setName(book.getName());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setYear(book.getYear());
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public Person bookOwner (int bookid) {
        return bookRepository.findById(bookid).orElseThrow(() -> new BookNotFoundException("Книга не айдена")).getReader();
    }

    @Transactional
    public void assignBook(int bookid, int personid) {
        bookRepository.findById(bookid).orElseThrow(() -> new BookNotFoundException("Книга не найдена"))
                .setReader(peopleRepository.findById(personid).orElseThrow(() -> new PersonNotFoundException("Читатель не найден")));
    }

    @Transactional
    public void releaseBook(int bookid) {
        bookRepository.findById(bookid).orElseThrow(() -> new BookNotFoundException("Книга не найдена")).setReader(null);
    }
}
