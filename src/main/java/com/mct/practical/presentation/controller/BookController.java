package com.mct.practical.presentation.controller;

import com.mct.practical.data.AuthorService;
import com.mct.practical.data.BookService;
import com.mct.practical.domain.entities.Author;
import com.mct.practical.domain.entities.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/book/list");
        mav.addObject("books", bookService.getAll());
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView add(@ModelAttribute("book") Book book) {
        List<Author> authors = authorService.getAll();
        ModelAndView mav = new ModelAndView("/book/add");
        mav.addObject("book", book);
        mav.addObject("authors", authors);
        return mav;
    }

    @PostMapping("/add")
    public ModelAndView addPost(@Valid @ModelAttribute("book") Book book, @NotNull BindingResult br) {
        if (br.hasErrors()) {
            return add(book);
        }
        bookService.insert(book);
        return new ModelAndView("redirect:/book/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable long id) {
        ModelAndView mav = new ModelAndView("/book/edit");
        mav.addObject("book", bookService.getOne(id));
        mav.addObject("authors", authorService.getAll());
        return mav;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editPost(@Valid @ModelAttribute("book") Book book, @NotNull BindingResult br, @PathVariable long id) {
        if (br.hasErrors()) {
            ModelAndView mav = new ModelAndView("/book/edit");
            mav.addObject("book", book);
            mav.addObject("authors", authorService.getAll());
            return mav;
        }
        bookService.update(id, book);
        return new ModelAndView("redirect:/book/list");
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable long id) {
        bookService.delete(id);
        return new ModelAndView("redirect:/book/list");
    }

}
