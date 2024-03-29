package fabusilva.com.github.WebServiceRESTful.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	private static final String TEMPLATE = "Hello, %s!";
	private final AtomicLong couter = new AtomicLong();
	
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "world") String name) {
		return new Greeting(couter.incrementAndGet(), String.format(TEMPLATE, name));
	}
	
	@GetMapping("/")
	public String home() {
		return "Page on!";
	}

}
