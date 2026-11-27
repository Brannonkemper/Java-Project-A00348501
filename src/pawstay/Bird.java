package pawstay;

// final: a sealed class's subclasses must be final, sealed or non-sealed
public final class Bird extends Pet {

    private final boolean canTalk;

    public Bird(String name) {
        super(name);
        this.canTalk = false;
    }

    public Bird(String name, String breed, double weightKg, Owner owner, boolean canTalk) {
        super(name, breed, weightKg, owner);
        this.canTalk = canTalk;
    }

    public boolean canTalk() {
        return canTalk;
    }

    @Override
    public String describe() {
        return "Bird: " + super.describe() + (canTalk ? ", talks!" : "");
    }

    @Override
    public String careNeeds() {
        return "Cover cage at night, fresh seed daily"
                + (canTalk ? ", talk to it so it doesn't get lonely" : "");
    }
}
