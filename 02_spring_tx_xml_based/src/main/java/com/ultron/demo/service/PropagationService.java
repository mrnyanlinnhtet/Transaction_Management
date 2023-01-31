package com.ultron.demo.service;

import com.ultron.demo.dto.PropagationResult;
import com.ultron.demo.repo.DetailRepository;
import com.ultron.demo.repo.HeaderRepository;

public class PropagationService {

	private HeaderRepository headerRepository;
	private DetailRepository detailRepository;

	public void setHeaderRepository(HeaderRepository headerRepository) {
		this.headerRepository = headerRepository;
	}

	public void setDetailRepository(DetailRepository detailRepository) {
		this.detailRepository = detailRepository;
	}

	
	public PropagationResult save(int status, String header, String details) {
		// Create header
		var headerId = headerRepository.create(header);
		if (status == 1) {
			throw new RuntimeException();
		}

		// Create detail
		var detailId = detailRepository.create(headerId, details);
		if (status == 2) {
			throw new RuntimeException();
		}

		return new PropagationResult(headerId, detailId);
	}

}
