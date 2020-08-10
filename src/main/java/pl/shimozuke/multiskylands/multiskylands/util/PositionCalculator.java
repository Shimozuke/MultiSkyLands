package pl.shimozuke.multiskylands.multiskylands.util;

import com.flowpowered.math.vector.Vector3i;
import pl.shimozuke.multiskylands.multiskylands.MultiskyLands;
import pl.shimozuke.multiskylands.multiskylands.storage.Storage;

public class PositionCalculator
{
    private MultiskyLands plugin;
    private Storage storage;
    private int x = 200;
    private int z = 200;
    private Vector3i position = new Vector3i(0, 99, 0);
    private int counter = 1;

    public PositionCalculator(MultiskyLands plugin)
    {
        this.plugin = plugin;
        this.storage = plugin.getStorage();
    }

    public Vector3i calculatePosition(String playerName)
    {
        if (!storage.islandExists(playerName))
        {
            while (true)
            {
                for (int j = 0; j < counter; j++)
                {
                    if (counter % 2 == 1)
                    {
                        position = position.add(x, 0, 0);
                        
                        if (storage.positionAvailability(position))
                        {
                            return position;
                        }
                    }
                    else
                    {
                        position = position.add(-x, 0, 0);

                        if (storage.positionAvailability(position))
                        {
                            return position;
                        }
                    }
                }
                for (int j = 0; j < counter; j++)
                {
                    if (counter % 2 == 1)
                    {
                        position = position.add(0, 0, z);

                        if (storage.positionAvailability(position))
                        {
                            return position;
                        }
                    }
                    else
                    {
                        position = position.add(0, 0, -z);

                        if (storage.positionAvailability(position))
                        {
                            return position;
                        }
                    }
                }
                counter++;
            }
        }
        return null;
    }

}
