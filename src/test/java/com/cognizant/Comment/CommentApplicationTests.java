package com.cognizant.Comment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CommentApplicationTests {

	@Autowired
	private CommentController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
