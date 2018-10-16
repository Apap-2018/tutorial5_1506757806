package com.apap.tutorial4.service;

import java.util.Optional;

import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.repository.DealerDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * DealerServiceImpl
 */
@Service
@Transactional
public class DealerServiceImpl implements DealerService {
	
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public Optional<DealerModel>getDealerDetailById(Long id) {
		return dealerDb.findById(id);
	}
	
	@Override
	public void addDealer(DealerModel dealer) {
		dealerDb.save(dealer);
	}

	@Override
	public void deleteDealer(DealerModel dealer) {
		// TODO Auto-generated method stub
		dealerDb.delete(dealer);
	}

	@Override
	public DealerModel getDealerDb() {
		return null;
	}

	@Override
	public DealerDb viewAllDealer() {
		// TODO Auto-generated method stub
		return dealerDb;
	}

	@Override
	public void updateDealer(Long id, DealerModel dealerModel) {
		// TODO Auto-generated method stub
		DealerModel dealer = getDealerDetailById(id).get();
		dealer.setAlamat(dealerModel.getAlamat());
		dealer.setNoTelp(dealerModel.getNoTelp());
	}

	@Override
	public void updateDealer(Long id) {
		// TODO Auto-generated method stub
		
	}
}
