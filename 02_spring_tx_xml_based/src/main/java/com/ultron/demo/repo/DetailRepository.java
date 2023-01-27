package com.ultron.demo.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public class DetailRepository {

	private SimpleJdbcInsert insert;

	public DetailRepository(DataSource dataSource) {
		insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("detail_tbl");
		insert.setGeneratedKeyName("id");
		insert.setColumnNames(List.of("name","header_id"));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<Integer> create(int headerId, String... names) {
		List<Integer> list = new ArrayList<>();

		for (String name : names) {
			var params = new HashMap<String, Object>();
			params.put("name", name);
			params.put("header_id", headerId);

			var id = insert.executeAndReturnKey(params).intValue();
			list.add(id);
		}
		return list;
	}

}
