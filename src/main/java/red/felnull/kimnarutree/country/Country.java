package red.felnull.kimnarutree.country;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.data.KNTDatas;
import red.felnull.kimnarutree.packet.CreateCountryMessage;
import red.felnull.kimnarutree.packet.PacketHandler;
import red.felnull.otyacraftengine.api.DataSendReceiverManager;
import red.felnull.otyacraftengine.data.WorldDataManager;
import red.felnull.otyacraftengine.util.PlayerHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Country {
    protected String uuid;
    private String name;
    private String foundedPlayerName;
    private String foundedPlayerUUID;
    private String flagImageUUID;
    private int flagWidth;
    private int flagHeight;

    public static Country clientNowCountry;

    public Country(String uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public Country(String uuid, CompoundNBT tag) {
        this.uuid = uuid;
        this.read(tag);
    }


    @OnlyIn(Dist.CLIENT)
    public static void sendCreateRequest(String name, byte[] flagImage, int flagWidth, int flagHeight) {
        String flagID = UUID.randomUUID().toString();
        DataSendReceiverManager.instance().sendToServer(KNTDatas.WORLD_NATIONAL_FLAG, flagID, flagImage);
        PacketHandler.INSTANCE.sendToServer(new CreateCountryMessage(name, flagID, flagWidth, flagHeight));
    }

    public static Country getCountryByUUID(String uuid) {
        CompoundNBT ctags = WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound("countrys");

        if (!ctags.contains(uuid))
            return null;

        return new Country(uuid, ctags.getCompound(uuid));
    }

    public static Country getCountrybyPlayer(ServerPlayerEntity player) {
        CompoundNBT ctags = WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound("players");
        String playerUUID = PlayerHelper.getUUID(player);

        if (!ctags.contains(playerUUID))
            return null;

        return getCountryByUUID(ctags.getString(playerUUID));
    }

    public static void setPlayer(ServerPlayerEntity player, Country country) {
        WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound("players").putString(PlayerHelper.getUUID(player), country.getUuid());
    }

    public static void addContry(Country country) {

        WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound("countrys").put(country.getUuid(), country.write(new CompoundNBT()));
    }

    public static Country getContryByTerritory(ResourceLocation dimensionLocation, ChunkPos pos) {
        CompoundNBT ctags = WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound("territorys");
        String chunkSt = dimensionLocation.toString() + ":" + pos.x + ":" + pos.z;

        if (!ctags.contains(chunkSt) || ctags.getString(chunkSt).equals("terranullius"))
            return null;

        return getCountryByUUID(ctags.getString(chunkSt));
    }

    public static void setTerritory(ResourceLocation dimensionLocation, ChunkPos pos, Country country) {
        String chunkSt = dimensionLocation.toString() + ":" + pos.x + ":" + pos.z;
        if (country != null) {
            WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound("territorys").putString(chunkSt, country.getUuid());
        } else {
            WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound("territorys").putString(chunkSt, "terranullius");
        }

    }

    public static List<Country> getCountrys() {
        List<Country> countryList = new ArrayList<Country>();
        CompoundNBT ctags = WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound("countrys");
        ctags.keySet().forEach(n -> countryList.add(getCountryByUUID(n)));
        return countryList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof Country)) {
            return false;
        }
        return ((Country) obj).getUuid().equals(this.uuid);
    }

    public void read(CompoundNBT tag) {
        this.name = tag.getString("name");
        this.foundedPlayerName = tag.getCompound("FoundedPlayer").getString("name");
        this.foundedPlayerUUID = tag.getCompound("FoundedPlayer").getString("uuid");
        this.flagImageUUID = tag.getString("FlagImageUUID");
        this.flagWidth = tag.getInt("FlagWidth");
        this.flagHeight = tag.getInt("FlagHeight");
    }

    public CompoundNBT write(CompoundNBT tag) {
        tag.putString("name", this.name);
        CompoundNBT foundedPlayer = new CompoundNBT();
        foundedPlayer.putString("name", this.foundedPlayerName);
        foundedPlayer.putString("uuid", this.foundedPlayerUUID);
        tag.put("FoundedPlayer", foundedPlayer);
        tag.putString("FlagImageUUID", this.flagImageUUID);
        tag.putInt("FlagWidth", this.flagWidth);
        tag.putInt("FlagHeight", this.flagHeight);
        return tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFoundedPlayerName() {
        return foundedPlayerName;
    }

    public String getFoundedPlayerUUID() {
        return foundedPlayerUUID;
    }

    public String toString() {
        return getName();
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

    public int getFlagWidth() {
        return flagWidth;
    }

    public int getFlagHeight() {
        return flagHeight;
    }

    public void setFlagWidth(int flagWidth) {
        this.flagWidth = flagWidth;
    }

    public void setFlagHeight(int flagHeight) {
        this.flagHeight = flagHeight;
    }
}


