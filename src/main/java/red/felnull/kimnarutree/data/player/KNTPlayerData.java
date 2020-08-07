package red.felnull.kimnarutree.data.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import red.felnull.kimnarutree.data.AbstractNBTBased;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.otyacraftengine.util.PlayerHelper;

public class KNTPlayerData extends AbstractNBTBased {

    public static String USER_NAME = "UserName";
    public static String MONEY  = "Money";
    public static String CREDITWORTHINESS = "Creditworthiness";
    public static String COUNTRY_UUID = "CountryUUID";

    public KNTPlayerData(String uuid){
        super(uuid);
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
}
