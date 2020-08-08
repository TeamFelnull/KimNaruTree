package red.felnull.kimnarutree.data.country;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.core.jmx.Server;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.data.AbstractNBTBased;
import red.felnull.kimnarutree.data.KNTDatas;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.kimnarutree.data.bank.Bank;
import red.felnull.kimnarutree.data.player.KNTPlayerData;
import red.felnull.kimnarutree.data.territory.Territory;
import red.felnull.kimnarutree.packet.CreateCountryMessage;
import red.felnull.kimnarutree.packet.PacketHandler;
import red.felnull.otyacraftengine.api.DataSendReceiverManager;
import red.felnull.otyacraftengine.util.PictuerUtil;
import red.felnull.otyacraftengine.util.PlayerHelper;

import java.awt.image.BufferedImage;
import java.util.*;

public class Country extends AbstractNBTBased {

    public static Country clientNowCountry;

    public static String CITIZENS = "Citizens";
    public static String NAME = "Name";
    public static String SIZE = "Size";
    public static String FOUNDED_PLAYER_UUID= "FoundedPlayerUUID";
    public static String REPRESENTATIVE_PLAYER_UUID = "RepresentativePlayerUUID";
    public static String FLAG_IMAGE_UUID = "FlagImageUUID";
    public static String FLAG_WIDTH = "FlagWidth";
    public static String FLAG_HEIGHT = "FlagHeight";

    public Country() {
        super(UUID.randomUUID().toString());
    }

    public Country(String countryUUID) {
        super(countryUUID);
    }

    @Override
    public CompoundNBT getParentNBT() {
        return Knbt.Country().getNBTs();
    }

    @Override
    public CompoundNBT getDefaultNBT() {
        CompoundNBT tag = new CompoundNBT();
        tag.put(CITIZENS, new CompoundNBT());
        tag.putString(NAME, "");
        tag.putInt(SIZE, 0);
        tag.putString(FOUNDED_PLAYER_UUID, "");
        tag.putString(REPRESENTATIVE_PLAYER_UUID, "");
        tag.putString(FLAG_IMAGE_UUID, "");
        tag.putInt(FLAG_WIDTH, 0);
        tag.putInt(FLAG_HEIGHT, 0);
        return tag;
    }

    public static void register(ServerPlayerEntity player, CreateCountryMessage message){
        Country country = new Country();

        Knbt.Country().register(country);
        country.setName(message.name);
        country.setFoundedPlayer(player);
        country.setRepresentativePlayer(player);
        country.setFlagImageUUID(message.flagUUID);
        country.setFlagWidth(message.flagW);
        country.setFlagHeight(message.flagH);
        country.addPlayer(player);
    }

    @OnlyIn(Dist.CLIENT)
    public static void sendCreateRequest(String name, byte[] flagImage) {
        String flagUUID = UUID.randomUUID().toString();
        BufferedImage image = PictuerUtil.geBfftImage(flagImage);

        DataSendReceiverManager.instance().sendToServer(KNTDatas.WORLD_NATIONAL_FLAG, flagUUID, flagImage);
        PacketHandler.INSTANCE.sendToServer(new CreateCountryMessage(name, flagUUID, image.getWidth(), image.getHeight()));
    }

    public static Country getCountryByUUID(String uuid) {
        if (!Knbt.Country().getNBTs().contains(uuid))
            return null;

        return new Country(uuid);
    }

    public static Country getCountryByPlayer(ServerPlayerEntity player) {
        KNTPlayerData plData = new KNTPlayerData(PlayerHelper.getUUID(player));

        if (!plData.hasCitizenship())
            return null;

        return getCountryByUUID(plData.getCountryUUID());
    }

    public static Country getCountryByTerritory(ResourceLocation dim, ChunkPos pos) {
        if(Territory.isTerraNullius(dim, pos))
            return null;

        return getCountryByUUID(Territory.getCountryUUID(dim, pos));
    }

    public static Country getCountryByTerritory(World world, ChunkPos pos) {
        return getCountryByTerritory(world.func_234923_W_().func_240901_a_(), pos);
    }

