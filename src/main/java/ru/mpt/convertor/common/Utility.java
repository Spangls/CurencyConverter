package ru.mpt.convertor.common;

public class Utility {
    public static int toIntMin(String range){
        return Integer.parseInt(range.substring(0, range.indexOf("-")).trim());
    }
    public static int toIntMax(String range){
        return Integer.parseInt(range.substring(range.indexOf("-")+1).trim());
    }
    public static float toFloatMin(String range){
        return Float.parseFloat(range.substring(0, range.indexOf("-")).trim());
    }
    public static float toFloatMax(String range){
        return Float.parseFloat(range.substring(range.indexOf("-")+1).trim());
    }
}
