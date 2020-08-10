package pl.shimozuke.multiskylands.multiskylands.util;

import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.world.World;
import pl.shimozuke.multiskylands.multiskylands.MultiskyLands;
import pl.shimozuke.multiskylands.multiskylands.storage.Storage;

import static org.spongepowered.api.block.BlockTypes.GRASS;

public class IslandCreator
{
    private MultiskyLands plugin;

    public IslandCreator(final MultiskyLands plugin)
    {
        this.plugin = plugin;
    }

    public boolean createIsland(final Vector3i position)
    {
        final World world = plugin.getMultiSkylandsWorld().get();
        for (int i = position.getX() - 5; i < position.getX() + 5; i++)
        {
            for (int j = position.getZ() - 5; j < position.getZ() + 5; j++)
            {
                world.setBlockType(i , 99, j, GRASS);
            }
        }
        return true;
    }
}
