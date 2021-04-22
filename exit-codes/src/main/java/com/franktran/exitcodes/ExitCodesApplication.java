package com.franktran.exitcodes;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeEvent;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ExitCodesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExitCodesApplication.class, args);
	}

	@Bean
	public ExitCodeExceptionMapper exitCodeExceptionMapper() {
		return exception -> {
			if (exception.getCause() instanceof NumberFormatException) {
				return 11;
			} else if (exception.getCause() instanceof NullPointerException) {
				return 12;
			}
			return 10;
		};
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			throw new NullPointerException();
		};
	}

	@Bean
	public MyEventListener myEventListener() {
		return new MyEventListener();
	}

	public static class MyEventListener {

		@EventListener
		public void exit(ExitCodeEvent event) {
			System.out.printf("Application exit with event code  %d%n", event.getExitCode());
		}
	}

}
