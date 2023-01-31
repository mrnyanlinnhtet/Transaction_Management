package com.ultron.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.support.TransactionTemplate;

import com.ultron.demo.dto.PropagationResult;
import com.ultron.demo.repo.DetailRepository;
import com.ultron.demo.repo.HeaderRepository;

@Service
public class PropagationService {

	@Autowired
	private HeaderRepository headerRepo;
	@Autowired
	private DetailRepository detailRepo;

	private TransactionTemplate txTemplate;

	public PropagationService(PlatformTransactionManager txManager) {
		txTemplate = new TransactionTemplate(txManager);
		txTemplate.setPropagationBehavior(TransactionAttribute.PROPAGATION_REQUIRED);
		txTemplate.setIsolationLevel(TransactionAttribute.ISOLATION_READ_COMMITTED);
		txTemplate.setTimeout(5);
	}

	public PropagationResult save(int status, String header, String details) {
		return txTemplate.execute(s -> {
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
		});
	}

}
