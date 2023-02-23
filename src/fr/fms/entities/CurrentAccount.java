package fr.fms.entities;

import java.time.LocalDate;

public class CurrentAccount extends Account {

	private double overdraft;

	public CurrentAccount(int id, double overdraft, double balance, User user, LocalDate localDate) throws Exception {

		super(id, balance, user, localDate);
		this.overdraft = overdraft;
	}

	public double getOverdraft() {
		return overdraft;
	}

	public void setOverdraft(double overdraft) {
		this.overdraft = overdraft;
	}

	@Override
	public String toString() {
		return "Current Account [ " + super.toString() + ", overdraft= " + overdraft + "]";
	}
}