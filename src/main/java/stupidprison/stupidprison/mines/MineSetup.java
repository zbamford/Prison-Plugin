package stupidprison.stupidprison.mines;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import stupidprison.stupidprison.Globals;

import java.util.List;
import java.util.function.Function;

import static stupidprison.stupidprison.Globals.materials;
import static stupidprison.stupidprison.Globals.world;

public class MineSetup {

    private int x, y, z;
    private int xSize, ySize, zSize;
    private int xBorder, zBorder;
    private int totalMineX, totalMineZ;

    private MineManager manager;
    public MineSetup(int x, int y, int z, int xSize, int ySize, int zSize, int xBorder, int zBorder) {
        Globals.log("Setting up mines...");
        this.x = x;
        this.y = y;
        this.z = z;
        this.xSize = xSize;
        this.ySize = ySize;
        this.zSize = zSize;
        this.xBorder = xBorder;
        this.zBorder = zBorder;

        totalMineX = xSize + (xBorder * 2) + 2;
        totalMineZ = zSize + (zBorder * 2) + 2;

        manager = new MineManager(500);

        setupMines();
    }

    private int numMines(int block) {
        if (block == 0 || block == 27) {
            return 1;
        } else {
            return block + 4;
        }
    }

    private void setupMines() {
        Globals.log("Materials size: " + materials.size());
        for (int i = 0; i < materials.size() - 1; i++) {
            Globals.log("Setting up block " + i);
            setupMineBlock(i);
        }
    }

    public void setupMineBlock(int block) {
        Material startMat, endMat;
        if (block == 0 || block == 27) {
            startMat = endMat = materials.get(block);
        } else {
            startMat = materials.get(block - 1);
            endMat = materials.get(block);
        }

        MineBuilder builder;
        Mine mine;
        int x1, x2, z1, z2;
        int numMines = numMines(block);

        for (int i = 0; i < numMines; i++) {
            builder = new MineBuilder();
            if (startMat == endMat) {
                builder.addMaterial(startMat, 100);
            } else {
                builder.addMaterial(startMat, ((numMines - i - 1) / (double) numMines) * 100); // fix chances
                builder.addMaterial(endMat, ((i + 1) / (double) numMines) * 100);
            }
            x1 = (totalMineX * block) + x + xBorder + 1;
            x2 = x1 + xSize - 1;
            z1 = (totalMineZ * i) + z + zBorder + 1;
            z2 = z1 + zSize - 1;
            builder.setBoundary(x1, x2, y - ySize, y, z1, z2);

            mine = builder.getMine();
            manager.registerMine(mine, id(block, i));
            buildBoundary(block, i);
        }
    }

    private String id(int block, int mine) {
        if (block == 0) {
            return "Tutorial";
        } else if (block == 27) {
            return "Free";
        } else {
            return ((char) (block + 'A' - 1)) + "" + (mine + 1);
        }
    }

    private void buildBoundary(int block, int mine) {
        int x1, x2, y1, y2, z1, z2;

        x1 = (totalMineX * block) + x + xBorder;
        x2 = x1 + xSize + 1;
        y1 = y - ySize - 1;
        y2 = y;
        z1 = (totalMineZ * mine) + z + zBorder;
        z2 = z1 + zSize + 1;

        walls(x1, x2, y1, y2, z1, z2, Material.BEDROCK);
        fill(x1, x2, y1, y1, z1, z2, Material.BEDROCK);
        border(x1, x2, y2, z1, z2, Material.QUARTZ_BLOCK);
    }

    private void walls(int x1, int x2, int y1, int y2, int z1, int z2, Material material) {
        fill(x1, x2, y1, y2, z1, z1, material);
        fill(x1, x1, y1, y2, z1, z2, material);
        fill(x1, x2, y1, y2, z2, z2, material);
        fill(x2, x2, y1, y2, z1, z2, material);
    }

    private void border(int x1, int x2, int y, int z1, int z2, Material material) {
        fill(x1 - xBorder, x1 - 1, y, y, z1 - zBorder, z2 + zBorder, material);
        fill(x2 + 1, x2 + xBorder, y, y, z1 - zBorder, z2 + zBorder, material);
        fill(x1, x2, y, y, z2 + 1, z2 + zBorder, material);
        fill(x1, x2, y, y, z1 - zBorder, z1 - 1, material);
    }

    private void fill(int x1, int x2, int y1, int y2, int z1, int z2, Material material) {
        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                for (int z = z1; z <= z2; z++) {
                    setBlock(x, y, z, material);
                }
            }
        }
    }

    private void setBlock(int x, int y, int z, Material material) {
        Block currBlock = world.getBlockAt(x, y, z);
        currBlock.setType(material);
    }
}
