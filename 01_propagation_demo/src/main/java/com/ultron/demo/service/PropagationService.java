package com.ultron.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ultron.demo.dto.PropagationResult;
import com.ultron.demo.repo.DetailRepository;
import com.ultron.demo.repo.HeaderRepository;

@Service
public class PropagationService {

	@Autowired
	private HeaderRepository headerRepo;
	@Autowired
	private DetailRepository detailRepo;

	@Transactional(propagation = Propagation.REQUIRED)
	public PropagationResult save(int status, String header, String details) {
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

		return new PropagationResult(headerId, detailId);
	}

}
