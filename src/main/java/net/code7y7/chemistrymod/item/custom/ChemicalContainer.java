package net.code7y7.chemistrymod.item.custom;

import net.code7y7.chemistrymod.LiquidSolution;
import net.code7y7.chemistrymod.solutions.NitrogenSolution;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChemicalContainer extends Item {
    public ChemicalContainer(Properties pProperties){

        super(pProperties);
    }
    //initializes some values like mass after it is picked up or crafted
    public boolean Initialized = false;
    //capacity of container
    public double volume = 0;
    //mass of the container
    public double mass;
    //interval of ml poured per pour
    public double mixInterval = 10;
    //is content mixed
    boolean isMixed = false;
    //container has a cover or stopcock
    boolean isCovered;
    //liquid chemicals in container
    ArrayList<LiquidSolution> liquidContents = new ArrayList<LiquidSolution>();

    public double getVolume(){
        return volume;
    }
    public void addVolume(double n){
        if(n <= getRemainingVolume())
            volume+=n;
    }
    public void setVolume(double n){
        volume=n;
    }

    public double getRemainingVolume(){
        double space = volume;
        if(!liquidContents.isEmpty()) {
            for (int i = liquidContents.size()-1; i >= 0; i--) {
                space -= liquidContents.get(i).getVolume();
            }
        }
        return space;
    }

    public LiquidSolution getTopLiquid(){
        if(liquidContents.isEmpty())
            return null;
        return liquidContents.get(0);
    }

    public void addLiquidContent(LiquidSolution solution){
        liquidContents.add(solution);
    }

    public ArrayList<LiquidSolution> getLiquidContents(){
        return liquidContents;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pLevel.isClientSide())
            if(pUsedHand.equals(InteractionHand.MAIN_HAND) && pPlayer.getOffhandItem().getItem() instanceof ChemicalContainer){
                //pour main hand container into offhand container
                ChemicalContainer offHandItem = (ChemicalContainer) pPlayer.getOffhandItem().getItem();

                //if the offhand container doesn't have the liquid on top
                if(offHandItem.getTopLiquid() != (getTopLiquid())){
                    //add the liquid on top
                    String newLiquid = getTopLiquid().getClass().getName();
                    offHandItem.addLiquidContent(LiquidSolution.createLiquid(newLiquid));
                }
                //if the liquid isn't at container capacity
                if(offHandItem.getTopLiquid().getVolume() >= mixInterval) {
                    //add to offHand container
                    offHandItem.getTopLiquid().addVolume(mixInterval);
                    //take from mainHand container
                    getTopLiquid().setVolume(getTopLiquid().getVolume()-mixInterval);
                } else {
                    //if it is then calculate amount added //add to offHand container
                    offHandItem.addVolume(getTopLiquid().getVolume()-getRemainingVolume());
                    //take from mainHand container
                    getTopLiquid().setVolume(getTopLiquid().getVolume()-mixInterval);
                }

                pPlayer.getCooldowns().addCooldown(this,5);

            }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        if(Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.literal("§8Volume: " + volume + "mL"));
            pTooltipComponents.add(Component.literal("§8Contents:"));
            if(!liquidContents.isEmpty()) {
                for (int i = liquidContents.size()-1; i >= 0; i--) {
                    pTooltipComponents.add(Component.literal("§7 -" + liquidContents.get(i).getName() + " (" + liquidContents.get(i).getChemFormula() + ") " + liquidContents.get(i).getVolume()+"ml"));
                }
            } else
                pTooltipComponents.add(Component.literal("§7 -none"));
        } else {
            pTooltipComponents.add(Component.literal("§8Volume: " + volume + "mL"));
            pTooltipComponents.add(Component.literal("§8Contents: §3SHIFT§8"));
        }
    }
}

