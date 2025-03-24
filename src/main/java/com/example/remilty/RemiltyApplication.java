package com.example.remilty;

import com.example.remilty.model.SwiftCode;
import com.example.remilty.parser.SwiftCodeParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RemiltyApplication {


	public static void main(String[] args) {

		SpringApplication.run(RemiltyApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(SwiftCodeParser swiftCodeParser) {
		return args -> {
			String filePath = "/app/Interns_2025_SWIFT_CODES - Sheet1.csv";
			swiftCodeParser.parseSwiftCodes(filePath);
		};
	}

}
