package stupidprison.stupidprison.mines;

import com.google.common.collect.RangeMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import static stupidprison.stupidprison.Globals.world;

public class Mine {

    private RangeMap<Integer, Material> materials;
    private int[] pos1, pos2;

    public Mine(RangeMap<Integer, Material> materials, int[] pos1, int[] pos2) {
        this.materials = materials;
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
        Material material = null;
        while (material == null) {
            int random = (int) (Math.random() * 10000);
            material = materials.get(random);
        }
        return material;
    }
}
