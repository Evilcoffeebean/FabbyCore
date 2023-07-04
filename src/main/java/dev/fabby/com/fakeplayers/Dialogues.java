package dev.fabby.com.fakeplayers;

import dev.fabby.com.Core;
import lombok.Getter;
import org.bukkit.Material;

import java.util.Random;
import java.util.Set;

@Getter
public enum Dialogues {

    QUESTION_ONE("does anyone have any {answer}"),
    QUESTION_TWO("how do I find {answer}"),
    QUESTION_THREE("how do I craft {answer}"),
    QUESTION_FOUR("how do I get {answer}"),
    QUESTION_FIVE("can I apply for {answer}"),

    ANSWER_ONE("coal, flint, flint and steel, boats, oak logs, shears, birch wood, acacia wood, totems, gold ingots, phantom membrane, shulker shells, shulkers, cobblestone, food, seeds, vouchers, fishing rods, netherite scraps, golden carrots, golden apples, wood, enchanted books, totems"),
    ANSWER_TWO("the end portal, crying obsidian, a jungle temple, a bastion, a nether fortress, a village, a witch hut"),
    ANSWER_THREE("totems of undying, glowstone dust, repeaters, trap doors, fireworks, a leash, a smoker, a blast furnace, a stonecutter, a smithing table"),
    ANSWER_FOUR("crate keys, ranks, money, tags, particle effects, pets"),
    ANSWER_FIVE("staff, helper, mod, admin, developer, builder, youtube rank, twitch rank, owner, coowner"),

    SIMPLE_STATEMENT_ONE("oh fuck lol"),
    SIMPLE_STATEMENT_TWO("damn this is nice"),
    SIMPLE_STATEMENT_THREE("need to ss this lmao"),
    SIMPLE_STATEMENT_FOUR("I'll hop in a call in a sec"),
    SIMPLE_STATEMENT_FIVE("nice"),
    SIMPLE_STATEMENT_SIX("join vc"),
    SIMPLE_STATEMENT_SEVEN("trading at spawn"),
    SIMPLE_STATEMENT_EIGHT("i recognize that bedrock pattern"),
    SIMPLE_STATEMENT_NINE("lmfao"),
    SIMPLE_STATEMENT_TEN("wait what"),
    SIMPLE_STATEMENT_ELEVEN("hey"),
    SIMPLE_STATEMENT_TWELVE("what"),
    SIMPLE_STATEMENT_THIRTEEN("any1 looking to cpvp?"),
    SIMPLE_STATEMENT_FOURTEEN("looking for someone to team with"),
    SIMPLE_STATEMENT_FIFTEEN("just leave it at the echest"),
    SIMPLE_STATEMENT_SIXTEEN("whats this, the handsome contest"),
    SIMPLE_STATEMENT_SEVENTEEN("nice spiderman reference lol"),
    SIMPLE_STATEMENT_EIGHTEEN("loving the sopranos quotes lmfao"),

