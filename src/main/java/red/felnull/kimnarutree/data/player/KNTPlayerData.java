package red.felnull.kimnarutree.data.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.AbstractNBTBased;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.kimnarutree.data.country.Country;
import red.felnull.kimnarutree.lib.lang.ADVANCEMENT;
import red.felnull.otyacraftengine.util.PlayerHelper;

import static red.felnull.kimnarutree.lib.resource.ResourceUtil.kntResource;

public class KNTPlayerData extends AbstractNBTBased {

    public ServerPlayerEntity player;

    public static final String USER_NAME = "UserName";
    public static final String MONEY  = "Money";
    public static final String CREDITWORTHINESS = "Creditworthiness";
    public static final String COUNTRY_UUID = "CountryUUID";

    public KNTPlayerData(String uuid){
        super(uuid);
    }

    public KNTPlayerData(ServerPlayerEntity player){
        super(PlayerHelper.getUUID(player));
        this.player = player;
    }

    @Override
    public CompoundNBT getParentNBT() {
        return Knbt.PlayerData().getNBTs();
    }

    @Override
    public CompoundNBT getDefaultNBT() {
        CompoundNBT plTag = new CompoundNBT();
        plTag.putString(USER_NAME, "");
        plTag.putLong(MONEY, 0);
        plTag.putInt(CREDITWORTHINESS, 0);
        plTag.putString(COUNTRY_UUID, "");
        return plTag;
    }

    public static void register(PlayerEntity player) {
        String uuid = PlayerHelper.getUUID(player);
        KNTPlayerData playerData = new KNTPlayerData(uuid);

        Knbt.PlayerData().register(playerData);
        playerData.setName(PlayerHelper.getUserName(player));
    }

    public static String getUserNameFromUUID(String uuid){
        return (Knbt.PlayerData().getNBTs().contains(uuid)) ? new KNTPlayerData(uuid).getName() : null;
    }

    public boolean hasCitizenship(){
        return !getCountryUUID().isEmpty();
    }

    public void checkWallet() {
        if(player == null)
            return;

        long money = getMoney();
        if (money >= 1000) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.WALLET_0), player);
        }
        if (money >= 10000) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.WALLET_MAN), player);
        }
        if (money >= 1000000) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.WALLET_100MAN), player);
        }
        if (money >= 10000000) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.WALLET_1000MAN), player);
        }
        if (money >= 50000000) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.WALLET_5000MAN), player);
        }
        if (money >= 100000000) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.WALLET_OKU), player);
        }
        if (money >= 500000000) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.WALLET_5OKU), player);
        }
        if (money >= 1000000000) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.WALLET_10OKU), player);
        }
        if (money >= 10000000000L) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.WALLET_100OKU), player);
        }
    }

    public long funeralCost() {
        long cost = getMoney() / 2;

        if(player == null || cost <= 0) {
            return 0;
        }

        if (cost < 500) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.FUNERAL_0), player);
        }
        if (cost < 1000000) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.FUNERAL_500), player);
        }
        if (cost < 30000000) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.FUNERAL_100MAN), player);
        }
        if (cost < 1000000000) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.FUNERAL_3000MAN), player);
        }
        if (cost >= 1000000000) {
            PlayerHelper.grantAdvancement(kntResource(ADVANCEMENT.FUNERAL_10OKU), player);
        }

        return cost;
    }

    public String getName(){
        return getNBT().getString(USER_NAME);
    }

    public void setName(String name){
        getNBT().putString(USER_NAME, name);
    }

    public long getMoney(){
        return getNBT().getLong(MONEY);
    }

    public void setMoney(long money){
        getNBT().putLong(MONEY, money);
        checkWallet();
    }

    public void addMoney(long money){
        setMoney(getMoney() + money);
    }

    public int getCreditworthiness(){
        return getNBT().getInt(CREDITWORTHINESS);
    }

    public void setCreditworthiness(int cw){
        getNBT().putInt(CREDITWORTHINESS, cw);
    }

    public String getCountryUUID(){
        return getNBT().getString(COUNTRY_UUID);
    }

    public void setCountryUUID(String uuid){
        getNBT().putString(COUNTRY_UUID, uuid);
    }

    @Override
    public String toString() {
        return key + "'s info" + '\n' +
                "Name: " + getName() + '\n' +
                "Country: " + Country.getCountryByUUID(getCountryUUID()) + '\n' +
                "Money: " + getMoney() + '\n' +
                "Creditworthiness: : " + getCreditworthiness();
    }
}
