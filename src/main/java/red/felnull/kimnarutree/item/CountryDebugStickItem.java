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
import red.felnull.kimnarutree.country.Country;
import red.felnull.otyacraftengine.item.IDetailedInfomationItem;

import java.util.List;

public class CountryDebugStickItem extends Item implements IDetailedInfomationItem {
    public CountryDebugStickItem(Properties properties) {
        super(properties);
    }

    public static void setCountryForItem(ItemStack stack, Country country) {
        CompoundNBT tag = stack.getOrCreateTag();

        if (country != null) {
            tag.putString("CountryUUID", country.getUuid());
            tag.putString("CountryName", country.getName());
            tag.putString("CountryFlag", country.getFlagImageUUID());
            tag.putInt("FlagWidth", country.getFlagWidth());
            tag.putInt("FlagHeight", country.getFlagHeight());
        }
    }

    public static Country getCountryForItem(ItemStack stack) {
        CompoundNBT tag = stack.getTag();

        if (tag == null || !tag.contains("CountryUUID") || tag.getString("CountryUUID").equals("null"))
            return null;


        return Country.getCountryByUUID(tag.getString("CountryUUID"));
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
                Country afcountry = Country.getContryByTerritory(world.func_234923_W_().func_240901_a_(), new ChunkPos(context.getPos()));
                if (country != null && (afcountry == null || !afcountry.equals(country))) {
                    Country.setTerritory(context.getWorld().func_234923_W_().func_240901_a_(), new ChunkPos(context.getPos()), country);
                    player.sendStatusMessage(new TranslationTextComponent("message.country.set", country.getName()), true);
                }
            } else {
                Country country = Country.getContryByTerritory(world.func_234923_W_().func_240901_a_(), new ChunkPos(context.getPos()));
                if (country != null) {
                    Country.setTerritory(context.getWorld().func_234923_W_().func_240901_a_(), new ChunkPos(context.getPos()), null);
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
            Country country = Country.getContryByTerritory(worldIn.func_234923_W_().func_240901_a_(), new ChunkPos(pos));
            if (country != null)
                player.sendStatusMessage(new TranslationTextComponent("message.country.territory", country.getName()), true);
            else
                player.sendStatusMessage(new TranslationTextComponent("message.country.terranullius"), true);
        } else {
            List<Country> countryList = Country.getCountrys();
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

        if (stack.getTag() != null && stack.getTag().contains("CountryName") && !stack.getTag().getString("CountryName").equals("null")) {
            return new TranslationTextComponent(this.getTranslationKey(stack) + ".alreadyset", stack.getTag().getString("CountryName"));
        }

        return super.getDisplayName(stack);
    }
}
