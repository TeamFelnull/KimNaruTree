package red.felnull.kimnarutree.lib;

import net.minecraft.util.ResourceLocation;
import red.felnull.kimnarutree.KimNaruTree;

public class ResourceUtil {

    public static ResourceLocation resource(String key){
        return new ResourceLocation(key);
    }

    public static ResourceLocation resource(IResourceEnum resourceEnum){
        return resource(resourceEnum.getKey());
    }

    public static ResourceLocation kntResource(String key){
        return new ResourceLocation(KimNaruTree.MOD_ID, key);
    }

    public static ResourceLocation kntResource(IResourceEnum resourceEnum){
        return kntResource(resourceEnum.getKey());
    }

    public static String string(IResourceEnum resourceEnum){
        return resourceEnum.getKey();
    }
}
