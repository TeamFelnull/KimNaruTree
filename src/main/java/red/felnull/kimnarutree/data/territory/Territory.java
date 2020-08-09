package red.felnull.kimnarutree.data.territory;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import red.felnull.kimnarutree.data.AbstractNBTBased;
import red.felnull.kimnarutree.data.Knbt;
import red.felnull.kimnarutree.data.country.Country;
import red.felnull.kimnarutree.util.StringFormatter;

import java.util.Set;

public class Territory extends AbstractNBTBased {

    public static String TERRA_NULLIUS = "TerraNullius";

    public Territory(String dimSt) {
        super(dimSt);
    }

    @Override
    public CompoundNBT getParentNBT() {
        return Knbt.Territory().getNBTs();
    }

    @Override
    public CompoundNBT getDefaultNBT() {
        CompoundNBT terTag = new CompoundNBT();
        return terTag;
    }

    public static void register(World world){
        Territory ter = new Territory(str(world.func_234923_W_().func_240901_a_()));
        Knbt.Territory().register(ter);
    }

    public static String str(ResourceLocation dim){
        return dim.toString();
    }

    public static String str(ChunkPos pos){
        return StringFormatter.chunk(pos);
    }

    public static String getCountryUUID(ResourceLocation dim, ChunkPos pos){
        return new Territory(str(dim)).getNBT().getString(str(pos));
    }

    public static void addTerritory(ResourceLocation dim, ChunkPos pos, Country country){
        addTerritory(dim, pos, country != null ? country.getUUID() : TERRA_NULLIUS);
    }

    public static void addTerritory(ResourceLocation dim, ChunkPos pos, String countryUUID){
        Knbt.Territory().get(str(dim)).putString(str(pos), countryUUID);
    }

    public static boolean isTerraNullius(ResourceLocation dim, ChunkPos pos){
        String dimSt = str(dim);
        String chunkSt = str(pos);
        return !Knbt.Territory().getNBTs().contains(dimSt)
                || !Knbt.Territory().get(dimSt).contains(chunkSt)
                || Knbt.Territory().get(dimSt).getString(chunkSt).equals(TERRA_NULLIUS);
    }

    public static  Set<String> getTerritoriesIn(ResourceLocation dim){
        return Knbt.Territory().get(str(dim)).keySet();
    }
}
