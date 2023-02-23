package fr.fms.tests;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

import fr.fms.entities.Account;
import fr.fms.entities.CurrentAccount;
import fr.fms.entities.DepositOperation;
import fr.fms.entities.Operation;
import fr.fms.entities.SavingAccount;
import fr.fms.entities.User;
import fr.fms.entities.WithdrawalOperation;
import fr.fms.services.IBankServiceImpl;

public class Test {

	public static void main(String[] args) {

		// Générer des customers
		User macron = null, biden = null, castex = null;
		try {
			macron = new User(1, "Macron", "Emmanuel", "emmanuel.macr@gouv.fr", "55 Rue du Faubourg Paris",
					"01 01 01 01 01", LocalDateTime.of(1977, 12, 21, 12, 0));
			biden = new User(2, "Biden", "Jojo", "joe.biden@fms-ea.com", "1600, Pennsylvania Avenue, Washington DC",
					"0 563 241 115", LocalDateTime.of(1942, 11, 20, 5, 35));
			castex = new User(3, "Castex", "Jean", "jean.castex@fms-ea.com", "Vic-Fezensac (Gers)", "033 5 63 24 11 51",
					LocalDateTime.of(1965, 06, 25, 5, 35));
		} catch (Exception e2) {

			e2.printStackTrace();
		}
		try {
			macron.setEmail("jhmlmlj");
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		System.out.println(macron);
		// Générer des comptes
		Account macronAccount = null;
		Account bidenAccount = null;
		Account macronSavingAccount = null;
		Account bidenSavingAccount = null;
		Account testAccount = null;
		try {
			testAccount = new CurrentAccount(5, 2000, 2000, macron, LocalDate.now());
			macronAccount = new CurrentAccount(1, 5000, 1500, macron, LocalDate.now());
			bidenAccount = new CurrentAccount(2, 15000, 250000, biden, LocalDate.now());
			macronSavingAccount = new SavingAccount(3, 2, 150000, macron, LocalDate.now());
			bidenSavingAccount = new SavingAccount(4, 3, 250000, biden, LocalDate.now());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Faire des opérations
		Operation deposit1 = new DepositOperation(1, LocalDate.now(), 200, macronAccount.getId());
		Operation withdrawal1 = new WithdrawalOperation(2, LocalDate.now(), 333, macronAccount.getId());

		// Affichage et jeux de test
		System.out.println("création et affichage de deux compte bancaires :\n");
		System.out.println(macronAccount);
		System.out.println(macronSavingAccount);
		System.out.println("----------------------------------------------");
		System.out.println("solde du compte courant de manu:" + macronAccount.getBalance());
		System.out.println("solde du compte epargne de manu:" + macronSavingAccount.getBalance());
		System.out.println("----------------------------------------------");
		IBankServiceImpl job = new IBankServiceImpl();

		// Ajout dans la hashMap
		job.addCustomer(macron);
		job.addCustomer(biden);
		job.addCustomer(castex);
		System.out.println("liste de nos clients :\n");
		job.displayCustomers();
		System.out.println("------------------------------------------");

		// Liste des comptes
		job.addAccount(testAccount);
		job.addAccount(macronAccount);
		job.addAccount(bidenAccount);
		job.addAccount(macronSavingAccount);
		job.addAccount(bidenSavingAccount);
		System.out.println("la liste des comptes :\n");
		job.displayAccounts();

		// Liste des opérations
		System.out.println("-----------------------------------------");

		int idAccount = 50;
		try {
			job.getAccount(idAccount);
		} catch (Exception e) {
			e.getMessage();
		}

		// Dépassement de capacité de retrait
		try {
			job.makeWithdrawal(1, 30002);
			// Virement sur le même compte{id}
			job.makeTransfer(1, 1, 1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Affichage des comptes qui appartiennent à un client
		System.out.println("\n--------------liste des comptes de Macron--------------------");
		macron.getListAccounts().forEach((account) -> System.out.println(account));

		// liste des transactions de manu
		job.addOperations(withdrawal1);
		job.addOperations(deposit1);
		System.out.println("\n-------------Liste des transactions sur le compte courant de manu ---------------");
		macronAccount.getListeOperations().forEach((ope) -> System.out.println(ope));

		System.out.println("------------------------Liste des compte de Biden---------------------");
		biden.getListAccounts().forEach((account) -> System.out.println(account));

		System.out
				.println("\n------------------------Liste des comptes filtré sur les riches ! ---------------------\n");

		Predicate<? super Account> predicate = e -> ((Account) e).getBalance() > 10000;
		job.getAccounts().values().stream().filter(predicate).forEach(e -> System.out.println(e));

		System.out.println("\n------------------------Liste des clients filtré  ---------------------\n");
		job.getCustomers().values().stream().sorted((e1, e2) -> e1.getLastName().compareToIgnoreCase(e2.getLastName()))
				.map(e -> e.getFirstName() + " " + e.getLastName().toUpperCase() + " né le : "
						+ e.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
				.forEach(e -> System.out.println(e));

	}
}