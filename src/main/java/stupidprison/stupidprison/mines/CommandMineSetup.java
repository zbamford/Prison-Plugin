package stupidprison.stupidprison.mines;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandMineSetup implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName()) {
            case "setupmines":
                MineSetup s = new MineSetup(0, 10, 0, 5, 5, 5, 5, 5);
                break;
            case "resetmines":
                break;
            default:
                break;
        }

        return true;
    }
}
