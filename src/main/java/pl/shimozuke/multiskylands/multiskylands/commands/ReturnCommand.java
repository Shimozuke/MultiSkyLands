package pl.shimozuke.multiskylands.multiskylands.commands;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.command.TabCompleteEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.storage.WorldProperties;

import java.util.Optional;
import java.util.UUID;

public class ReturnCommand implements CommandExecutor
{

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
    {

        if (src instanceof Player)
        {

            Player player = (Player) src;

            Optional<WorldProperties> optionalDefaultWorld = Sponge.getServer().getDefaultWorld();
            UUID uuid = optionalDefaultWorld.get().getUniqueId();
            World world = Sponge.getServer().getWorld(uuid).get();

            Vector3i destinationI = optionalDefaultWorld.get().getSpawnPosition();
//            Vector3d destinationD = new Vector3d(destinationI.getX(), destinationI.getY(), destinationI.getZ());

            Location<World> location = new Location<World>(world, destinationI);

            if (!optionalDefaultWorld.isPresent())
            {
                throw new CommandException(Text.of("The default world does't exist."));
            }
            else
            {
                player.setLocation(Sponge.getTeleportHelper().getSafeLocation(location, 10, 10).get());
                player.sendMessage(Text.of(TextColors.GREEN, "Welcome in default world."));
            }
        }
        else
            throw new CommandException(Text.of("This command can only be used by players."));
        return CommandResult.success();
    }
}
