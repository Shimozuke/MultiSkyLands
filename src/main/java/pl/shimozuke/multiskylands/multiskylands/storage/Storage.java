package pl.shimozuke.multiskylands.multiskylands.storage;

import com.flowpowered.math.vector.Vector3i;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.TypeTokens;
import pl.shimozuke.multiskylands.multiskylands.MultiskyLands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Storage
{
    private MultiskyLands plugin;
    private Path path;
    private ConfigurationLoader<CommentedConfigurationNode> loader;
    private CommentedConfigurationNode islands;

    public Storage(MultiskyLands plugin)
    {
        this.plugin = plugin;
        this.path = plugin.getConfigDir().resolve("islands.conf");

        try
        {
            if (Files.notExists(plugin.getConfigDir()))
            {
                Files.createDirectory(plugin.getConfigDir());
            }
            if (Files.notExists(path))
            {
                Files.createFile(path);
            }

            this.loader = HoconConfigurationLoader.builder().setPath(path).build();
            this.islands = this.loader.load();
        }
        catch (IOException e)
        {
            Sponge.getServer().getConsole().sendMessage(Text.of(TextColors.RED, "loader is dead"));
        }
    }

    public void saveIsland(final String playerName, final Vector3i islandPosition)
    {
        try
        {
            this.islands.getNode(playerName, "islandPosition").setValue(TypeTokens.VECTOR_3I_TOKEN,islandPosition);
            this.loader.save(islands);
        }
        catch (IOException | ObjectMappingException e)
        {
            Sponge.getServer().getConsole().sendMessage(Text.of(TextColors.RED, "islands save is dead"));
            e.printStackTrace();
        }
    }

    public Vector3i getIslandPosition(final String playerName)
    {
        try
        {
            Vector3i value = islands.getNode(playerName).getValue(TypeTokens.VECTOR_3I_TOKEN);
            return value;
        }
        catch (ObjectMappingException e)
        {
            Sponge.getServer().getConsole().sendMessage(Text.of(TextColors.RED, "islands get position is dead"));
            e.printStackTrace();
        }
        return null;
    }

    public void deleteIsland(final String playerName)
    {
        try
        {
            islands.removeChild(playerName);
            loader.save(islands);
        }
        catch (IOException e)
        {
            Sponge.getServer().getConsole().sendMessage(Text.of(TextColors.RED, "islands delete is dead"));
        }
    }

    public boolean islandExists(final String playerName)
    {
        final Object object = islands.getNode(playerName).getKey();
        final boolean isVirtual = islands.getNode(playerName).isVirtual();

        if (islands.getNode(playerName).isVirtual())
            return false;
        else
            return true;

//        if (islands.getNode(playerName).getKey() != null)
//            return true;
//        else
//            return false;
    }

    public boolean positionAvailability(final Vector3i islandPosition)
    {
        final List<? extends ConfigurationNode> configNodes = islands.getChildrenList();
        for (final ConfigurationNode node : configNodes)
        {
            final Vector3i thisIslandPosition;
            try
            {
                thisIslandPosition = node.getNode("islandPosition").getValue(TypeTokens.VECTOR_3I_TOKEN);
                if (thisIslandPosition == islandPosition)
                    return false;
            }
            catch (ObjectMappingException e)
            {
                Sponge.getServer().getConsole().sendMessage(Text.of(TextColors.RED, "Availavility bla bla bla"));
            }
        }
        return true;
    }

}
