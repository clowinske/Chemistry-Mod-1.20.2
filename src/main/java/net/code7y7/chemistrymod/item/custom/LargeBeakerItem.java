package net.code7y7.chemistrymod.item.custom;

import net.code7y7.chemistrymod.solutions.NitrogenSolution;

public class LargeBeakerItem extends ChemicalContainer {
    public LargeBeakerItem(Properties pProperties){
        super(pProperties);
        mass = 1500;
        //capacity of container
        volume = 600;
        //addLiquidContent(new NitrogenSolution(50.5));
    }
}
