package red.felnull.kimnarutree.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import red.felnull.kimnarutree.data.player.KNTPlayerData;
import red.felnull.kimnarutree.lib.COMMAND;
import red.felnull.kimnarutree.lib.TranslationUtil;
import red.felnull.kimnarutree.util.MoneyUtil;

import java.util.Collection;

import static red.felnull.kimnarutree.lib.TranslationUtil.kntTranslate;

public class MoneyCommand {

    public static final String MONEY = "money";
    public static final String TARGETS = "targets";
    public static final String ADD = "add";
    public static final String SET = "set";
    public static final String ARG = "arg";

    public static void register(CommandDispatcher<CommandSource> d) {
        d.register(Commands.literal(MONEY)
                .requires(source -> source.hasPermissionLevel(2))
                .executes(source -> showMoney(source.getSource(), null))
                .then(Commands.argument(TARGETS, EntityArgument.players())
                        .executes(source -> showMoney(source.getSource(), EntityArgument.getPlayers(source, TARGETS)))
                        .then(Commands.literal(ADD)
                                .then(Commands.argument(ARG, LongArgumentType.longArg())
                                        .executes(source -> addMoney(source.getSource(), EntityArgument.getPlayers(source, TARGETS), LongArgumentType.getLong(source, ARG)))
                                )
                        )
                        .then(Commands.literal(SET)
                                .then(Commands.argument(ARG, LongArgumentType.longArg())
                                        .executes(source -> setMoney(source.getSource(), EntityArgument.getPlayers(source, TARGETS), LongArgumentType.getLong(source, ARG)))
                                )
                        )
                )
        );
    }

    private static int showMoney(CommandSource source, Collection<ServerPlayerEntity> collection) {
        if (collection == null) {
            try {
                source.sendFeedback(kntTranslate(COMMAND.MONEY_SHOW_ME, MoneyUtil.getDisplayAmount(new KNTPlayerData(source.asPlayer()).getMoney())), true);
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            collection.forEach(pl -> source.sendFeedback(kntTranslate(COMMAND.MONEY_SHOW_PLAYER, pl.getName(), MoneyUtil.getDisplayAmount(new KNTPlayerData(pl).getMoney())), true));
        }
        return 1;
    }

    private static int addMoney(CommandSource source, Collection<ServerPlayerEntity> collection, long i) {
        collection.forEach(pl -> {
            source.sendFeedback(kntTranslate(COMMAND.MONEY_ADD, MoneyUtil.getDisplayAmount(i), pl.getName()), true);
            new KNTPlayerData(pl).addMoney(i);
        });
        return 1;
    }

    private static int setMoney(CommandSource source, Collection<ServerPlayerEntity> collection, long i) {
        collection.forEach(pl -> {
            KNTPlayerData data = new KNTPlayerData(pl);
            source.sendFeedback(kntTranslate(COMMAND.MONEY_SET, pl.getName(), MoneyUtil.getDisplayAmount(data.getMoney()), MoneyUtil.getDisplayAmount(i)), true);
            data.setMoney(i);
        });
        return 1;
    }
}
