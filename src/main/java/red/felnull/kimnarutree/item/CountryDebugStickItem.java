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
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import red.felnull.kimnarutree.data.country.Country;
import red.felnull.otyacraftengine.item.IDetailedInfomationItem;

import java.util.List;

public class CountryDebugStickItem extends Item implements IDetailedInfomationItem {

    public static String COUNTRY_UUID = "CountryUUID";
    public static String COUNTRY_NAME = "CountryName";
    public static String FLAG_UUID = "FlagUUID";
    public static String FLAG_WIDTH = "FlagWidth";
    public static String FLAG_HEIGHT = "FlagHeight";

    public CountryDebugStickItem(Properties properties) {
        super(properties);
    }

    public static Country getCountryForItem(ItemStack stack) {
        CompoundNBT tag = stack.getTag();

        if (tag == null || !tag.contains(COUNTRY_UUID) || tag.getString(COUNTRY_UUID).equals("null"))
            return null;

        return Country.getCountryByUUID(tag.getString(COUNTRY_UUID));
    }

    public static void setCountryForItem(ItemStack stack, Country country) {
        CompoundNBT tag = stack.getOrCreateTag();

        if (country != null) {
            tag.putString(COUNTRY_UUID, country.getUUID());
            tag.putString(COUNTRY_NAME, country.getName());
            tag.putString(FLAG_UUID, country.getFlagImageUUID());
            tag.putInt(FLAG_WIDTH, country.getFlagWidth());
            tag.putInt(FLAG_HEIGHT, country.getFlagHeight());
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        if (!world.isRemote && player != null) {

            if (!player.isCrouching()) {
                Country country = getCountryForItem(context.getItem());
                Country preCountry = Country.getCountryByTerritory(world, new ChunkPos(context.getPos()));
                if (country != null && (preCountry == null || !preCountry.equals(country))) {
                    Country.setTerritory(context.getWorld(), new ChunkPos(context.getPos()), country);
                    player.sendStatusMessage(new TranslationTextComponent("message.country.set", country.getName()), true);
                }
            } else {
                Country country = Country.getCountryByTerritory(world, new ChunkPos(context.getPos()));
                if (country != null) {
                    Country.setTerritory(context.getWorld(), new ChunkPos(context.getPos()), null);
                    player.sendStatusMessage(new TranslationTextComponent("message.country.set", new TranslationTextComponent("message.country.terranullius")), true);
                }
            }
        }

        return ActionResultType.func_233537_a_(world.isRemote);
    }

    @Override
    public boolean canPlayerBreakBlockWhileHolding(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
        if (worldIn.isRemote)
            return false;

        ItemStack stack = player.getHeldItem(Hand.MAIN_HAND);

        if (!player.isCrouching()) {
            Country country = Country.getCountryByTerritory(worldIn, new ChunkPos(pos));
            if (country != null)
                player.sendStatusMessage(new TranslationTextComponent("message.country.territory", country.getName()), true);
            else
                player.sendStatusMessage(new TranslationTextComponent("message.country.terranullius"), true);
        } else {
            List<Country> countryList = Country.getCountryList();
            if (countryList.isEmpty()) {
                return false;
            } else {
                Country settedC = getCountryForItem(stack);
                Country setingC = null;
                if (settedC == null) {
                    setingC = countryList.get(0);
                } else if (countryList.indexOf(settedC) == countryList.size() - 1) {
                    setingC = countryList.get(0);
                } else {
                    setingC = countryList.get(countryList.indexOf(settedC) + 1);
                }
                player.sendStatusMessage(new StringTextComponent(setingC.getName()), true);
                setCountryForItem(stack, setingC);
            }
        }
        return false;
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {

        if (stack.getTag() != null && stack.getTag().contains(COUNTRY_NAME) && !stack.getTag().getString(COUNTRY_NAME).equals("null")) {
            return new TranslationTextComponent(this.getTranslationKey(stack) + ".alreadyset", stack.getTag().getString(COUNTRY_NAME));
        }

        return super.getDisplayName(stack);
    }
}
