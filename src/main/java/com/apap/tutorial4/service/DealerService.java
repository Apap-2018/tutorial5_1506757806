package com.apap.tutorial4.service;

import java.util.Optional;

import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.repository.DealerDb;

/**
 * DealerService
 */
public interface DealerService {
	Optional<DealerModel> getDealerDetailById(Long id);
	
	void addDealer(DealerModel dealer);

	void deleteDealer(DealerModel dealer);

	DealerModel getDealerDb();
	
	DealerDb viewAllDealer();

	void updateDealer(Long id);

	void updateDealer(Long id, DealerModel dealerModel);

}
