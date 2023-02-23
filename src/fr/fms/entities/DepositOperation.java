package fr.fms.entities;

import java.time.LocalDate;

public class DepositOperation extends Operation {

	public DepositOperation(int id, LocalDate operationDate, double amount, int idAccount) {
		super(id, operationDate, amount, idAccount);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Versement : " + super.toString();
	}
}
