package dev.fabby.com.tags;

import dev.fabby.com.utils.StringUtil;

public enum Tag {

    COOL_PLAYER("fabby.tag.cool", "&6Cool"),
    WARRIOR("fabby.tag.warrior", "&cWarrior"),
    GARDENER("fabby.tag.gardener", "&aGardener"),
    BOTANIST("fabby.tag.botanist", "&2Botanist"),
    BREWER("fabby.tag.brewer", "&5Brewer"),
    KINGPIN("fabby.tag.kingpin", "&cKingpin"),
    BEAUTY("fabby.tag.beauty", "&dBeauty"),
    LATE_BLOOMER("fabby.tag.latebloomer", "&bLate Bloomer"),
    MYSTERIOUS("fabby.tag.mysterious", "&5Mysterious"),
    DAB("fabby.tag.dab", "&9Dab"),
    COOK("fabby.tag.cook", "&eCook"),
    SWEET("fabby.tag.sweet", "&3Sweet"),
    SALTY("fabby.tag.salty", "&1Salty"),
    ANGRY("fabby.tag.angry", "&cAngry"),
    SICK("fabby.tag.sick", "&5Sick"),
    MALICIOUS("fabby.tag.malicious", "&4Malicious"),
    OPTIMISTIC("fabby.tag.optimistic", "&eOptimistic"),
    DEAD_INSIDE("fabby.tag.deadinside", "&cDead Inside"),
    GRIEFER("fabby.tag.griefer", "&4Griefer"),
    TOXIC("fabby.tag.toxic", "&4Toxic"),
    BOSS("fabby.tag.boss", "&4Boss");

    private final String id, display;

    Tag(String id, String display) {
        this.id = id;
        this.display = display;
    }

    public String getId() {
        return id;
    }

    public String getDisplay() {
        return StringUtil.color("&7[" + display + "&7]&r");
    }
}
