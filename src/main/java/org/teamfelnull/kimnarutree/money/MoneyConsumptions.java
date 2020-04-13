package org.teamfelnull.kimnarutree.money;

public class MoneyConsumptions {
	//葬式代
	public static long consumptionFuneral(long moneyIn, PlayerEntity pl) {

		if (moneyIn <= 0)
			return moneyIn;

		return moneyIn / 2;

	}
}
