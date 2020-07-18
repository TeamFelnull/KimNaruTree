package red.felnull.kimnarutree.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;

public class KNTCommands {
    public static void registerCommand(CommandDispatcher<CommandSource> d) {
        MoneyCommand.register(d);
    }
}
