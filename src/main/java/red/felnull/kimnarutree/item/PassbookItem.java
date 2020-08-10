package red.felnull.kimnarutree.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.NewChatGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import red.felnull.kimnarutree.KimNaruTree;
import red.felnull.kimnarutree.data.player.KNTPlayerData;
import red.felnull.kimnarutree.lib.lang.ITranslationEnum;
import red.felnull.otyacraftengine.util.PlayerHelper;

public class PassbookItem extends KNTItem {
    public static ITextComponent newChatComponent;
    public static ITextComponent oldChatComponent;
    private static final int DELETION_ID = 19194545;

    public PassbookItem(Properties properties) {
        super(properties);
    }

    public static Item instance(ITranslationEnum klang, int maxStackSize){
        return new PassbookItem(new Item.Properties().maxStackSize(maxStackSize).group(KNTItemGroup.MOD_TAB)).setRegistryName(KimNaruTree.MOD_ID, klang.getKey());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity pl, Hand hand) {

        if(!world.isRemote()){
            newChatComponent = new StringTextComponent(new KNTPlayerData(PlayerHelper.getUUID(pl)).toString());
            NewChatGui chatGui = Minecraft.getInstance().ingameGUI.getChatGUI();
            if(oldChatComponent != null) {
                chatGui.deleteChatLine(DELETION_ID);
            }

            chatGui.printChatMessageWithOptionalDeletion(newChatComponent, DELETION_ID);
        }

        return ActionResult.resultSuccess(pl.getHeldItem(hand));
    }
}