    NPC_DIALOGUE_1("{npc} where you at"),
    NPC_DIALOGUE_2("where did you go now {npc}"),
    NPC_DIALOGUE_3("where'd you find that {npc} lol"),
    NPC_DIALOGUE_4("over here {npc}"),
    NPC_DIALOGUE_5("here {npc}"),
    NPC_DIALOGUE_6("nah not there {npc}"),
    NPC_DIALOGUE_7("{npc} check dm"),
    NPC_DIALOGUE_8("{npc} check discord"),
    NPC_DIALOGUE_9("{npc} ill send u coords"),
    NPC_DIALOGUE_10("did you take my shit again {npc}"),
    NPC_DIALOGUE_11("{npc} ur an asshole"),
    NPC_DIALOGUE_12("{npc} lmaoo"),
    NPC_DIALOGUE_13("let me get some of that {npc}"),
    NPC_DIALOGUE_14("{npc} got any {item}"),
    NPC_DIALOGUE_15("{npc} what are you selling"),
    NPC_DIALOGUE_16("{npc} meet me at spawn"),
    NPC_DIALOGUE_17("*inserts tobey mcguire reference* {npc}"),
    NPC_DIALOGUE_18("{npc} hax"),
    NPC_DIALOGUE_19("the villager farm wont work {npc}"),
    NPC_DIALOGUE_20("sharp as a fucking cueball {npc}"),
    NPC_DIALOGUE_21("all this from a slice of gabagool {npc}"),
    NPC_DIALOGUE_22("thats why dinosaurs dont exist no more {npc}"),
    NPC_DIALOGUE_23("ur mother was working the bonbon concession at the eiffel tower {npc}"),
    NPC_DIALOGUE_24("my guy killed 16 czechoslovakians {npc}"),
    NPC_DIALOGUE_25("guy was an interior decorator {npc}"),
    NPC_DIALOGUE_26("his house looked like shit {npc}"),
    NPC_DIALOGUE_27("strong as a bull, handsome {npc}"),
    NPC_DIALOGUE_28("ur sisters ass {npc}"),
    NPC_DIALOGUE_29("the sacred and the propane {npc}"),
    NPC_DIALOGUE_30("{npc} chin up king"),
    NPC_DIALOGUE_31("I missed the part where that's my problem {npc}"),
    NPC_DIALOGUE_32("if there are any fleas on u they're paying fckn rent {npc}"),
    NPC_DIALOGUE_33("{npc} dm me the coords again i lost them"),
    NPC_DIALOGUE_34("{npc} cpvp outside of spawn"),
    NPC_DIALOGUE_35("fight me {npc}"),
    NPC_DIALOGUE_36("${number}k {npc}"),
    NPC_DIALOGUE_37("dm me the price {npc}"),
    NPC_DIALOGUE_38("no thx {npc}"),
    NPC_DIALOGUE_39("{npc} dm"),
    NPC_DIALOGUE_40("{npc} fucking scammer"),
    NPC_DIALOGUE_41("nice hacks {npc}"),
    NPC_DIALOGUE_42("ffs {npc}"),
    NPC_DIALOGUE_43("{npc} turn off that fucking killaura"),
    NPC_DIALOGUE_44("report {npc}"),
    NPC_DIALOGUE_45("somebody ban {npc} already ffs"),
    NPC_DIALOGUE_46("ur fucked now {npc}"),
    NPC_DIALOGUE_47("again {npc}"),
    NPC_DIALOGUE_48("{npc} when you wanna rematch tho"),
    NPC_DIALOGUE_49("{npc} farm goes there"),
    NPC_DIALOGUE_50("{npc} mic isnt working");


    private static final Random random = new Random();
    private final String text;

    Dialogues(String text) {
        this.text = text;
    }

    public static String buildFinalDialogue() {
        int result = random.nextInt(100);

        if (result < 35) {
            if (result < 25) {
                return selectRandomComplexDialogue();
            }
            return selectRandomSimpleDialogue();
        }
        return selectInterNpcDialogue();
    }

