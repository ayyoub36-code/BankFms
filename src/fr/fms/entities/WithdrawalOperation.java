package fr.fms.entities;

import java.time.LocalDate;

public class WithdrawalOperation extends Operation {

	// const

	public WithdrawalOperation(int id, LocalDate operationDate, double amount, int idAccount) {
		super(id, operationDate, amount, idAccount);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Retrait : " + super.toString();
	}

}
