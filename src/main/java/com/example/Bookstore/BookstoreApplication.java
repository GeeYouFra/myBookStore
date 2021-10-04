package com.example.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;

import com.example.Bookstore.domain.User;
import com.example.Bookstore.domain.UserRepository;

import jdk.internal.org.jline.utils.Log;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository catrepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			
			catrepository.save(new Category("Life"));
			catrepository.save(new Category("Family"));
			catrepository.save(new Category("Fame"));
			
			repository.save(new Book("Life of Pi", "Giovani Francisco", 2021, "123135-AB", 19.99, catrepository.findByName("Life").get(0)));
			repository.save(new Book("Endangered", "Gee Zee", 1999, "434842-CD", 19.99, catrepository.findByName("Fame").get(0) ));

			User user1 = new User("user", "$2a$10$P8k.WYT8gGgrs2WAUl784uxcoKinbNqi7xF/4jGxTfJ.zV08L8pi.", "user@gmail.com", "USER");
			User user2 = new User("admin", "$2a$10$2YDFgrF7qulNJGXdwm7w9O9O7YU9iHLTD0CagdWnL6qk5DrgbR9D2", "admin@gmail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			
			log.info("fetch all USERS");
			for (User user : urepository.findAll()) {
				log.info(user.toString());
			}
		};
	}

}