    public static Country getCountryOn(ServerPlayerEntity player) {
        return getCountryByTerritory(player.world.func_234923_W_().func_240901_a_(), new ChunkPos(player.func_233580_cy_()));
    }

    public static void setTerritory(ResourceLocation dim, ChunkPos pos, Country country) {
        Country preCountry = getCountryByTerritory(dim, pos);

        if (preCountry != null) {
            preCountry.addSize(-1);
        }
        if (country != null) {
            country.addSize(+1);
        }

        Territory.addTerritory(dim, pos, country.getUUID());
    }

    public static void setTerritory(World world, ChunkPos pos, Country country) {
        setTerritory(world.func_234923_W_().func_240901_a_(), pos, country);
    }

    public static List<Country> getCountryList() {
        List<Country> countryList = new ArrayList<>();
        Knbt.Country().getNBTs().keySet().forEach(uuid -> countryList.add(getCountryByUUID(uuid)));

        return countryList;
    }

    public Map<String, String> getCitizens(){
        HashMap<String, String> citizens = new HashMap<>();

        getNBT().getCompound(CITIZENS).keySet().forEach(uuid -> citizens.put(uuid, KNTPlayerData.getUserNameFromUUID(uuid)));

        return citizens;
    }
    public void addPlayer(ServerPlayerEntity player) {
        String plUUID = PlayerHelper.getUUID(player);

        addCitizen(plUUID, PlayerHelper.getUserName(player));
        new KNTPlayerData(plUUID).setCountryUUID(getKey());
    }

    public void addCitizen(String uuid, String name){
        getNBT().getCompound(CITIZENS).putString(uuid, name);
    }

    public String getName() {
        return getNBT().getString(NAME);
    }

    public void setName(String name) {
        getNBT().putString(NAME, name);
    }

    public String getUUID() {
        return getKey();
    }

    public String getFoundedPlayerUUID() {
        return getNBT().getString(FOUNDED_PLAYER_UUID);
    }

    public String getFoundedPlayerName() {
        return KNTPlayerData.getUserNameFromUUID(getFoundedPlayerUUID());
    }

    public void setFoundedPlayer(PlayerEntity pl) {
        setFoundedPlayerUUID(PlayerHelper.getUUID(pl));
    }

    public void setFoundedPlayerUUID(String uuid) {
        getNBT().putString(FOUNDED_PLAYER_UUID, uuid);
    }

    public String getRepresentativePlayerUUID() {
        return getNBT().getString(REPRESENTATIVE_PLAYER_UUID);
    }

    public String getRepresentativePlayerName() {
        return KNTPlayerData.getUserNameFromUUID(getRepresentativePlayerUUID());
    }

    public void setRepresentativePlayer(PlayerEntity pl) {
        setRepresentativePlayerUUID(PlayerHelper.getUUID(pl));
    }

    public void setRepresentativePlayerUUID(String uuid) {
        getNBT().putString(REPRESENTATIVE_PLAYER_UUID, uuid);
    }

    public String getFlagImageUUID() {
        return getNBT().getString(FLAG_IMAGE_UUID);
    }

    public void setFlagImageUUID(String flagImageUUID) {
        getNBT().putString(FLAG_IMAGE_UUID, flagImageUUID);
    }

    public int getFlagWidth() {
        return getNBT().getInt(FLAG_WIDTH);
    }

    public void setFlagWidth(int flagWidth) {
        getNBT().putInt(FLAG_WIDTH, flagWidth);
    }

    public int getFlagHeight() {
        return getNBT().getInt(FLAG_HEIGHT);
    }

    public void setFlagHeight(int flagHeight) {
        getNBT().putInt(FLAG_HEIGHT, flagHeight);
    }

    public int getSize() {
        return getNBT().getInt(SIZE);
    }

    public void setSize(int size) {
        getNBT().putInt(SIZE, size);
    }

    public void addSize(int size) {
        setSize(getSize() + size);
    }

    @Override
    public String toString() {
        return getName();
    }
}


