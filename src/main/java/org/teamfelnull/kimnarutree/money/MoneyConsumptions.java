package org.teamfelnull.kimnarutree.money;

public class MoneyConsumptions {
	//葬式代
	public static long ofFuneral(long moneyIn) {

		if (moneyIn <= 0)
			return moneyIn;

		return moneyIn / 2;

	}
}
