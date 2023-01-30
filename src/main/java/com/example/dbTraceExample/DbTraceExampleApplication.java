package com.example.dbTraceExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.dbTraceExample.model.Contact;
import com.example.dbTraceExample.model.User;
import com.example.dbTraceExample.repository.ContactRepository;
import com.example.dbTraceExample.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DbTraceExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbTraceExampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository repository, ContactRepository contactRepository) {
		return (args) -> {
			List<Integer> ids = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				User usr = User.builder().username(UUID.randomUUID().toString())
						.build();
				List<Contact> contacts = Arrays.asList(Contact.builder()
						.user(usr)
						.phoneNumber(randomPhoneNumber())
						.build());
				usr.setContacts(contacts);
				usr = repository.save(usr);
				contactRepository.saveAll(contacts);
				ids.add(usr.getId());
			}
			repository.findAllById(ids).forEach(el -> log.info("el: {}", el));
		};
	}

	public static String randomPhoneNumber() {
		return String.valueOf(ThreadLocalRandom.current().nextLong(9999, 99999999 + 1));
	}

}
