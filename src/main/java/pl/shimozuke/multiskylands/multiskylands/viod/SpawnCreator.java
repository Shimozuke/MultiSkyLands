package pl.shimozuke.multiskylands.multiskylands.viod;

import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.World;
import pl.shimozuke.multiskylands.multiskylands.MultiskyLands;
import pl.shimozuke.multiskylands.multiskylands.helpfullTools.Storage;

import java.util.Optional;

import static org.spongepowered.api.block.BlockTypes.BEDROCK;
import static org.spongepowered.api.block.BlockTypes.GRASS;

public class SpawnCreator
{
    private MultiskyLands plugin;
    private Vector3i spawn = new Vector3i(0, 99, 0);
    private Storage storage;
    public SpawnCreator(MultiskyLands plugin)
    {
        this.plugin = plugin;
        this.storage = plugin.storage();
    }

    public void createSpawn()
    {
        Optional<World> optionalWorld = Sponge.getServer().getWorld("MultiSkyLands");

        if (optionalWorld.isPresent())
        {
            World world = optionalWorld.get();

            world.setBlockType(0, 98, 0, BEDROCK);

            storage.saveIsland("Spawn", spawn);

            for (int i = -20; i <= 20; i++)
            {
                for (int j = -20; j <= 20; j++)
                {
                    world.setBlockType(i, 99, j, GRASS);
                }
            }
        }
        else
            Sponge.getServer().getConsole().sendMessage(Text.of(TextColors.RED, "World doesn't exist. What the fuck!?!?!?"));
    }
}
