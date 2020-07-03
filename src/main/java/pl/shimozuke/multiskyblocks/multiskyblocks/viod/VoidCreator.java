package pl.shimozuke.multiskyblocks.multiskyblocks.viod;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.WorldArchetypes;

import java.io.IOException;

public class VoidCreator
{
    public static void createVoid()
    {
        try
        {
            Sponge.getServer().createWorldProperties("MultiSkyBlocks", WorldArchetypes.THE_VOID);
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
