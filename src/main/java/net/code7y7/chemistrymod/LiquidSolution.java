package net.code7y7.chemistrymod;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LiquidSolution {
    public LiquidSolution(){

    }
    public String name;
    public String chemFormula;
    public double molarMass;
    public double volume;
    //color rgb, or white for colorless
    public Color color;
    //
    public double opacity;
    //default temperature is room temp
    public double temperature = 25.0;

    public String getName(){
        return name;
    }
    public String getChemFormula(){
        return chemFormula;
    }

    public double getVolume(){
        return volume;
    }

    public void addVolume(double amount){
        volume += amount;
    }

    public void setVolume(double n){
        volume = n;
    }

    public static LiquidSolution createLiquid(String className) {
        try {
            // Get the class object based on the class name
            Class<?> clazz = Class.forName(className);

            // Get the constructor of the class
            Constructor<?> constructor = clazz.getConstructor();

            // Create an instance of the class
            return (LiquidSolution) constructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException |
                 InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

}
