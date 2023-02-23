package fr.fms.services;

import fr.fms.entities.Account;
import fr.fms.entities.User;

public interface IBankService {

	// Méthode qui permet d'ajouter un compte
	public void addAccount(Account account);

	// Méthode qui permet d'ajouter un nouveau client
	public void addCustomer(User user);

	// fourni methode consulter compte {id}
	public Account getAccount(int id) throws Exception;

	// methode retrait
	public void makeWithdrawal(int accountId, double amount) throws Exception;

	// methode versement
	public void makeDeposit(int accountId, double amount) throws Exception;

	// methode virement
	public void makeTransfer(int accountId_withdrawal, int accountId_deposit, double amount) throws Exception;

}