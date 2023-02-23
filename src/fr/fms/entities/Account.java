package fr.fms.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Account {

	private int id;
	private double balance;
	private User user;
	private LocalDate date;
	private ArrayList<Operation> listeOperations;

	public Account(int id, double balance, User user, LocalDate localDate) throws Exception {

		this.id = id;
		this.balance = balance;
		this.user = user;
		this.date = localDate;
		if (user == null) {
			throw new Exception("Ajoutez un client à ce compte !");
		} else {
			this.user.getListAccounts().add(this);
		}

		this.listeOperations = new ArrayList<Operation>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public ArrayList<Operation> getListeOperations() {
		return listeOperations;
	}

	@Override
	public String toString() {
		return "AccountId=" + id + ", creationDate=" + date + ", balance=" + balance + "]";
	}
}