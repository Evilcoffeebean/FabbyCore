package dev.fabby.com.fakeplayers;

import lombok.Getter;

import java.util.Random;

@Getter
public enum FakePlayerList {

    //immortal
    Vengeancey("Vengeancey", "immortal", "Vengeancey"),
    Scharaff("Scharaff", "immortal", "Scharaff"),

    //elite
    SaintGalbert("SaintGalbert", "elite", "SaintGalbert"),
    Dirt_Miser("Dirt_Miser", "elite", "Dirt_Miser"),
    Kubyara_("Kubyara_", "elite", "Kubyara_"),
    cutiecake0057("cutiecake0057", "elite", "cutiecake0057"),

    //mvp
    roselie("roselie", "mvp", "roselie"),
    Klapekkk("Klapekkk", "mvp", "Klapekkk"),
    Tegannnn("Tegannnn", "mvp", "Tegannnn"),
    Enit_S2("Enit_S2", "mvp", "Enit_S2"),

    //vip
    KittyPhonk("KittyPhonk", "vip", "KittyPhonk"),
    imawkward21("imawkward21", "vip", "imawkward21"),
    okinasai("okinasai", "vip", "okinasai"),

    //booster
    CatDontHack("CatDontHack", "booster", "CatDontHack"),
    yusins("yusins", "booster", "yusins"),
    Cashier("Cashier", "booster", "Cashier"),
    yeqqy("yeqqy", "booster", "yeqqy"),
    AnxJess("AnxJess", "booster", "AnxJess"),

    //default
    Dienstwaffe("Dienstwaffe", "default", "Dienstwaffe"),
    Cellbit("Cellbit", "default", "Cellbit"),
    DOGbanana10("DOGbanana10", "default", "DOGbanana10"),
    Mad6y("Mad6y", "default", "Mad6y"),
    emenaeza("emenaeza", "default", "emenaeza"),
    Luxuoso("Luxuoso", "default", "Luxuoso"),
    NyxGoesMeow("NyxGoesMeow", "default", "NyxGoesMeow"),
    Mirphiz("Mirphiz", "default", "Mirphiz"),
    Rowet("Rowet", "default", "Rowet"),
    Dissociate("Dissociate", "default", "Dissociate"),
    FluxGamer7("FluxGamer7", "default", "FluxGamer7"),
    Mthc("Mthc", "default", "Mthc"),
    Hexar("Hexar", "default", "Hexar"),
    matscan("matscan", "default", "matscan"),
    smores_2017("smores_2017", "default", "smores_2017");

    private static final Random random = new Random();
    private final String name, rank, displayName;

    FakePlayerList(String name, String rank, String displayName) {
        this.name = name;
        this.rank = rank;
        this.displayName = displayName;
    }

    public static FakePlayerList getRandomPlayer() {
        int index = random.nextInt(values().length);
        return values()[index];
    }

    //This is a temporary solution to the tab completions for npc names without their ids
    //obsolete now, will leave it just in case
    public static String[] getAllNames() {
        return new String[] {
                "Vengeancey",
                "Scharaff",
                "SaintGalbert",
                "Dirt_Miser",
                "Kubyara_",
                "cutiecake0057",
                "roselie",
                "Klapekkk",
                "Tegannnn",
                "Enit_S2",
                "KittyPhonk",
                "imawkward21",
                "okinasai",
                "CatDontHack",
                "yusins",
                "Cashier",
                "yeqqy",
                "AnxJess",
                "Dienstwaffe",
                "Cellbit",
                "DOGbanana10",
                "Mad6y",
                "emenaeza",
                "Luxuoso",
                "NyxGoesMeow",
                "Mirphiz",
                "Rowet",
                "Dissociate",
                "FluxGamer7",
                "Mthc",
                "Hexar",
                "matscan",
                "smores_2017"
        };
    }
}
