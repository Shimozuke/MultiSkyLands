package pl.shimozuke.multiskyblocks.multiskyblocks;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

import pl.shimozuke.multiskyblocks.multiskyblocks.viod.VoidCreator;

@Plugin(id = PluginInfo.ID, name = PluginInfo.Name, authors = PluginInfo.Authors, url = PluginInfo.URL, description = PluginInfo.Description)
public class Multiskyblocks
{

    @Inject
    private Logger logger;

    @Listener
    public void onServerStart(GameStartedServerEvent event)
    {

        if (!Sponge.getServer().getWorld("MultiSkyBlocks").isPresent())
            VoidCreator.createVoid();
    }

}
