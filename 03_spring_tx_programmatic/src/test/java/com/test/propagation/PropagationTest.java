package com.test.propagation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.ultron.demo.service.PropagationService;

@SpringJUnitConfig(locations = "classpath:/application.xml")
@Sql(scripts = "/sql/init.sql")
public class PropagationTest {

	@Autowired
	private PropagationService service;

	@ParameterizedTest
	@CsvSource({
		"header,details,1,1,1"
	})
	void paropagationTest(String header, String details, int status, int headerId, int detailId) {

		var result = service.save(status, header, details);

		assertNotNull(result);
		assertEquals(headerId, result.headerId());
		
		assertNotNull(result.detailsId());
		assertEquals(detailId, result.detailsId().get(0));
	}

}
