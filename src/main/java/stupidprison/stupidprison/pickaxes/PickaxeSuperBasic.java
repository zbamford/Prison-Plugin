package stupidprison.stupidprison.pickaxes;

import org.bukkit.inventory.ItemStack;
import org.ipvp.canvas.Menu;
import org.ipvp.canvas.type.ChestMenu;

public class PickaxeSuperBasic extends Pickaxe {

    public PickaxeSuperBasic() {
        super();
        lore.add("A very cheap pickaxe used by new prisoners.");
        lore.add("Doesn't have any special abilities but can be upgraded.");
    }

    public Menu createMenu() {
        return ChestMenu.builder(6).title("Menu").build();
    }

    @Override
    public ItemStack toItem() {
        return super.toItem();
    }
}
