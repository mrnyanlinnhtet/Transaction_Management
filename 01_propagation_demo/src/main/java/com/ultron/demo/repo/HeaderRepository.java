package com.ultron.demo.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class HeaderRepository {

	private SimpleJdbcInsert insert;

	public HeaderRepository(DataSource dataSource) {
		insert = new SimpleJdbcInsert(dataSource);
		insert.setTableName("header_tbl");
		insert.setGeneratedKeyName("id");
		insert.setColumnNames(List.of("name", "header_id"));
	}

	public int create(String name) {
		var param = new HashMap<String, Object>();
		param.put("name", name);
		return insert.executeAndReturnKey(param).intValue();
	}

}
