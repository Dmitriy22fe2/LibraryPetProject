package org.example.controller;

import jakarta.validation.Valid;
import org.example.model.Book;
import org.example.model.Person;
import org.example.service.BookService;
import org.example.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("books")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String books(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "/books/index";
    }

    @GetMapping("/new")
    public String createBook(Model model) {
        model.addAttribute("book", new Book());
        return "/books/new";
    }

    @PostMapping("/new")
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) return "/books/new";
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findOneById(id));
        model.addAttribute("bookOwner", bookService.bookOwner(id));
        model.addAttribute("person", new Person());
        model.addAttribute("people", peopleService.findAll());
        return "/books/show";
    }

    @PatchMapping("/{id}")
    public String assignABook(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookService.assignBook(id, person.getPersonid());
        return "redirect:/books/" + id;
    }

    @PutMapping("/{id}")
    public String releaseABook(@PathVariable("id") int id, @ModelAttribute("person") @Valid Person person) {
        bookService.releaseBook(id);
        return "redirect:/books/" + id;
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findOneById(id));
        return "/books/edit";
    }

    @PatchMapping("/{id}/edit")
    public String editBook(@ModelAttribute("book") @Valid Book book, BindingResult result, @PathVariable("id") int id, Model model) {
        book.setBookid(id);
        if (result.hasErrors()) {
            model.addAttribute("book", book);
            return "/books/edit";
        }
        bookService.update(book);
        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteABook(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
