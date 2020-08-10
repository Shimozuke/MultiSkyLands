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
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import pl.shimozuke.multiskylands.multiskylands.MultiskyLands;

import java.util.Optional;

public class SpawnCommand implements CommandExecutor
{
    private MultiskyLands plugin;

    public SpawnCommand(MultiskyLands plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
    {
        if (src instanceof Player)
        {
            final Player player = (Player) src;
            final Vector3d destination = new Vector3d(0, 100, 0);
            final Optional<World> optionalWorld = this.plugin.getMultiSkylandsWorld();
            if (optionalWorld.isPresent())
            {
                World world = optionalWorld.get();
                player.setLocation(new Location<>(world, destination));
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
