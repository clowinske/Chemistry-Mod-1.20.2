package net.code7y7.chemistrymod.item;

import net.code7y7.chemistrymod.ChemistryMod;
import net.code7y7.chemistrymod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModeCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ChemistryMod.MOD_ID);

    //Glassware Tab
    public static final RegistryObject<CreativeModeTab> GLASSWARE_TAB = CREATIVE_MODE_TABS.register("glassware_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LARGEBEAKER.get()))
                    .title(Component.translatable("creativetab.glassware_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        //items in tab
                        pOutput.accept(ModItems.SMALLBEAKER.get());
                        pOutput.accept(ModItems.MEDIUMBEAKER.get());
                        pOutput.accept(ModItems.LARGEBEAKER.get());
                        pOutput.accept(ModItems.SMALLERLENMEYERFLASK.get());
                        pOutput.accept(ModItems.MEDIUMERLENMEYERFLASK.get());
                        pOutput.accept(ModItems.LARGEERLENMEYERFLASK.get());
                    })
                    .build());
    //Blocks Tab
    public static final RegistryObject<CreativeModeTab> CHEMISTRYBLOCKS_TAB = CREATIVE_MODE_TABS.register("chemistryblocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.LABTABLE.get()))
                    .title(Component.translatable("creativetab.chemistryblocks_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        //items in tab
                        pOutput.accept(ModBlocks.LABTABLE.get());
                    })
                    .build());
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
