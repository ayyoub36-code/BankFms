package fr.fms.entities;

import java.time.LocalDate;

public class SavingAccount extends Account {

	private double interestRate;

	public SavingAccount(int id, double interestRate, double balance, User user, LocalDate date) throws Exception {
		super(id, balance, user, date);
		this.interestRate = interestRate;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		return "Saving Account [ " + super.toString() + ", interestRate=" + interestRate + "]";
	}
}