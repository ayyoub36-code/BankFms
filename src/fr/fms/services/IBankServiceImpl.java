package fr.fms.services;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import fr.fms.entities.Account;
import fr.fms.entities.CurrentAccount;
import fr.fms.entities.DepositOperation;
import fr.fms.entities.Operation;
import fr.fms.entities.User;
import fr.fms.entities.WithdrawalOperation;

public class IBankServiceImpl implements IBankService {

	private Map<Integer, Account> accounts;
	private Map<Integer, User> customers;
	private Map<Integer, Operation> operations;

	public Map<Integer, User> getCustomers() {
		return customers;
	}

	public Map<Integer, Account> getAccounts() {
		return accounts;
	}

	public IBankServiceImpl() {
		operations = new HashMap<>();
		accounts = new HashMap<>();
		customers = new HashMap<>();
	}

	/**
	 * consulter un compte bancaire
	 * 
	 * @author Mehdioui_Ayyoub
	 * @param id
	 * @return account<Account>
	 * @throws Exception
	 */
	@Override
	public Account getAccount(int id) throws Exception {
		Account account = accounts.get(id);// find accountById
		if (account == null)
			throw new Exception("Vous demandez un compte inexistant");
		else
			return account;
	}

	/**
	 * effectuer un retrait
	 * 
	 * @author Mehdioui_Ayyoub
	 * @param accountId, amount
	 * @throws Exception
	 * 
	 */
	@Override
	public void makeWithdrawal(int accountId, double amount) throws Exception {
		// variable id
		double capacity = 0;
		int idOpe = operations.size() + 1;
		Account account = null;
		try {
			account = getAccount(accountId);
		} catch (Exception e) {
			e.getMessage();
		} // faire un retrait : trouver le compte
		if (account != null) {
			// compte courant (decouvert)
			if (account instanceof CurrentAccount)
				capacity = account.getBalance() + ((CurrentAccount) account).getOverdraft();
			else
				capacity = account.getBalance();
			if (capacity >= amount) {// modifSold
				account.setBalance(account.getBalance() - amount);// creOpé
				WithdrawalOperation operation = new WithdrawalOperation(idOpe, LocalDate.now(), amount,
						account.getId());
				account.getListeOperations().add(operation);
				operations.put(idOpe, operation);// ajout de l opération dans le tableau
				System.out.println("retrait effectué avec succès");
			} else
				throw new Exception("votre solde n'est pas suffisant !");
		} else
			throw new Exception("l'id saisit est invalide !");
	}

	/**
	 * effectuer un dépôt
	 * 
	 * @author SupervielleBF
	 * @param accountId, amount
	 * @throws Exception
	 * 
	 */
	@Override
	public void makeDeposit(int accountId, double amount) throws Exception {
		// variable id
		int idOpe = operations.size() + 1;
		if (accounts.get(accountId) != null) { // add the amount
			accounts.get(accountId).setBalance(accounts.get(accountId).getBalance() + amount);
			// one operation related to one accountID
			operations.put(operations.size() + 1,
					new DepositOperation(idOpe, LocalDate.now(), amount, accounts.get(accountId).getId()));// operations.get(operationId).set
			accounts.get(accountId).getListeOperations()
					.add(new DepositOperation(idOpe, LocalDate.now(), amount, accounts.get(accountId).getId()));
			System.out.println("versement effectué avec succès");
		} else
			throw new Exception("l'id saisit est invalide !");
	}

	/**
	 * effectuer un virement
	 * 
	 * @author Mehdioui_Ayyoub
	 * @param accountId, amount
	 * @throws Exception
	 * 
	 */
	@Override
	public void makeTransfer(int accountId_withdrawal, int accountId_deposit, double amount) throws Exception {
		if (accountId_withdrawal != accountId_deposit) {
			makeWithdrawal(accountId_withdrawal, amount); // methode virement : retir => compte n1
			makeDeposit(accountId_deposit, amount);// verse => compte n2

		} else
			System.out.println("vous ne pouvez pas retirer et verser sur le même compte");
	}

	@Override
	public void addAccount(Account account) {
		accounts.put(account.getId(), account);

	}

	@Override
	public void addCustomer(User user) {
		customers.put(user.getId(), user);

	}

	public void addOperations(Operation operation) {// ajouter une operation dans la liste
		operations.put(operations.size() + 1, operation);
		accounts.get(operation.getIdAccount()).getListeOperations().add(operation);
	}

	public void displayAccounts() {
		for (Account account : accounts.values()) {
			System.out.println(account);
		}
	}

	public void displayCustomers() {
		for (User customer : customers.values()) {
			System.out.println(customer);
		}
	}
}