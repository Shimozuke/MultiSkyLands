package pl.shimozuke.multiskylands.multiskylands.commands;

import com.google.common.collect.Lists;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import pl.shimozuke.multiskylands.multiskylands.MultiskyLands;

import javax.xml.soap.Text;
import java.util.List;
import java.util.Map;

public class HelpCommand implements CommandExecutor
{

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
    {
        Map<List<String>, CommandSpec> commands = MultiskyLands.SUBCOMMAND;
        List<Text> helpList = Lists.newArrayList();

        return CommandResult.success();
    }
}
