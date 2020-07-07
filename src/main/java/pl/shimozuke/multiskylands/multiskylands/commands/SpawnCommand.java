package pl.shimozuke.multiskylands.multiskylands.commands;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.World;

public class SpawnCommand implements CommandExecutor
{
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
    {
        if (src instanceof Player)
        {
            Player player = (Player) src;
            Vector3d destination = new Vector3d(0, 100, 0);
            if (Sponge.getServer().getWorld("MultiSkyLands").isPresent())
            {
                World world = Sponge.getServer().getWorld("MultiSkyLands").get();
                player.setLocation(destination, world.getUniqueId());
                player.sendMessage(Text.of(TextColors.GREEN, "You were teleported to Sky Lands"));
            }
            else
                throw new CommandException(Text.of(TextColors.RED, "World doesn't exist."));
        }
        else
            throw new CommandException(Text.of(TextColors.RED, "Only in-game players can use this command."));
        return CommandResult.success();
    }
}
