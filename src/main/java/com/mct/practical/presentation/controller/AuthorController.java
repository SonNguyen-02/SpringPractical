package com.mct.practical.presentation.controller;

import com.mct.practical.data.AuthorService;
import com.mct.practical.domain.entities.Author;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("/author/list");
        mav.addObject("authors", authorService.getAll());
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("/author/add");
        mav.addObject("author", new Author());
        return mav;
    }

    @PostMapping("/add")
    public ModelAndView addPost(@Valid @ModelAttribute("author") Author author, @NotNull BindingResult br) {
        if (br.hasErrors()) {
            return new ModelAndView("/author/add");
        }
        authorService.insert(author);
        return new ModelAndView("redirect:/author/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable long id) {
        ModelAndView mav = new ModelAndView("/author/edit");
        mav.addObject("author", authorService.getOne(id));
        return mav;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editPost(@Valid @ModelAttribute("author") Author author, @NotNull BindingResult br, @PathVariable long id) {
        if (br.hasErrors()) {
            return new ModelAndView("/author/edit");
        }
        authorService.update(id, author);
        return new ModelAndView("redirect:/author/list");
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable long id) {
        authorService.delete(id);
        return new ModelAndView("redirect:/author/list");
    }

}
