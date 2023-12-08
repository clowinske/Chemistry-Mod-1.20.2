package net.code7y7.chemistrymod.item;

import net.code7y7.chemistrymod.ChemistryMod;
import net.code7y7.chemistrymod.item.custom.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;

import java.util.logging.Logger;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ChemistryMod.MOD_ID);
    public static final RegistryObject<Item> SMALLBEAKER = ITEMS.register("small_beaker",
            () -> new SmallBeakerItem(new Item.Properties()));
    public static final RegistryObject<Item> MEDIUMBEAKER = ITEMS.register("medium_beaker",
            () -> new MediumBeakerItem(new Item.Properties()));
    public static final RegistryObject<Item> LARGEBEAKER = ITEMS.register("large_beaker",
            () -> new LargeBeakerItem(new Item.Properties()));
    public static final RegistryObject<Item> SMALLERLENMEYERFLASK = ITEMS.register("small_erlenmeyer_flask",
            () -> new SmallErlenmeyerItem(new Item.Properties()));
    public static final RegistryObject<Item> MEDIUMERLENMEYERFLASK = ITEMS.register("medium_erlenmeyer_flask",
            () -> new MediumErlenmeyerItem(new Item.Properties()));
    public static final RegistryObject<Item> LARGEERLENMEYERFLASK = ITEMS.register("large_erlenmeyer_flask",
            () -> new LargeErlenmeyerItem(new Item.Properties()));
    public static final RegistryObject<Item> SULPHURPOWDER = ITEMS.register("sulphur_powder",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