    private static String selectInterNpcDialogue() {
        Dialogues[] options = {
                NPC_DIALOGUE_1, NPC_DIALOGUE_2, NPC_DIALOGUE_3, NPC_DIALOGUE_4, NPC_DIALOGUE_5,
                NPC_DIALOGUE_6, NPC_DIALOGUE_7, NPC_DIALOGUE_8, NPC_DIALOGUE_9, NPC_DIALOGUE_10,
                NPC_DIALOGUE_11, NPC_DIALOGUE_12, NPC_DIALOGUE_13, NPC_DIALOGUE_14, NPC_DIALOGUE_15,
                NPC_DIALOGUE_16, NPC_DIALOGUE_17, NPC_DIALOGUE_18, NPC_DIALOGUE_19, NPC_DIALOGUE_20,
                NPC_DIALOGUE_21, NPC_DIALOGUE_22, NPC_DIALOGUE_23, NPC_DIALOGUE_24, NPC_DIALOGUE_25,
                NPC_DIALOGUE_26, NPC_DIALOGUE_27, NPC_DIALOGUE_28, NPC_DIALOGUE_21, NPC_DIALOGUE_30,
                NPC_DIALOGUE_31, NPC_DIALOGUE_32, NPC_DIALOGUE_33, NPC_DIALOGUE_34, NPC_DIALOGUE_35,
                NPC_DIALOGUE_36, NPC_DIALOGUE_37, NPC_DIALOGUE_38, NPC_DIALOGUE_39, NPC_DIALOGUE_40,
                NPC_DIALOGUE_41, NPC_DIALOGUE_42, NPC_DIALOGUE_43, NPC_DIALOGUE_44, NPC_DIALOGUE_45,
                NPC_DIALOGUE_46, NPC_DIALOGUE_47, NPC_DIALOGUE_48, NPC_DIALOGUE_49, NPC_DIALOGUE_50
        };
        int dialogueIndex = random.nextInt(options.length);
        Dialogues dialogueResult = options[dialogueIndex];

        Set<String> savedPlayers = Core.getCore().getNpcManager().getNpcMap().keySet();
        int randomIndex = random.nextInt(savedPlayers.size());
        FakePlayerList fake = FakePlayerList.valueOf((String) savedPlayers.toArray()[randomIndex]);

        int materialIndex = random.nextInt(Material.values().length);
        Material randomMaterial = Material.values()[materialIndex];
        String materialName = randomMaterial.toString().toLowerCase().replace("_", " ");

        return dialogueResult.text.replace("{npc}", fake.getName()).replace("{number}", String.valueOf(random.nextInt(64))).replace("{item}", materialName);
    }

    private static String selectRandomSimpleDialogue() {
        Dialogues[] options = {
                SIMPLE_STATEMENT_ONE, SIMPLE_STATEMENT_TWO, SIMPLE_STATEMENT_THREE,
                SIMPLE_STATEMENT_FOUR, SIMPLE_STATEMENT_FIVE, SIMPLE_STATEMENT_SIX,
                SIMPLE_STATEMENT_SEVEN, SIMPLE_STATEMENT_EIGHT, SIMPLE_STATEMENT_NINE,
                SIMPLE_STATEMENT_TEN, SIMPLE_STATEMENT_ELEVEN, SIMPLE_STATEMENT_TWELVE,
                SIMPLE_STATEMENT_THIRTEEN, SIMPLE_STATEMENT_FOURTEEN, SIMPLE_STATEMENT_FIFTEEN,
                SIMPLE_STATEMENT_SIXTEEN, SIMPLE_STATEMENT_SEVENTEEN, SIMPLE_STATEMENT_EIGHTEEN
        };
        int index = random.nextInt(options.length);
        Dialogues result = options[index];

        return result.text;
    }

    private static String selectRandomComplexDialogue() {
        String[] options = {firstComplex(), secondComplex(), thirdComplex(), fourthComplex(), fifthComplex()};
        int index = random.nextInt(options.length);

        return options[index];
    }

    private static String firstComplex() {
        String[] answers = ANSWER_ONE.text.split(", ");
        int index = random.nextInt(answers.length);
        String randomAnswer = answers[index];

        return QUESTION_ONE.text.replace("{answer}", randomAnswer);
    }

    private static String secondComplex() {
        String[] answers = ANSWER_TWO.text.split(", ");
        int index = random.nextInt(answers.length);
        String randomAnswer = answers[index];

        return QUESTION_TWO.text.replace("{answer}", randomAnswer);
    }

    private static String thirdComplex() {
        String[] answers = ANSWER_THREE.text.split(", ");
        int index = random.nextInt(answers.length);
        String randomAnswer = answers[index];

        return QUESTION_THREE.text.replace("{answer}", randomAnswer);
    }

    private static String fourthComplex() {
        String[] answers = ANSWER_FOUR.text.split(", ");
        int index = random.nextInt(answers.length);
        String randomAnswer = answers[index];

        return QUESTION_FOUR.text.replace("{answer}", randomAnswer);
    }

    private static String fifthComplex() {
        String[] answers = ANSWER_FIVE.text.split(", ");
        int index = random.nextInt(answers.length);
        String randomAnswer = answers[index];

        return QUESTION_FIVE.text.replace("{answer}", randomAnswer);
    }
}
