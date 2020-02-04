package com.sabulous.todoapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import com.sabulous.todoapp.config.MainConfig;
import com.sabulous.todoapp.config.WebSecurityConfig;
import com.sabulous.todoapp.controllers.ItemController;
import com.sabulous.todoapp.controllers.MainController;
import com.sabulous.todoapp.controllers.TodoController;
import com.sabulous.todoapp.repositories.ItemRepository;
import com.sabulous.todoapp.repositories.TodoRepository;
import com.sabulous.todoapp.repositories.UserRepository;
import com.sabulous.todoapp.services.EncryptionService;
import com.sabulous.todoapp.services.IUserDetailsService;
import com.sabulous.todoapp.services.ItemService;
import com.sabulous.todoapp.services.TodoService;
import com.sabulous.todoapp.services.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoAppTests {

	@Autowired
	MainController mainController;

	@Autowired
	ItemController itemController;

	@Autowired
	TodoController todoController;

	@Autowired
	MainConfig mainConfig;

	@Autowired
	WebSecurityConfig webSecurityConfig;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	TodoRepository todoRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemService itemService;

	@Autowired
	TodoService todoService;

	@Autowired
	EncryptionService encryptionService;

	@Autowired
	IUserDetailsService userDetailsService;

	@Autowired
	UserService userService;

	@Test
	public void contextLoadTest() throws Exception {
		//using AssertJ
		assertThat(mainController).isNotNull();
		assertThat(itemController).isNotNull();
		assertThat(todoController).isNotNull();

		assertThat(mainConfig).isNotNull();
		assertThat(webSecurityConfig).isNotNull();

		assertThat(itemRepository).isNotNull();
		assertThat(todoRepository).isNotNull();
		assertThat(userRepository).isNotNull();

		assertThat(itemService).isNotNull();
		assertThat(todoService).isNotNull();
		assertThat(encryptionService).isNotNull();
		assertThat(userDetailsService).isNotNull();
		assertThat(userService).isNotNull();

		//using JUnit
		assertNotNull(mainController);
		assertNotNull(itemController);
		assertNotNull(todoController);
		
		assertNotNull(mainConfig);
		assertNotNull(webSecurityConfig);

		assertNotNull(itemRepository);
		assertNotNull(todoRepository);
		assertNotNull(userRepository);

		assertNotNull(itemService);
		assertNotNull(todoService);
		assertNotNull(encryptionService);
		assertNotNull(userDetailsService);
		assertNotNull(userService);
	}
	
}
