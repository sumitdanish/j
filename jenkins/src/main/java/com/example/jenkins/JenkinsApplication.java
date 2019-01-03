package com.example.jenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class JenkinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JenkinsApplication.class, args);
	}

}


@RestController
class ABC{
	@GetMapping("get")
	public Map<String,String> map(){
		Map<String,String> m = new HashMap<>();
		m.put("key","value");
		m.put("key1","value");
		return m;
	}
}
