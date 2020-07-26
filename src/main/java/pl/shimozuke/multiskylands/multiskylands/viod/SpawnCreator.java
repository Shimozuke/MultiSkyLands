package pl.shimozuke.multiskylands.multiskylands.viod;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.World;

import java.util.Optional;

import static org.spongepowered.api.block.BlockTypes.BEDROCK;
import static org.spongepowered.api.block.BlockTypes.GRASS;

public class SpawnCreator
{
    public static void createSpawn()
    {
        Optional<World> optionalWorld = Sponge.getServer().getWorld("MultiSkyLands");

        if (optionalWorld.isPresent())
        {
            World world = optionalWorld.get();

            world.setBlockType(0, 98, 0, BEDROCK);

            for (int i = -22; i <= 22; i++)
            {
                for (int j = -22; j <= 22; j++)
                {
                    world.setBlockType(i, 99, j, GRASS);
                }
            }
        }
        else
            Sponge.getServer().getConsole().sendMessage(Text.of(TextColors.RED, "World doesn't exist. What the fuck!?!?!?"));
    }
}
