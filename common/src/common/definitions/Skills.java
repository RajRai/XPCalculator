package common.definitions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Skills {
    public static Skill[] skills = new Skill[]{
            new Skill("Attack", Activities.combatActivities, "FF0000"),
            new Skill("Defence", Activities.combatActivities, "00FFFF"),
            new Skill("Strength", Activities.combatActivities, "00FF00"),
            new Skill("Hitpoints", Activities.hitpointsActivities, "BF0000"),
            new Skill("Ranged", Activities.combatActivities, "FF4000"),
            new Skill("Prayer", Activities.prayerActivities, "FFFFFF"),
            new Skill("Magic", Activities.combatActivities, "0000BF"),
            new Skill("Cooking", Activities.cookingActivities, "8000D0"),
            new Skill("Woodcutting", Activities.woodcuttingActivities, "008000"),
            new Skill("Fletching", Activities.fletchingActivites, "00BF00"),
            new Skill("Fishing", Activities.fishingActivities, "00FFFF"),
            new Skill("Firemaking", Activities.firemakingActivites, "FF4000"),
            new Skill("Crafting", Activities.craftingActivities, "808000"),
            new Skill("Smithing", Activities.smithingActivities, "FFFF80"),
            new Skill("Mining", Activities.miningActivities, "808040"),
            new Skill("Herblore", Activities.herbloreActivities, "008000"),
            new Skill("Agility", Activities.agilityActivities, "0000BF"),
            new Skill("Thieving", Activities.thievingActivities, "8000D0"),
            new Skill("Slayer", null, "000000"),
            new Skill("Farming", Activities.farmingActivities, "008000"),
            new Skill("Runecrafting", Activities.runecraftingActivities, "FFFFBF"),
            new Skill("Construction", Activities.constructionActivities, "FFFF80"),
            new Skill("Hunter", Activities.hunterActivities, "000000")
    };

    public static List<String> skillNames = Arrays.stream(Skills.skills).toList().stream()
            .map(Skill::getName)
            .collect(Collectors.toList());


    public static HashMap<String, Skill> skillsMap = new HashMap<>();
    static {
        for (int i = 0; i < skills.length; i++){
            skillsMap.put(skills[i].getName(), skills[i]);
        }
    }
}
