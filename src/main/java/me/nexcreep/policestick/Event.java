package me.nexcreep.policestick;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class Event implements Listener {
    Logger log = new Logger();

    public static final String[] auth = {"NexCreep", "Ronny"};
    public static final String itemTag = "Police Baton";
    public static final String[] prisionList = {"comisaria", "elgulag", "thesky1"};
    public static Integer actualP = 0;

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e){
        //Primero comporbamos que el evento se haya creado por un jugador
        if (e.getDamager().getType() == EntityType.PLAYER && e.getEntity().getType() == EntityType.PLAYER) {
            Player pe = (Player) e.getDamager();
            Player pv = (Player) e.getEntity();
            ItemStack item = pe.getInventory().getItemInMainHand();
            //Luego comprobamos que la herramienta haya pegado a un jugador, que sea un autorizado y que el item tenga
            //el Nametag apropiado
            if (Arrays.asList(auth).contains(pe.getName()) && item.getItemMeta().getDisplayName().equals(itemTag)) {

                //Informamos al log de lo que a pasado
                log.info(String.format("%s sent a %s to prison", pe.getName(), pv.getName()), false);

                //Llamamos a la consola
                ConsoleCommandSender console = Bukkit.getConsoleSender();

                //Y ejecutamos el comando:
                //jail <player> <jailname> [datediff]
                String command = String.format("jail %s %s 15m", pv.getName(), prisionList[actualP]);
                Bukkit.dispatchCommand(console, command);
                pe.sendMessage(String.format("§6Has mandado a la carcel de §b%s§6 a §b%s§6 durante §b15 minutos", prisionList[actualP], pv.getName()));
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        //Comprobamos que la interaccion la haya hecho un jugador que ademas este autorizado en la lista 'auth'
        if (Arrays.asList(auth).contains(e.getPlayer().getName()) && e.getPlayer().getType() == EntityType.PLAYER){
            //Sintetizamos los datos necesarios
            Player p = e.getPlayer();
            Action a = e.getAction();
            ItemStack item = p.getInventory().getItemInMainHand();
            //Ahora se comprueba si la accion es la correcta y es con el item correcto
            if (a == Action.RIGHT_CLICK_AIR && item.getItemMeta().getDisplayName().equals(itemTag)){
                if (actualP < Arrays.asList(prisionList).size() - 1){
                    //Si el numero del cursor no supera el tamaño de
                    // la lista menos 1 sumale uno al cursor
                    actualP = actualP + 1;
                }else {
                    //Si no restablece el valor del cursor a cero
                    actualP = 0;
                }
                //Envío de confimación al jugador motor
                p.sendMessage(String.format("§6Confirmado §b%s", prisionList[actualP]));
            }
        }
    }
}
