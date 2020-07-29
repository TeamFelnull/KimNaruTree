package red.felnull.kimnarutree.country;

import net.minecraft.nbt.CompoundNBT;

public class Country {
    private String name;
    protected String uuid;
    private String foundedPlayerName;
    private String foundedPlayerUUID;
    private String flagImageUUID;

    public Country(String uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public Country(String uuid, CompoundNBT tag) {
        this.uuid = uuid;
        this.read(tag);
    }

    public void read(CompoundNBT tag) {
        this.name = tag.getString("name");
        this.foundedPlayerName = tag.getCompound("FoundedPlayer").getString("name");
        this.foundedPlayerUUID = tag.getCompound("FoundedPlayer").getString("uuid");
        this.flagImageUUID = tag.getString("FlagImageUUID");
    }

    public CompoundNBT write(CompoundNBT tag) {
        tag.putString("name", this.name);
        CompoundNBT foundedPlayer = new CompoundNBT();
        foundedPlayer.putString("name", this.foundedPlayerName);
        foundedPlayer.putString("uuid", this.foundedPlayerUUID);
        tag.put("FoundedPlayer", foundedPlayer);
        tag.putString("FlagImageUUID", this.flagImageUUID);
        return tag;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoundedPlayerName() {
        return foundedPlayerName;
    }

    public String getFoundedPlayerUUID() {
        return foundedPlayerUUID;
    }

    public void setFoundedPlayer(String name, String uuid) {
        this.foundedPlayerName = name;
        this.foundedPlayerUUID = uuid;
    }

    public String getFlagImageUUID() {
        return flagImageUUID;
    }

    public void setFlagImageUUID(String flagImageUUID) {
        this.flagImageUUID = flagImageUUID;
    }


}
