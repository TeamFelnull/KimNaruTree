package red.felnull.kimnarutree.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.data.player.KNTPlayerData;
import red.felnull.otyacraftengine.data.WorldDataManager;
import red.felnull.otyacraftengine.util.PlayerHelper;

public class MoneyUtil {
    public static long getMoney(String uuid) {
        return new KNTPlayerData(uuid).getMoney();
    }

    public static long getMoney(ServerPlayerEntity playerEntity) {
        return getMoney(PlayerHelper.getUUID(playerEntity));
    }

    public static void setMoney(String uuid, long money) {
        new KNTPlayerData(uuid).setMoney(money);
    }

    public static void setMoney(ServerPlayerEntity sp, long money) {
        setMoney(PlayerHelper.getUUID(sp), money);
        checkWallet(sp, getMoney(sp));
    }

    public static void addMoney(String uuid, long money) {
        setMoney(uuid, getMoney(uuid) + money);
    }

    public static void addMoney(ServerPlayerEntity sp, long money) {
        addMoney(PlayerHelper.getUUID(sp), money);
    }

    public static TranslationTextComponent getDisplayAmount(long money) {
        return new TranslationTextComponent("money.currencyunit.g", money);
    }

    public static long ofFuneral(long moneyIn) {

        if (moneyIn <= 0)
            return moneyIn;
        return moneyIn / 2;

    }

    public static void checkWallet(ServerPlayerEntity player, long money) {
        if (money >= 1000) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:root"), player);
        }
        if (money >= 10000) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:wallet_10000"), player);
        }
        if (money >= 1000000) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:wallet_1000000"), player);
        }
        if (money >= 10000000) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:wallet_10000000"), player);
        }
        if (money >= 50000000) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:wallet_50000000"), player);
        }
        if (money >= 100000000) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:wallet_100000000"), player);
        }
        if (money >= 500000000) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:wallet_500000000"), player);
        }
        if (money >= 1000000000) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:wallet_1000000000"), player);
        }
        if (money >= 10000000000L) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:wallet_10000000000"), player);
        }
    }

    public static void checkFuneralCost(ServerPlayerEntity player, long cost) {
        if (cost <= 0) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:funeral_0"), player);
        }
        if (cost >= 500 && cost < 1000000) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:funeral_500"), player);
        }
        if (cost >= 1000000 && cost < 30000000) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:funeral_1000000"), player);
        }
        if (cost >= 30000000 && cost < 1000000000) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:funeral_30000000"), player);
        }
        if (cost >= 1000000000) {
            PlayerHelper.grantAdvancement(new ResourceLocation("kimnarutree:funeral_1000000000"), player);
        }
    }
}
