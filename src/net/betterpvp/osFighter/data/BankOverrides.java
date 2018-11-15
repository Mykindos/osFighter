package net.betterpvp.osFighter.data;

import org.osbot.rs07.api.map.Area;

public enum BankOverrides {


    STRONGHOLD(new Area(1850, 5172, 2408, 5329), Bank.EDGEVILLE),
    MOLE_LAIR(new Area(1716, 5250, 1796, 5120), Bank.FALADOR_EAST),
    TAVERLY_DUNGEON(new Area(
            new int[][]{
                    { 2957, 9808 },
                    { 2960, 9799 },
                    { 2976, 9798 },
                    { 2973, 9760 },
                    { 2947, 9667 },
                    { 2881, 9661 },
                    { 2875, 9690 },
                    { 2796, 9684 },
                    { 2804, 9857 },
                    { 2950, 9859 }
            }
    ), Bank.FALADOR_WEST),
    UPPER_TAVERLY_DUNGEON(new Area(2749, 9870, 2981, 9756).setPlane(1), Bank.FALADOR_WEST),
    DWARVEN_MINE_DUNGEON(new Area(2964, 9854, 3066, 9690), Bank.FALADOR_WEST),
    EDGEVILLE_DUNGEON(new Area(3072, 9999, 3151, 9786), Bank.EDGEVILLE),
    VARROCK_SEWERS(new Area(3292, 9955, 3152, 9853), Bank.VARROCK_WEST),
    REVENANT_CAVE(new Area(3131, 10237, 3275, 10046), Bank.EDGEVILLE),
    FREMENNIK_SLAYER_DUNGEON(new Area(2813, 10047, 2682, 9949), Bank.CAMELOT),
    BRIMHAVEN_DUNGEON(new Area(2623, 9599, 2751, 9409), Bank.ARDOUGNE_SOUTH),
    KOUREND_DUNGEON(new Area(1586, 10118, 1744, 9974), Bank.KOUREND),
    CHASM_OF_FIRE_1(new Area(1404, 10109, 1470, 10046), Bank.KOUREND),
    CHASM_OF_FIRE_2(new Area(1404, 10109, 1470, 10046).setPlane(1), Bank.KOUREND),
    CHASM_OF_FIRE_3(new Area(1404, 10109, 1470, 10046).setPlane(2), Bank.KOUREND);




    private Area a;
    private Bank b;

    BankOverrides(Area a, Bank b){

    }

    public Area getArea(){
        return a;
    }

    public Bank getBank(){
        return b;
    }
}
