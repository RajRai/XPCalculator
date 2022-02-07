package core;

import common.definitions.Skill;
import common.definitions.Skills;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PostFormatter {

    public HashMap<String, ArrayList<String>> maxedPlayers;

    public PostFormatter(){
        maxedPlayers = new HashMap<>();
        for (String skill : Skills.skillNames){
            maxedPlayers.put(skill, new ArrayList<>());
        }
    }

    public String getFormattedPost(){
        String header = String.format("[center][b][size=150][center][color=#000000][u]Clan total 2b Exp in each stat.[/u][/color][/center][/size][/b]\n" +
                "[center][b][size=150]THIS IS UP TO DATE AS OF %s[/size][/b][/center]\n" +
                "\n" +
                "\n", new SimpleDateFormat("MM/dd/yyyy").format(new Date()));

        String out = "";

        for (int i = 0; i < Skills.skills.length; i++){
            Skill skill = Skills.skills[i];
            out += String.format("[b][center][color=#%s][u]%s-(%d)[/u][/color][/center][/b]\n" +
                    "[color=#808080][spoiler]",
                    skill.getHexColor(), skill.getName(), maxedPlayers.get(skill.getName()).size());
            for (String name : maxedPlayers.get(skill.getName())){
                out += "\n" + name;
            }
            out += "\n[/spoiler][/color]";
        }

        return header + out;
    }

}
