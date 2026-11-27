package pawstay;

// final: a sealed class's subclasses must be final, sealed or non-sealed
public final class Cat extends Pet {

    private final boolean indoorOnly;

    public Cat(String name) {
        super(name);
        this.indoorOnly = true;
    }

    public Cat(String name, String breed, double weightKg, Owner owner, boolean indoorOnly) {
        super(name, breed, weightKg, owner);
        this.indoorOnly = indoorOnly;
    }

    public boolean isIndoorOnly() {
        return indoorOnly;
    }

    @Override
    public String describe() {
        return "Cat: " + super.describe() + (indoorOnly ? ", indoor only" : ", allowed outside");
    }

    @Override
    public String careNeeds() {
        return "Litter tray cleaned twice daily, scratching post"
                + (indoorOnly ? "" : ", supervised time in the garden run");
    }
}
