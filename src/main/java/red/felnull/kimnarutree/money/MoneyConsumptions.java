package red.felnull.kimnarutree.money;

public class MoneyConsumptions {
    //葬式代						プレイヤーの残高
    public static long ofFuneral(long moneyIn) {

        if (moneyIn <= 0)
            return moneyIn;
        //葬式後の残高
        return moneyIn / 2;

    }
}
