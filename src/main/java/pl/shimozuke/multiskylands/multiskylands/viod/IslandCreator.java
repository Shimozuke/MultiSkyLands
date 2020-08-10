package pl.shimozuke.multiskylands.multiskylands.viod;

import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.World;
import pl.shimozuke.multiskylands.multiskylands.MultiskyLands;
import pl.shimozuke.multiskylands.multiskylands.helpfullTools.Storage;

import static org.spongepowered.api.block.BlockTypes.GRASS;

public class IslandCreator
{
    private MultiskyLands plugin;
    private Storage storage;
    private World world;

    public IslandCreator(MultiskyLands plugin)
    {
        this.plugin = plugin;
        this.storage = plugin.storage();
        this.world = plugin.world();
    }

    public void createIsland(Vector3i position)
    {
        for (int i = position.getX() - 5; i < position.getX() + 5; i++)
        {
            for (int j = position.getZ() - 5; j < position.getZ() + 5; j++)
            {
                world.setBlockType(i , 99, j, GRASS);
            }
        }
    }
}
