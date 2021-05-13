package core;

public class Skill {

    private final String name;
    private final Activity[] activities;
    private int currExp = 0;
    private int goalExp = 2000000000; // Max XP, probably going to be used most frequently

    public Skill(String name, Activity[] activities){
        this.name = name;
        this.activities = activities;
    }

    public String getName() {
        return name;
    }

    public Activity[] getActivites() {
        return activities;
    }

    public int getCurrExp() {
        return currExp;
    }

    public void setCurrExp(int currExp) {
        this.currExp = currExp;
    }

    public int getGoalExp() {
        return goalExp;
    }

    public void setGoalExp(int goalExp) {
        this.goalExp = goalExp;
    }

    public int getDiff(){
        return this.goalExp - this.currExp;
    }

    public static int experienceAtLevel(int level){
        double l = (double)level;
        // https://oldschool.runescape.wiki/w/Experience#Formula
        return (int)((l*l - l + 600*((Math.pow(2, l/7)-Math.pow(2, 1.0/7))/(Math.pow(2, 1.0/7) - 1)))/8);
    }

    public static int levelAtExperience(int xp){
        for (int i = 1; i < 100; i++){
            if (experienceAtLevel(i) > xp){
                return i-1;
            }
        }
        return 99;
    }

    public String toString(){
        return name;
    }
}
