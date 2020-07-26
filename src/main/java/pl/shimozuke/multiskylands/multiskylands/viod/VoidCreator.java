package pl.shimozuke.multiskylands.multiskylands.viod;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.world.WorldArchetypes;
import org.spongepowered.api.world.storage.WorldProperties;

import java.io.IOException;

public class VoidCreator
{
    public static void createVoid()
    {
        try
        {
            WorldProperties worldProperties = Sponge.getServer().createWorldProperties("MultiSkyLands", WorldArchetypes.THE_VOID);
            Sponge.getServer().loadWorld(worldProperties);
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
        }
    }
}
