package in.nic.pes.lgd.service.impl;

import in.nic.pes.lgd.bean.EntityTransactionsNew;
import in.nic.pes.lgd.dao.PESTransactionDAO;
import in.nic.pes.lgd.service.PESTransactionService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PESTransactionServiceImpl implements PESTransactionService{

	@Autowired
	PESTransactionDAO pesTransactionDAO;
	
	@Override
	public List<EntityTransactionsNew> getLGDTransactions(Integer statecode) {
		// TODO Auto-generated method stub
		return pesTransactionDAO.getLGDTransactions(statecode);
	}

	@Override
	public EntityTransactionsNew getEntityTransactionById(Integer tid) {
		// TODO Auto-generated method stub
		return pesTransactionDAO.getEntityTransactionById(tid);
	}

	@Override
	public boolean update(EntityTransactionsNew transaction, boolean isRequiredForPES) {
		// TODO Auto-generated method stub
		return pesTransactionDAO.update(transaction, isRequiredForPES);
	}
}
