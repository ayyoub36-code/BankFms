package fr.fms.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

import fr.fms.entities.Account;
import fr.fms.entities.CurrentAccount;
import fr.fms.entities.SavingAccount;
import fr.fms.entities.User;

public class ServiceBank {

	public static void main(String[] args) {
		/**
		 * menu principal myBank
		 * 
		 * @author Mehdioui Ayyoub
		 * 
		 */
		Scanner scan = new Scanner(System.in);
		IBankServiceImpl job = ressources();
		connectToAccount(job, scan);
		scan.close();

	}

	// couleur console
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RED = "\u001B[31m";

	private static IBankServiceImpl ressources() {
		// resources
		// Générer des customers
		User macron = null, biden = null;
		try {
			macron = new User(1, "Macron", "Emmanuel", "emmanuel.macron@gouv.fr", "55 Rue du Faubourg Paris",
					"01 01 01 01 01", LocalDateTime.of(1977, 12, 21, 12, 0));
			biden = new User(2, "Biden", "Jojo", "joe.biden@fms-ea.com", "1600, Pennsylvania Avenue, Washington DC",
					"0 563 241 115", LocalDateTime.of(1942, 11, 20, 5, 35));
		} catch (Exception e) {
			e.printStackTrace();
		}

//		try {
//			macron.setPhoneNumber("0753998971");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}

		// test address mail
//		try {
//			macron.setEmail("jhmlmlj");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}

		// Générer des comptes
		Account macronAccount = null, bidenAccount = null, macronSavingAccount = null, bidenSavingAccount = null;
		try {
			macronAccount = new CurrentAccount(1, 5000, 25000, macron, LocalDate.now());
			bidenAccount = new CurrentAccount(2, 15000, 250000, biden, LocalDate.now());
			macronSavingAccount = new SavingAccount(3, 2, 150000, macron, LocalDate.now());
			bidenSavingAccount = new SavingAccount(4, 3, 250000, biden, LocalDate.now());
		} catch (Exception e) {
			e.printStackTrace();
		}

		IBankServiceImpl job = new IBankServiceImpl();

		// Ajout dans la hashMap
		job.addCustomer(macron);
		job.addCustomer(biden);

		job.addAccount(macronAccount);
		job.addAccount(bidenAccount);
		job.addAccount(macronSavingAccount);
		job.addAccount(bidenSavingAccount);

		return job;
	}

	private static void connectToAccount(IBankServiceImpl job, Scanner scan) {

		System.out.println("*****" + ANSI_GREEN + " Bienvenue chez FMS myBank™" + ANSI_RESET
				+ " *****\n Saisissez votre numéro de compte :");

		// variable and object
		int numAccount = 0;
		String name = null;
		boolean success = false;
		Account account = null;

		while (!success) {
			try {
				while (!scan.hasNextInt())
					scan.next();
				numAccount = scan.nextInt();
				if (job.getAccount(numAccount) != null)
					success = true;
				name = job.getAccount(numAccount).getUser().getFirstName();
				account = job.getAccount(numAccount);
			} catch (Exception e) {
				System.out.println("Vous demandez un compte inexistant !");
			}
		}
		if (success) {
			System.out.println("\nBonjour " + ANSI_GREEN + name + ANSI_RESET + ", que souhaiter vous faire ?\n");
			int input = 0;

			// Affichage du menu principal
			while (input != 6) {
				System.out.println("**************** Taper le numéro correspondant *****************");
				System.out.println(ANSI_CYAN + "1 : Versement");
				System.out.println("2 : Retrait");
				System.out.println("3 : Virement");
				System.out.println("4 : Informations sur ce compte");
				System.out.println("5 : Liste des opérations" + ANSI_RESET);
				System.out.println(ANSI_RED + "6 : Sortie" + ANSI_RESET);
				System.out.println();
				while (!scan.hasNextInt())
					scan.next();
				input = scan.nextInt();
				switch (input) {
				// Versement
				case 1:
					System.out.println("Saisissez le montant à verser sur ce compte :");
					double amount = scan.nextDouble();
					try {
						job.makeDeposit(numAccount, amount);
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println();
					break;
				// Retrait
				case 2:
					System.out.println("Saisissez le montant à retirer sur ce compte :");
					double amountTo = scan.nextDouble();
					try {
						job.makeWithdrawal(numAccount, amountTo);
					} catch (Exception e) {
						System.out.println("votre solde n'est pas suffisant !");
					}
					System.out.println();

					break;
				// Virement
				case 3:
					System.out.println("Saisissez le numéro de compte destinataire :");
					int accountId_deposit = scan.nextInt();
					System.out.println("Saisissez le montant à verser sur ce compte :");
					amount = scan.nextDouble();
					try {
						job.makeTransfer(numAccount, accountId_deposit, amount);
					} catch (Exception e) {
						System.out.println("vous ne pouvez pas retirer et verser sur le même compte");
					}

					break;
				// Informations sur ce compte
				case 4:
					System.out.println("Informations sur ce compte : ");
					if (account.getBalance() < 0)
						System.err.println("Attention vous êtes en découvert !");
					System.out.println(account + "\n");
					break;
				// liste operations
				case 5:
					if (account.getListeOperations().isEmpty()) {
						System.out.println("Aucune opération !");
					} else {
						System.out.println("Liste des opérations :");
						account.getListeOperations().forEach((ope) -> System.out.println(ope));
						System.out.println();
					}
					break;
				case 6:
					System.err.println("Déconnexion !\n");
					connectToAccount(job, scan);
					break;
				default:
					System.out.println("Erreur de saisie");
				}
			}
		}
	}
}
