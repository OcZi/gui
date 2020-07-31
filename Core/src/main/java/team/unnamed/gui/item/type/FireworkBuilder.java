package team.unnamed.gui.item.type;

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.ArrayList;
import java.util.List;

public class FireworkBuilder extends DefaultItemBuilder {

    private int power = 3;

    private final List<FireworkEffect> fireworkEffects = new ArrayList<>();

    FireworkBuilder(Material material) {
        super(material);
    }

    FireworkBuilder(Material material, int amount) {
        super(material, amount);
    }

    public FireworkBuilder addAttribute(FireworkEffect fireworkEffect) {
        fireworkEffects.add(fireworkEffect);

        return this;
    }

    public FireworkBuilder power(int power) {
        this.power = power;

        return this;
    }

    @Override
    public ItemStack build() {
        if (!material.name().startsWith("FIREWORK")) {
            throw new IllegalArgumentException("This material must be firework!");
        }

        ItemStack item = super.build();

        FireworkMeta fireworkMeta = (FireworkMeta) item.getItemMeta();

        fireworkEffects.forEach(fireworkMeta::addEffect);

        fireworkMeta.setPower(power);

        item.setItemMeta(fireworkMeta);

        return item;
    }

}