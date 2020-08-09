package red.felnull.kimnarutree.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.data.country.Country;
import red.felnull.kimnarutree.lib.ITranslationEnum;
import red.felnull.kimnarutree.lib.MESSAGE;
import red.felnull.otyacraftengine.item.IDetailedInfomationItem;

import java.util.List;

import static red.felnull.kimnarutree.lib.TranslationUtil.*;

public class CountryDebugStickItem extends KNTItem implements IDetailedInfomationItem {

    public static final String COUNTRY_UUID = "CountryUUID";
    public static final String COUNTRY_NAME = "CountryName";
    public static final String FLAG_UUID = "FlagUUID";
    public static final String FLAG_WIDTH = "FlagWidth";
    public static final String FLAG_HEIGHT = "FlagHeight";

    public static final String ALREADY_SET = ".already_set";

    public CountryDebugStickItem(Properties properties) {
        super(properties);
    }

    public static Item instance(ITranslationEnum klang, int maxStackSize){
        return new CountryDebugStickItem(new Item.Properties().maxStackSize(maxStackSize).group(KNTItemGroup.MOD_TAB)).setRegistryName(KimNaruTree.MOD_ID, klang.getKey());
    }

    public static Country getCountryForItem(ItemStack stack) {
        CompoundNBT tag = stack.getTag();

        if (tag == null || !tag.contains(COUNTRY_UUID))
            return null;

        return Country.getCountryByUUID(tag.getString(COUNTRY_UUID));
    }

    public static void setCountryForItem(ItemStack stack, Country country) {
        if (country == null)
            return;

        CompoundNBT tag = stack.getOrCreateTag();
        tag.putString(COUNTRY_UUID, country.getUUID());
        tag.putString(COUNTRY_NAME, country.getName());
        tag.putString(FLAG_UUID, country.getFlagImageUUID());
        tag.putInt(FLAG_WIDTH, country.getFlagWidth());
        tag.putInt(FLAG_HEIGHT, country.getFlagHeight());
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        PlayerEntity player = context.getPlayer();

        if (!world.isRemote && player != null) {
            Country current = Country.getCountryByTerritory(world, pos);

            if (!player.isCrouching()) {
                Country next = getCountryForItem(context.getItem());
                if (next != null && !next.equals(current)) {
                    Country.setTerritory(world, pos, next);
                    player.sendStatusMessage(kntTranslate(MESSAGE.COUNTRY_SET, next.getName()), true);
                }
            }

            if (player.isCrouching()) {
                if (current != null) {
                    Country.setTerritory(world, pos, null);
                    player.sendStatusMessage(kntTranslate(MESSAGE.COUNTRY_SET, kntTranslate(MESSAGE.COUNTRY_TERRA_NULLIUS)), true);
                }
            }
        }

        return ActionResultType.func_233537_a_(world.isRemote);
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (worldIn.isRemote)
            return false;

        if (!player.isCrouching()) {
            Country country = Country.getCountryByTerritory(worldIn, pos);
            TranslationTextComponent component;
            component = (country != null) ?
                    kntTranslate(MESSAGE.COUNTRY_TERRITORY, country.getName()) : kntTranslate(MESSAGE.COUNTRY_TERRA_NULLIUS);

            player.sendStatusMessage(component, true);
        }

        if (player.isCrouching()) {
            List<Country> list = Country.getCountryList();
            if(list.isEmpty())
                return false;

            ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);
            Country current = getCountryForItem(stack);
            Country next;
            next = (current == null) || (list.indexOf(current) == list.size() - 1) ?
                    list.get(0) : list.get(list.indexOf(current) + 1);

            setCountryForItem(stack, next);
            player.sendStatusMessage(new StringTextComponent(next.getName()), true);
        }

        return false;
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        CompoundNBT tag = stack.getTag();

        if (tag != null && tag.contains(COUNTRY_NAME)) {
            return kntTranslate(getTranslationKey(stack) + ALREADY_SET, tag.getString(COUNTRY_NAME));
        }

        return super.getDisplayName(stack);
    }
}
