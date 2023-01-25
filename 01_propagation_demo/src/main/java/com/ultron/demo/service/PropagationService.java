package com.ultron.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ultron.demo.repo.DetailRepository;
import com.ultron.demo.repo.HeaderRepository;

@Service
public class PropagationService {

	@Autowired
	private HeaderRepository headerRepo;
	@Autowired
	private DetailRepository detailRepo;

	public Map<Integer, List<Integer>> save(int status, String header, String details) {
		// Create header
		var headerId = headerRepo.create(header);
		if (status == 1) {
			throw new RuntimeException();
		}

		// Create detail
		var detailId = detailRepo.create(headerId, details);
		if (status == 2) {
			throw new RuntimeException();
		}

		var data = new HashMap<Integer, List<Integer>>();
		data.put(headerId, detailId);

		return data;
	}

}
