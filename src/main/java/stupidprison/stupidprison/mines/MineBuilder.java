package stupidprison.stupidprison.mines;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import org.bukkit.Material;
import org.bukkit.World;
import stupidprison.stupidprison.StupidPrison;

import static org.bukkit.Bukkit.getServer;

public class MineBuilder {

    private RangeMap<Integer, Material> materials;
    private World world;
    private int[] pos1, pos2;
    private int start, end;

    public MineBuilder() {
        materials = TreeRangeMap.create();
        world = getServer().getWorld("world");
        start = 0;
        end = 0;
    }

    /**
     * Adds a new block to the mine.
     *
     * @param material The block to add to the mine
     * @param chance Percent chance of this block (only up to hundredths place will be used)
     */
    public void addMaterial(Material material, double chance) {
        end += (int) (chance * 100);
        materials.put(Range.closedOpen(start, end), material);
        start += (int) (chance * 100);
    }

    public void setBoundary(int x1, int x2, int y1, int y2, int z1, int z2) {
        pos1 = new int[3];
        pos2 = new int[3];
        pos1[0] = x1 > x2 ? x2 : x1;
        pos1[1] = y1 > y2 ? y2 : y1;
        pos1[2] = z1 > z2 ? z2 : z1;
        pos2[0] = x1 > x2 ? x1 : x2;
        pos2[1] = y1 > y2 ? y1 : y2;
        pos2[2] = z1 > z2 ? z1 : z2;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Returns the currently created mine and resets the material list
     *
     * @return The currently created mine
     */
    public Mine getMine() {
        return new Mine(materials, pos1, pos2);
    }
}
