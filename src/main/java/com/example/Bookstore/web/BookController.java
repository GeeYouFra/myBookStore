package com.example.Bookstore.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;


@Controller
public class BookController {
	@Autowired
	private BookRepository repository;

	@RequestMapping(value = { "/", "/booklist" })
	public String showIndex(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}

	@RequestMapping(value = "/add")
	public String addStudent(Model model) {
		model.addAttribute("book", new Book());
		return "addBook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveStudent(Book book, Model model) {
		repository.save(book);
		model.addAttribute("book", new Book());
		return "redirect:booklist";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
    	//TWO DOTS MEAN THAT NOW NEEDS TO GO ONE LEVEL UP IN
    	//HIERARCHY. ENDPOINT BASE IS /
        return "redirect:../booklist";
    } 

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		Optional<Book> book = repository.findById(bookId);
		model.addAttribute("book", book);
		return "modifystudent";
	}
}
