package in.nic.pes.lgd.service;

import in.nic.pes.lgd.bean.EntityTransactionsNew;

import java.util.List;

public interface PESTransactionService {
	
	/* method added to fetch data from LGD DB public schema
	 * for PES state administrator
	 */
	public List<EntityTransactionsNew> getLGDTransactions(Integer statecode);
	public EntityTransactionsNew getEntityTransactionById(Integer tid);
	public boolean update(EntityTransactionsNew transaction, boolean isRequiredForPES);

}
