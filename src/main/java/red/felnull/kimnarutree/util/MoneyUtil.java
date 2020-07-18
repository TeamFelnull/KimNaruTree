package red.felnull.kimnarutree.util;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.otyacraftengine.data.WorldDataManager;
import red.felnull.otyacraftengine.util.PlayerHelper;

public class MoneyUtil {
    //残高取得
    public static long getMoney(String uuid, boolean isClientSide) {
        return WorldDataManager.instance().getPlayerData(uuid, new ResourceLocation(KimNaruTree.MODID, "money"), isClientSide).getLong("money");
    }

    public static long getMoney(ServerPlayerEntity playerEntity, boolean isClientSide) {
        return getMoney(PlayerHelper.getUUID(playerEntity), isClientSide);
    }

    //残高変更（鯖からの呼び出しのみ）
    public static void setMoney(String uuid, long money) {
        WorldDataManager.instance().getPlayerData(uuid, new ResourceLocation(KimNaruTree.MODID, "money"), false).putLong("money", money);
    }

    public static void setMoney(ServerPlayerEntity sp, long money) {
        setMoney(PlayerHelper.getUUID(sp), money);
    }

    //残高加算（鯖からの呼び出しのみ）
    public static void addMoney(String uuid, long money) {
        setMoney(uuid, getMoney(uuid, false) + money);
    }

    public static void addMoney(ServerPlayerEntity sp, long money) {
        addMoney(PlayerHelper.getUUID(sp), money);
    }

    //金額表示
    public static TranslationTextComponent getDisplayAmount(long money) {
        return new TranslationTextComponent("money.currencyunit.g", money);
    }
}
