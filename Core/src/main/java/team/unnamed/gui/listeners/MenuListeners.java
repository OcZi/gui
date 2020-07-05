package team.unnamed.gui.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import team.unnamed.gui.menu.MenuBuilder;
import team.unnamed.gui.menu.MenuManager;

import java.util.concurrent.atomic.AtomicReference;

public class MenuListeners implements Listener {

    private final MenuManager menuManager;

    public MenuListeners(MenuManager menuManager) {
        this.menuManager = menuManager;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        if (inventory == null) {
            return;
        }

        if (event.getCurrentItem() == null) {
            return;
        }

        AtomicReference<MenuBuilder> builder = new AtomicReference<>();

        menuManager.getMenuBuilders().forEach(menuBuilder -> {
            if (ChatColor.stripColor(menuBuilder.getTitle()).equals(ChatColor.stripColor(event.getView().getTitle()))) {
                builder.set(menuBuilder);
            }
        });

        MenuBuilder menuBuilder = builder.get();

        if(menuBuilder == null) {
            return;
        }

        menuBuilder.getButtons().forEach(simpleButton -> {
            if(simpleButton.getSlot() == event.getSlot()) {
                event.setCancelled(simpleButton.getButton().clickEvent(event));
            }
        });
    }

}