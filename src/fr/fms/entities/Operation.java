package fr.fms.entities;

import java.time.LocalDate;

public abstract class Operation {
	// attribute
	private int id;
	private LocalDate operationDate;
	private double amount;
	private int idAccount;// id du compte suffit

	// const
	public Operation(int id, LocalDate operationDate, double amount, int idAccount) {
		this.setId(id);
		this.setOperationDate(operationDate);
		this.setAmount(amount);
		this.setIdAccount(idAccount);
	}

	// methodes

	// To string
	@Override
	public String toString() {
		return "Transaction [TransactionId = " + id + ", TransactionDate = " + operationDate + ", amount = " + amount
				+ ", accountId = " + idAccount + "]";
	}

	// get&set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(LocalDate operationDate) {
		this.operationDate = operationDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
}
