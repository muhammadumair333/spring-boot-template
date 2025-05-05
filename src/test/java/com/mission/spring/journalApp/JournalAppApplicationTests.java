package com.mission.spring.journalApp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JournalAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void add(){
		assertEquals(4, 2+2);
	}

}
