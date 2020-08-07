package red.felnull.kimnarutree.country;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.data.AbstractNBTBased;
import red.felnull.kimnarutree.data.KNTDatas;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.kimnarutree.packet.CreateCountryMessage;
import red.felnull.kimnarutree.packet.PacketHandler;
import red.felnull.otyacraftengine.api.DataSendReceiverManager;
import red.felnull.otyacraftengine.data.WorldDataManager;
import red.felnull.otyacraftengine.util.PictuerUtil;
import red.felnull.otyacraftengine.util.PlayerHelper;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Country extends AbstractNBTBased {

    public static String NAME = "Name";
    public static String FOUNDED_PLAYER_NAME = "FoundedPlayerName";
    public static String FOUNDED_PLAYER_UUID= "FoundedPlayerUUID";
    public static String REPRESENTATIVE_PLAYER_NAME = "RepresentativePlayerName";
    public static String REPRESENTATIVE_PLAYER_UUID = "RepresentativePlayerUUID";
    public static String FLAG_WIDTH = "FlagWidth";
    public static String FLAG_HEIGHT = "FlagHeight";
    public static String SIZE = "Size";
    public static String FLAG = "Flag";

    private String name;
    private String foundedPlayerName;
    private String foundedPlayerUUID;
    private String representativePlayerName;
    private String representativePlayerUUID;
    private String flagImageUUID;
    private int flagWidth;
    private int flagHeight;
    private int size;

    public static Country clientNowCountry;

    public Country() {
        super(UUID.randomUUID().toString());
    }

    public Country(String uuid) {
        super(uuid);
    }

    @Deprecated
    public Country(CompoundNBT tag) {
        super(UUID.randomUUID().toString());
        this.read(tag);
    }

    @Override
    public CompoundNBT getParentNBT() {
        return Knbt.getCountries();
    }

    @Override
    public CompoundNBT getDefaultNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.putString(NAME, "");
        tag.putString(FOUNDED_PLAYER_NAME, "");
        tag.putString(FOUNDED_PLAYER_UUID, "");
        tag.putString(REPRESENTATIVE_PLAYER_NAME, "");
        tag.putString(REPRESENTATIVE_PLAYER_UUID, "");
        tag.putInt(FLAG_HEIGHT, 0);
        tag.putInt(FLAG_WIDTH, 0);
        tag.putInt(SIZE, 0);
        return tag;
    }

    @OnlyIn(Dist.CLIENT)
    public static void sendCreateRequest(String name, byte[] flagImage) {
        String flagID = UUID.randomUUID().toString();
        BufferedImage image = PictuerUtil.geBfftImage(flagImage);

        DataSendReceiverManager.instance().sendToServer(KNTDatas.WORLD_NATIONAL_FLAG, flagID, flagImage);
        PacketHandler.INSTANCE.sendToServer(new CreateCountryMessage(name, flagID, image.getWidth(), image.getHeight()));
    }

    public static Country getCountryByUUID(String uuid) {
        if (!Knbt.getCountries().contains(uuid))
            return null;

        return new Country(uuid);
    }

    public static Country getCountryByPlayer(ServerPlayerEntity player) {
        String playerUUID = PlayerHelper.getUUID(player);

        if (!new KNTPlayerData(playerUUID).belongTo())
            return null;

        return getCountryByUUID();
    }

    public static void setPlayer(ServerPlayerEntity player, Country country) {
        WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound("players").putString(PlayerHelper.getUUID(player), country.getUuid());
    }

    public void writeData() {
        WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound("countrys").put(getUuid(), write(new CompoundNBT()));
    }

    public static void addCountry(Country country) {

        WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound("countrys").put(country.getUuid(), country.write(new CompoundNBT()));
    }

    public static Country getCountryByTerritory(ResourceLocation dimensionLocation, ChunkPos pos) {
        CompoundNBT ctags = WorldDataManager.instance().getWorldData(new ResourceLocation(KimNaruTree.MODID, "countrydata")).getCompound("territorys");
        String dimSt = dimensionLocation.toString();
        String chunkSt = pos.x + ":" + pos.z;

        if (!ctags.contains(dimSt) || !ctags.getCompound(dimSt).contains(chunkSt) || ctags.getCompound(dimSt).getString(chunkSt).equals("terranullius"))
            return null;

        return getCountryByUUID(ctags.getCompound(dimSt).getString(chunkSt));
    }

    public static void setTerritory(ResourceLocation dimensionLocation, ChunkPos pos, Country country) {
        Country befC = getCountryByTerritory(dimensionLocation, pos);

        if (befC != null) {
            befC.setSize(befC.getSize() - 1);
            befC.writeData();
        }
        if (country != null) {
                country.setSize(country.getSize() + 1);
                country.writeData();
        }


        String dimSt = dimensionLocation.toString();
        String chunkSt = pos.x + ":" + pos.z;
        CompoundNBT ctag = Knbt.getTerritories().contains(dimSt) ? Knbt.getTerritory(dimSt) : new CompoundNBT();
        ctag.putString(chunkSt, country != null ? country.getUuid() : "terranullius");
        Knbt.addTerritory(dimSt, ctag);
    }

    public static List<Country> getCountryList() {
        List<Country> countryList = new ArrayList<>();
        Knbt.getCountries().keySet().forEach(n -> countryList.add(getCountryByUUID(n)));
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
        this.representativePlayerName = tag.getCompound("RepresentativePlayer").getString("name");
        this.representativePlayerUUID = tag.getCompound("RepresentativePlayer").getString("uuid");
        this.flagImageUUID = tag.getString("FlagImageUUID");
        this.flagWidth = tag.getInt("FlagWidth");
        this.flagHeight = tag.getInt("FlagHeight");
        this.size = tag.getInt("Size");
    }

    public CompoundNBT write(CompoundNBT tag) {
        tag.putString("name", this.name);
        CompoundNBT foundedPlayer = new CompoundNBT();
        foundedPlayer.putString("name", this.foundedPlayerName);
        foundedPlayer.putString("uuid", this.foundedPlayerUUID);

        CompoundNBT representativePlayer = new CompoundNBT();
        representativePlayer.putString("name", this.representativePlayerName);
        representativePlayer.putString("uuid", this.representativePlayerUUID);

        tag.put("FoundedPlayer", foundedPlayer);
        tag.put("RepresentativePlayer", representativePlayer);

        tag.putString("FlagImageUUID", this.flagImageUUID);
        tag.putInt("FlagWidth", this.flagWidth);
        tag.putInt("FlagHeight", this.flagHeight);
        tag.putInt("Size", this.size);
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

    public String getRepresentativePlayerName() {
        return representativePlayerName;
    }


    public String getRepresentativePlayerUUID() {
        return representativePlayerUUID;
    }

    public void setRepresentativePlayer(String name, String uuid) {
        this.representativePlayerName = name;
        this.representativePlayerUUID = uuid;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}


