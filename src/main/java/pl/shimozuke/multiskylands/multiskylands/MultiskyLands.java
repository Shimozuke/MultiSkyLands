package pl.shimozuke.multiskylands.multiskylands;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.World;
import pl.shimozuke.multiskylands.multiskylands.commands.CreateCommand;
import pl.shimozuke.multiskylands.multiskylands.commands.HelpCommand;
import pl.shimozuke.multiskylands.multiskylands.commands.ReturnCommand;
import pl.shimozuke.multiskylands.multiskylands.commands.SpawnCommand;
import pl.shimozuke.multiskylands.multiskylands.storage.Storage;
import pl.shimozuke.multiskylands.multiskylands.util.SpawnCreator;
import pl.shimozuke.multiskylands.multiskylands.util.VoidCreator;

import java.nio.file.Path;
import java.util.*;

@Plugin(id = PluginInfo.ID, name = PluginInfo.Name, authors = PluginInfo.Authors, url = PluginInfo.URL, description = PluginInfo.Description)
public class MultiskyLands
{
    public static final Map<List<String>, CommandSpec> SUBCOMMAND = new HashMap<>();
    private Storage storage;

    public static final String MULTISKYLANDS_WORLD_NAME = "MultiSkyLands";

    @Inject
    @ConfigDir(sharedRoot = false)
    private Path configDir;

    @Inject
    private Logger logger;

    @Listener
    public void onGamePreInitialization(GamePreInitializationEvent event)
    {
        this.storage = new Storage(this);
    }

    @Listener
    public void onGameInitialization(GameInitializationEvent event)
    {
        initCommands();
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event)
    {
        if (!Sponge.getServer().getWorld(MULTISKYLANDS_WORLD_NAME).isPresent())
        {
            VoidCreator.createVoid();
            SpawnCreator spawnCreator = new SpawnCreator(this);
            spawnCreator.createSpawn();
        }
    }

    public Path getConfigDir()
    {
        return configDir;
    }

    public Storage getStorage()
    {
        return storage;
    }

    public Optional<World> getMultiSkylandsWorld()
    {
        return Sponge.getServer().getWorld(MULTISKYLANDS_WORLD_NAME);
    }

    private void initCommands()
    {
        SUBCOMMAND.put(Arrays.asList("spawn"), CommandSpec.builder()
            .description(Text.of("Teleport to MultiSkyLands spawn."))
            .permission(PluginPermission.SPAWN_COMMAND)
            .executor(new SpawnCommand(this))
            .build());

        SUBCOMMAND.put(Arrays.asList("return"), CommandSpec.builder()
            .description(Text.of("Let you return to default world."))
            .permission(PluginPermission.RETURN_COMMAND)
            .executor(new ReturnCommand())
            .build());

        SUBCOMMAND.put(Arrays.asList("create"), CommandSpec.builder()
            .description(Text.of("Let you create island."))
            .permission(PluginPermission.CREATE_COMMAND)
            .executor(new CreateCommand(this))
            .build());

        CommandSpec mainCommand = CommandSpec.builder()
                .description(Text.of("Displays all avalible commands."))
                .executor(new HelpCommand())
                .children(SUBCOMMAND)
                .build();

        Sponge.getCommandManager().register(this, mainCommand, "multiskylands", "msl");
    }
}
