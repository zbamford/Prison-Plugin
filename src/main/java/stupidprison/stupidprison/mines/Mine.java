package stupidprison.stupidprison.mines;

import com.google.common.collect.RangeMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import stupidprison.stupidprison.StupidPrison;

public class Mine {

    private RangeMap<Integer, Material> materials;
    private World world;
    private int[] pos1, pos2;

    public Mine(RangeMap<Integer, Material> materials, World world, int[] pos1, int[] pos2) {
        this.materials = materials;
        this.world = world;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    public void refill() {
        Material currMat;
        Block currBlock;

        for (int x = pos1[0]; x <= pos2[0]; x++) {
            for (int y = pos1[1]; y <= pos2[1]; y++) {
                for (int z = pos1[2]; z <= pos2[2]; z++) {
                    currMat = chooseMaterial();
                    currBlock = world.getBlockAt(x, y, z);
                    currBlock.setType(currMat);
                }
            }
        }
    }

    private Material chooseMaterial() {
        int random = (int) (Math.random() * 10000);
        return materials.get(random);
    }
}
