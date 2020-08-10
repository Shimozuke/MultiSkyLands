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
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import pl.shimozuke.multiskylands.multiskylands.MultiskyLands;
import pl.shimozuke.multiskylands.multiskylands.util.PositionCalculator;
import pl.shimozuke.multiskylands.multiskylands.storage.Storage;
import pl.shimozuke.multiskylands.multiskylands.util.IslandCreator;

import java.util.Optional;

public class CreateCommand implements CommandExecutor
{
    private final MultiskyLands plugin;
    private final Storage storage;
    private final PositionCalculator positionCalculator;
    private final IslandCreator islandCreator;

    public CreateCommand(MultiskyLands plugin)
    {
        this.plugin = plugin;
        this.storage = plugin.getStorage();
        this.positionCalculator = new PositionCalculator(plugin);
        this.islandCreator = new IslandCreator(plugin);
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
    {
        if(!(src instanceof Player))
            throw new CommandException(Text.of(TextColors.RED, "Only in-game players can use this command!"));

        Player player = (Player) src;

        final Optional<World> multiSkylandsWorld = this.plugin.getMultiSkylandsWorld();
        if (!multiSkylandsWorld.isPresent())
        {
            throw new CommandException(Text.of(TextColors.RED, "Multiskylands world does not exist!"));
        }

        Vector3i position = this.positionCalculator.calculatePosition(player.getName());
        if (position == null)
        {
            src.sendMessage(Text.of(TextColors.RED, "You own an island already."));
            return CommandResult.success();
        }

        Vector3d positionD = position.toDouble().add(0, 1, 0);
        boolean didCreate = this.islandCreator.createIsland(position);

        if (didCreate)
        {
            this.storage.saveIsland(player.getName(), position);
            player.setLocation(new Location<>(multiSkylandsWorld.get(), positionD));
        }

        return CommandResult.success();
    }
}
