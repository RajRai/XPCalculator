package core;

public class Activity {

    private final String description;
    private final int exp;
    private final String location;
    private final String teleport;
    private final Supplies[] supplies;

    /**
     * @param supplies a double array where each interior array is a (String, Integer) pair stored in a length-2 array.
     *                 supplies.length should give the number of different types of supplies used.
     *                 supplies[0].length should always be 2.
     */
    public Activity(String description, int exp, String location, String teleport, Supplies[] supplies){
        this.description = description;
        this.exp = exp;
        this.location = location;
        this.teleport = teleport;
        this.supplies = supplies;
    }

    public int getActionsForXp(int xp, double boost){
        return (int) Math.ceil((double)xp / (this.exp * boost));
    }

    public Supplies[] suppliesForXp(int xp, double boost){
        if (supplies == null) return new Supplies[0];
        Supplies[] results = new Supplies[supplies.length];
        for (int i = 0; i < supplies.length; i++) {
            results[i] = new Supplies(supplies[i].name, supplies[i].n * getActionsForXp(xp, boost));
        }
        return results;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getTeleport() {
        return teleport;
    }

    public Supplies[] getSupplies() {
        return supplies;
    }
}
