package pl.shimozuke.multiskylands.multiskylands;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;

import org.spongepowered.api.text.Text;
import pl.shimozuke.multiskylands.multiskylands.commands.HelpCommand;
import pl.shimozuke.multiskylands.multiskylands.commands.ReturnCommand;
import pl.shimozuke.multiskylands.multiskylands.commands.SpawnCommand;
import pl.shimozuke.multiskylands.multiskylands.viod.VoidCreator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Plugin(id = PluginInfo.ID, name = PluginInfo.Name, authors = PluginInfo.Authors, url = PluginInfo.URL, description = PluginInfo.Description)
public class MultiskyLands
{
    public static final Map<List<String>, CommandSpec> SUBCOMMAND = new HashMap<>();

    @Inject
    private Logger logger;

    @Listener
    public void onGameInitialization(GameInitializationEvent event)
    {
        initCommands();
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event)
    {

        if (!Sponge.getServer().getWorld("MultiSkyLands").isPresent())
            VoidCreator.createVoid();
    }

    private void initCommands()
    {
        SUBCOMMAND.put(Arrays.asList("spawn"), CommandSpec.builder()
            .description(Text.of("Teleport to MultiSkyLands spawn."))
            .permission(PluginPermission.SPAWN_COMMAND)
            .executor(new SpawnCommand())
            .build());

        SUBCOMMAND.put(Arrays.asList("return"), CommandSpec.builder()
        .description(Text.of("Let you return to default world."))
        .permission(PluginPermission.RETURN_COMMAND)
        .executor(new ReturnCommand())
        .build());

        CommandSpec mainCommand = CommandSpec.builder()
                .description(Text.of("Displays all avalible commands."))
                .executor(new HelpCommand())
                .children(SUBCOMMAND)
                .build();

        Sponge.getCommandManager().register(this, mainCommand, "MultiSkyLands", "msl");
    }
}
