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
import org.spongepowered.api.world.World;
import pl.shimozuke.multiskylands.multiskylands.MultiskyLands;
import pl.shimozuke.multiskylands.multiskylands.helpfullTools.PositionCalculator;
import pl.shimozuke.multiskylands.multiskylands.helpfullTools.Storage;
import pl.shimozuke.multiskylands.multiskylands.viod.IslandCreator;

public class CreateCommand implements CommandExecutor
{
    private MultiskyLands plugin;
    private Storage storage;
    private PositionCalculator positionCalculator;
    private Vector3i position;
    private World world;
    private IslandCreator islandCreator;

    public CreateCommand(MultiskyLands plugin)
    {
        this.plugin = plugin;
        this.storage = plugin.storage();
        this.world = plugin.world();
        this.positionCalculator = new PositionCalculator(plugin);
        this.islandCreator = new IslandCreator(plugin);
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException
    {
        if (src instanceof Player)
        {
            Player player = (Player) src;
            String playerName = player.getName();
            position = positionCalculator.calculatePosition(playerName);
            Vector3d positionD = new Vector3d(position.getX(), position.getY() + 1, position.getZ());
            if (position == null)
            {
                Sponge.getServer().getPlayer(playerName).get().sendMessage(Text.of(TextColors.RED, "You currently have island."));
                return null;
            }
            storage.saveIsland(playerName, position);
            islandCreator.createIsland(position);
            player.setLocation(positionD, world.getUniqueId());

        }
        return CommandResult.success();
    }
}
