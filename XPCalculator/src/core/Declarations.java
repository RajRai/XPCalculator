package core;

public class Declarations {

        /* I was thinking about writing scrapers for the wiki for all this
        but it actually seems easier than writing a unique scraper for all 23 pages
        given the fact maybe 20 people will use this app */

        public static Activity[] combatActivities = new Activity[]{
                new Activity("Damage (focused XP)", 1000, "Anywhere",  "Combat Training or Monsters", null),
                new Activity("Damage (shared XP, 2 skills)", 500, "Anywhere",  "Combat Training or Monsters", null),
                new Activity("Damage (shared XP, 3 skills)", 333, "Anywhere",  "Combat Training or Monsters", null)
        };

        public static Activity[] hitpointsActivities = new Activity[]{
                new Activity("Damage", 333, "Anywhere",  "Combat Training or Monsters", null)
        };

        public static Activity[] prayerActivities = new Activity[]{
                new Activity("Bones", 180, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Bones", 1)}),
                new Activity("Monkey Bones", 200, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Monkey Bones", 1)}),
                new Activity("Big Bones", 600, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Big Bones", 1)}),
                new Activity("Large Monkey Bones", 720, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Large Monkey Bones", 1)}),
                new Activity("Babydragon Bones", 1200, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Babydragon Bones", 1)}),
                new Activity("Wyrm Bones", 2400, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Wyrm Bones", 1)}),
                new Activity("Dragon Bones", 4000, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Large Monkey Bones", 1)}),
                new Activity("Dagannoth Bones", 8000, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Dagannoth Bones", 1)}),
                new Activity("Lava Dragon Bones", 12000, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Lava Dragon Bones", 1)}),
                new Activity("Drake Bones", 12000, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Drake Bones", 1)}),
                new Activity("Hydra Bones", 16000, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Hydra Bones", 1)}),
                new Activity("Frost Dragon Bones", 20000, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Frost Dragon Bones", 1)}),
                new Activity("Superior Dragon Bones", 24000, "Altar",  "POH, Edgeville", new Supplies[]{new Supplies("Superior Dragon Bones", 1)}),
        };

        public static Activity[] cookingActivities = new Activity[]{
                new Activity("Shrimp", 600, "Skilling Area",  "::skilling", null),
                new Activity("Trout", 1400, "Skilling Area",  "::skilling", null),
                new Activity("Salmon", 1800, "Skilling Area",  "::skilling", null),
                new Activity("Karambwan", 4000, "Skilling Area",  "::skilling", null),
                new Activity("Tuna", 2000, "Skilling Area",  "::skilling", null),
                new Activity("Lobster", 2400, "Skilling Area",  "::skilling", null),
                new Activity("Bass", 2600, "Skilling Area",  "::skilling", null),
                new Activity("Swordfish", 3000, "Skilling Area",  "::skilling", null),
                new Activity("Monkfish", 4000, "Skilling Area",  "::skilling", null),
                new Activity("Shark", 5000, "Skilling Area",  "::skilling", null),
                new Activity("Manta Ray", 6000, "Skilling Area",  "::skilling", null),
                new Activity("Dark Crab", 9400, "Skilling Area",  "::skilling", null),
                new Activity("Lava Eel", 6000, "Skilling Area",  "::skilling", null),
                new Activity("Rocktail", 7600, "Skilling Area",  "::skilling", null),
        };

        public static Activity[] fishingActivities = new Activity[]{
                new Activity("Shrimp", 300, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Karambwanji", 300, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Trout", 1575, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Salmon", 2205, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Karambwan", 7500, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Tuna", 2400, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Lobster", 2700, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Bass", 3600, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Swordfish", 3000, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Monkfish", 6000, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Shark", 7500, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Manta Ray", 9000, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Dark Crab", 14100, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Lava Eel", 11400, "POH or Skilling Area",  "POH, ::skilling", null),
                new Activity("Rocktail", 11400, "POH or Skilling Area",  "POH, ::skilling", null),
        };
        
        public static Activity[] woodcuttingActivities = new Activity[]{
                new Activity("Logs", 500, "Woodcutting teleport",  "Skilling", null),
                new Activity("Arctic Pine Logs", 500, "Fremennik Isles",  "", null),
                new Activity("Achey Logs", 500, "South of Castle Wars",  "Minigames", null),
                new Activity("Oak Logs", 1000, "Woocutting Teleport",  "Skilling", null),
                new Activity("Willow Logs", 1500, "Draynor Village",  "Amulet of Glory", null),
                new Activity("Teak Logs", 1700, "Woodcutting Teleport",  "Skilling", null),
                new Activity("Maple Logs", 1900, "Woodcutting Teleport",  "Skilling", null),
                new Activity("Mahogany Logs", 1900, "Woodcutting Teleport",  "Skilling", null),
                new Activity("Yew Logs", 5000, "Edgeville",  "Edgeville", null),
                new Activity("Magic Logs", 10000, "Skilling Area",  "::skilling", null),
        };

        public static Activity[] firemakingActivites = new Activity[]{
                new Activity("Logs", 500, "Woodcutting teleport",  "Skilling", null),
                new Activity("Arctic Pine Logs", 500, "Fremennik Isles",  "", null),
                new Activity("Achey Logs", 500, "South of Castle Wars",  "Minigames", null),
                new Activity("Oak Logs", 1000, "Woocutting Teleport",  "Skilling", null),
                new Activity("Willow Logs", 1500, "Draynor Village",  "Amulet of Glory", null),
                new Activity("Teak Logs", 1700, "Woodcutting Teleport",  "Skilling", null),
                new Activity("Maple Logs", 1900, "Woodcutting Teleport",  "Skilling", null),
                new Activity("Mahogany Logs", 1900, "Woodcutting Teleport",  "Skilling", null),
                new Activity("Yew Logs", 5000, "Edgeville",  "Edgeville", null),
                new Activity("Magic Logs", 10000, "Skilling Area",  "::skilling", null),
        };

        public static Activity[] fletchingActivites = new Activity[]{
                new Activity("Bronze Bolts", 200, "",  "", new Supplies[]{new Supplies("Bronze Bolts (Unf.)", 1), new Supplies("Feathers", 1)}),
                new Activity("Blurite Bolts", 300, "",  "", new Supplies[]{new Supplies("Blurite Bolts (Unf.)", 1), new Supplies("Feathers", 1)}),
                new Activity("Iron Bolts", 300, "",  "", new Supplies[]{new Supplies("Iron Bolts (Unf.)", 1), new Supplies("Feathers", 1)}),
                new Activity("Silver Bolts", 500, "",  "", new Supplies[]{new Supplies("Silver Bolts (Unf.)", 1), new Supplies("Feathers", 1)}),
                new Activity("Steel Bolts", 400, "",  "", new Supplies[]{new Supplies("Steel Bolts (Unf.)", 1), new Supplies("Feathers", 1)}),
                new Activity("Mithril Bolts", 700, "",  "", new Supplies[]{new Supplies("Mithril Bolts (Unf.)", 1), new Supplies("Feathers", 1)}),
                new Activity("Adamant Bolts", 900, "",  "", new Supplies[]{new Supplies("Adamant Bolts (Unf.)", 1), new Supplies("Feathers", 1)}),
                new Activity("Runite Bolts", 1200, "",  "", new Supplies[]{new Supplies("Runite Bolts (Unf.)", 1), new Supplies("Feathers", 1)}),
                new Activity("Dragon Bolts", 1500, "",  "", new Supplies[]{new Supplies("Dragon Bolts (Unf.)", 1), new Supplies("Feathers", 1)}),
                new Activity("Adding Opal tips to bolts", 120, "",  "", new Supplies[]{new Supplies("Bronze Bolts", 1), new Supplies("Enchant Opal Casts", 1)}),
                new Activity("Adding Jade tips to bolts", 300, "",  "", new Supplies[]{new Supplies("Blurite Bolts", 1), new Supplies("Enchant Jade Casts", 1)}),
                new Activity("Adding Red Topaz tips to bolts", 400, "",  "", new Supplies[]{new Supplies("Steel Bolts", 1), new Supplies("Enchant Red Topaz Casts", 1)}),
                new Activity("Adding Sapphire tips to bolts", 500, "",  "", new Supplies[]{new Supplies("Mithril Bolts", 1), new Supplies("Enchant Sapphire Casts", 1)}),
                new Activity("Adding Emerald tips to bolts", 700, "",  "", new Supplies[]{new Supplies("Mithril Bolts", 1), new Supplies("Enchant Emerald Casts", 1)}),
                new Activity("Adding Ruby tips to bolts", 900, "",  "", new Supplies[]{new Supplies("Adamant Bolts", 1), new Supplies("Enchant Ruby Casts", 1)}),
                new Activity("Adding Diamond tips to bolts", 1200, "",  "", new Supplies[]{new Supplies("Adamant Bolts", 1), new Supplies("Enchant Diamond Casts", 1)}),
                new Activity("Adding Dragonstone tips to bolts", 1400, "",  "", new Supplies[]{new Supplies("Runite Bolts", 1), new Supplies("Enchant Dragonstone Casts", 1)}),
                new Activity("Adding Onyx tips to bolts", 2500, "",  "", new Supplies[]{new Supplies("Runite Bolts", 1), new Supplies("Enchant Onyx Casts", 1)}),
                new Activity("Adding Opal tips to bolts (bolt starts unfinished)", 320, "",  "", new Supplies[]{new Supplies("Bronze Bolts (unf.) and Feathers", 1), new Supplies("Enchant Opal Casts", 1)}),
                new Activity("Adding Jade tips to bolts (bolt starts unfinished)", 600, "",  "", new Supplies[]{new Supplies("Blurite Bolts (unf.) and Feathers", 1), new Supplies("Enchant Jade Casts", 1)}),
                new Activity("Adding Red Topaz tips to bolts (bolt starts unfinished)", 800, "",  "", new Supplies[]{new Supplies("Steel Bolts (unf.) and Feathers", 1), new Supplies("Enchant Red Topaz Casts", 1)}),
                new Activity("Adding Sapphire tips to bolts (bolt starts unfinished)", 1200, "",  "", new Supplies[]{new Supplies("Mithril Bolts (unf.) and Feathers", 1), new Supplies("Enchant Sapphire Casts", 1)}),
                new Activity("Adding Emerald tips to bolts (bolt starts unfinished)", 1400, "",  "", new Supplies[]{new Supplies("Mithril Bolts (unf.) and Feathers", 1), new Supplies("Enchant Emerald Casts", 1)}),
                new Activity("Adding Ruby tips to bolts (bolt starts unfinished)", 1800, "",  "", new Supplies[]{new Supplies("Adamant Bolts (unf.) and Feathers", 1), new Supplies("Enchant Ruby Casts", 1)}),
                new Activity("Adding Diamond tips to bolts (bolt starts unfinished)", 2100, "",  "", new Supplies[]{new Supplies("Adamant Bolts (unf.) and Feathers", 1), new Supplies("Enchant Diamond Casts", 1)}),
                new Activity("Adding Dragonstone tips to bolts (bolt starts unfinished)", 2600, "",  "", new Supplies[]{new Supplies("Runite Bolts (unf.) and Feathers", 1), new Supplies("Enchant Dragonstone Casts", 1)}),
                new Activity("Adding Onyx tips to bolts (bolt starts unfinished)", 3700, "",  "", new Supplies[]{new Supplies("Runite Bolts (unf.) and Feathers", 1), new Supplies("Enchant Onyx Casts", 1)}),
                new Activity("Adding Opal tips to unfinished dragon bolts", 1620, "",  "", new Supplies[]{new Supplies("Dragon Bolts (unf.) and Feathers", 1), new Supplies("Enchant Opal Casts", 1)}),
                new Activity("Adding Jade tips to unfinished dragon bolts", 1800, "",  "", new Supplies[]{new Supplies("Dragon Bolts (unf.) and Feathers", 1), new Supplies("Enchant Jade Casts", 1)}),
                new Activity("Adding Red Topaz tips to unfinished dragon bolts", 1900, "",  "", new Supplies[]{new Supplies("Dragon Bolts (unf.) and Feathers", 1), new Supplies("Enchant Red Topaz Casts", 1)}),
                new Activity("Adding Sapphire tips to unfinished dragon bolts", 2000, "",  "", new Supplies[]{new Supplies("Dragon Bolts (unf.) and Feathers", 1), new Supplies("Enchant Sapphire Casts", 1)}),
                new Activity("Adding Emerald tips to unfinished dragon bolts", 2200, "",  "", new Supplies[]{new Supplies("Dragon Bolts (unf.) and Feathers", 1), new Supplies("Enchant Emerald Casts", 1)}),
                new Activity("Adding Ruby tips to unfinished dragon bolts", 2400, "",  "", new Supplies[]{new Supplies("Dragon Bolts (unf.) and Feathers", 1), new Supplies("Enchant Ruby Casts", 1)}),
                new Activity("Adding Diamond tips to unfinished dragon bolts", 2700, "",  "", new Supplies[]{new Supplies("Dragon Bolts (unf.) and Feathers", 1), new Supplies("Enchant Diamond Casts", 1)}),
                new Activity("Adding Dragonstone tips to unfinished dragon bolts", 2900, "",  "", new Supplies[]{new Supplies("Dragon Bolts (unf.) and Feathers", 1), new Supplies("Enchant Dragonstone Casts", 1)}),
                new Activity("Adding Onyx tips to unfinished dragon bolts", 4000, "",  "", new Supplies[]{new Supplies("Dragon Bolts (unf.) and Feathers", 1), new Supplies("Enchant Onyx Casts", 1)}),
                new Activity("Adding Feathers to Rune Dart Tips", 1200, "",  "", new Supplies[]{new Supplies("Rune Dart Tip", 1), new Supplies("Feathers", 1)}),
        };

        public static Activity[] craftingActivities = new Activity[]{
                new Activity("Mining Marble", 15000, "Crafting Guild or Skilling Area",  "Skilling or ::skilling", null),
                new Activity("Mining Runite", 12000, "Skilling Area", "::skilling", null),
        };

        public static Activity[] smithingActivities = new Activity[]{
                new Activity("Smithing rune platebodies", 50000, "Premium Skilling Zone",  "Donor & Premium", new Supplies[]{new Supplies("Runite Bar", 5)}),
                new Activity("Smithing rune dart tips or runite bolts", 10000, "Premium Skilling Zone",  "Donor & Premium", new Supplies[]{new Supplies("Runite Bar", 1)}),
                new Activity("Smelting runite bars", 10000, "Premium Skilling Zone",  "Donor & Premium", new Supplies[]{new Supplies("Runite Ore", 1), new Supplies("Coal", 4)}),
        };

        public static Activity[] miningActivities = new Activity[]{
                new Activity("Mining Tin or Copper", 300, "Skilling Area", "::skilling", null),
                new Activity("Mining Clay", 50, "Crafting Guild", "Skilling", null),
                new Activity("Mining Limestone", 100, "Crafting Guild", "Skilling", null),
                new Activity("Mining Blurite", 600, "Asgarnia Ice Dungeon", "", null),
                new Activity("Mining Iron", 900, "Skilling Area", "::skilling", null),
                new Activity("Mining Silver", 1500, "Skilling Area", "::skilling", null),
                new Activity("Mining Coal", 2000, "Skilling Area", "::skilling", null),
                new Activity("Mining Gold", 2000, "Skilling Area", "::skilling", null),
                new Activity("Mining Mithril", 3000, "Skilling Area", "::skilling", null),
                new Activity("Mining Adamantite", 6000, "Skilling Area", "::skilling", null),
                new Activity("Mining Runite", 12000, "Skilling Area", "::skilling", null),
                new Activity("Mining Marble", 15000, "Crafting Guild or Skilling Area", "Skilling or ::skilling", null),
        };

        public static Activity[] herbloreActivities = new Activity[]{
                new Activity("Super Prayer", 25000, "",  "", new Supplies[]{new Supplies("Prayer Potion", 1), new Supplies("Bonemeal", 1)}),
                new Activity("Overload", 30000, "",  "", new Supplies[]{new Supplies("Extreme Potion Set", 1), new Supplies("Torstol", 1)}),
                new Activity("Extreme Ranging", 23000, "",  "", new Supplies[]{new Supplies("Ranging Potion", 1), new Supplies("Grenwall Spikes", 1)}),
                new Activity("Super Combat", 20000, "",  "", new Supplies[]{new Supplies("Super Potion Set", 1), new Supplies("Torstol", 1)}),
        };

        public static Activity[] agilityActivities = new Activity[]{
                new Activity("Wilderness agility course", 106000, "Deep Wilderness", "PvP Teleports", null),
                new Activity("Gnome agility course", 34400, "Gnome Stronghold", "Skilling", null),
                new Activity("Werewolf agility course", 47000, "Near Canifis", "Skilling", null),
                new Activity("Black warlocks", 75000, "::Skilling or Jungle Hunter Area", "::skilling or Jungle Hunter", null),
        };

        public static Activity[] thievingActivities = new Activity[]{
                new Activity("Pickpocketing Rogues", 20000, "Bar North of Yanille", "", null),
                new Activity("Gem Stalls", 15000, "Skilling Area", "::skilling", null),
        };

        public static Activity[] farmingActivities = new Activity[]{
                new Activity("Magic Trees", 400000, "Magic Secateurs Trees Teleports", "", new Supplies[]{new Supplies("Magic Sapling", 1)}),
                new Activity("Belladonnas", 50000, "", "", new Supplies[]{new Supplies("Belladonna Seed", 1)}),
                new Activity("Guam", 2000, "", "", null),
                new Activity("Marrentill", 4000, "", "", null),
                new Activity("Tarromin", 6000, "", "", null),
                new Activity("Harralander", 8000, "", "", null),
                new Activity("Ranarr", 10000, "", "", null),
                new Activity("Toadflax", 13000, "", "", null),
                new Activity("Irit", 16000, "", "", null),
                new Activity("Avantoe", 20000, "", "", null),
                new Activity("Kwuarm", 26000, "", "", null),
                new Activity("Snapdragon", 32000, "", "", null),
                new Activity("Cadantine", 40000, "", "", null),
                new Activity("Lantadyme", 45000, "", "", null),
                new Activity("Dwarf Weed", 50000, "", "", null),
                new Activity("Torstol", 60000, "", "", null),
        };

        public static Activity[] runecraftingActivities = new Activity[]{
                new Activity("Death Runes", 3000, "Death Altar", "Abyss", new Supplies[]{new Supplies("Pure Essence", 1)}),
                new Activity("Soul Runes", 2150, "Death Altar", "Abyss", new Supplies[]{new Supplies("Pure Essence", 1)}),
        };

        public static Activity[] constructionActivities = new Activity[]{
                new Activity("Oak Larder", 2000, "POH Kitchen", "POH", new Supplies[]{new Supplies("Oak Plank", 8)}),
                new Activity("Mahogany Bench", 4000, "POH Dining Hall", "POH", new Supplies[]{new Supplies("Mahogany planks", 8)}),
                new Activity("Gilded Bench (Dining Hall)", 12000, "POH Dining Hall", "POH", new Supplies[]{new Supplies("Mahogany planks", 8), new Supplies("Gold leaf", 8)}),
                new Activity("Gilded Bench (Throne Room)", 18000, "POH Throne Room", "POH", new Supplies[]{new Supplies("Mahogany planks", 12), new Supplies("Gold leaf", 12)}),
        };

        public static Activity[] hunterActivities = new Activity[]{
                new Activity("Red Chinchompas", 4800, "Jungle Hunter Area", "Jungle Hunter", null),
                new Activity("Black Chinchompas", 18000, "Level 32 Wilderness", "PvP Teleports", null),
                new Activity("Kingly Imps", 12600, "Skilling Area", "::skilling", null),
                new Activity("Black Warlocks", 12000, "Skilling Area", "::skilling", null),
                new Activity("Dragon Imps", 2880, "Puro Puro", "Hunter Teleports", null),
        };

        public static Skill[] skills = new Skill[]{
                new Skill("Attack", combatActivities),
                new Skill("Defence", combatActivities),
                new Skill("Strength", combatActivities),
                new Skill("Hitpoints", hitpointsActivities),
                new Skill("Ranged", combatActivities),
                new Skill("Prayer", prayerActivities),
                new Skill("Magic", combatActivities),
                new Skill("Cooking", cookingActivities),
                new Skill("Woodcutting", woodcuttingActivities),
                new Skill("Fletching", fletchingActivites),
                new Skill("Fishing", fishingActivities),
                new Skill("Firemaking", firemakingActivites),
                new Skill("Crafting", craftingActivities),
                new Skill("Smithing", smithingActivities),
                new Skill("Mining", miningActivities),
                new Skill("Herblore", herbloreActivities),
                new Skill("Agility", agilityActivities),
                new Skill("Thieving", thievingActivities),
                new Skill("Farming", farmingActivities),
                new Skill("Runecrafting", runecraftingActivities),
                new Skill("Construction", constructionActivities),
                new Skill("Hunter", hunterActivities)
        };
}
