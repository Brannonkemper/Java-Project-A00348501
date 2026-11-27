package pawstay;

// final: a sealed class's subclasses must be final, sealed or non-sealed
public final class Dog extends Pet {

    private final int walksPerDay;

    // super(...) calls the PARENT's constructor. It must be the first statement.
    public Dog(String name) {
        super(name);                       // runs Pet(String name)
        this.walksPerDay = 2;
    }

    public Dog(String name, String breed, double weightKg, Owner owner, int walksPerDay) {
        super(name, breed, weightKg, owner);   // runs Pet's full constructor
        if (walksPerDay < 1) {
            throw new IllegalArgumentException("A dog needs at least 1 walk per day");
        }
        this.walksPerDay = walksPerDay;
    }

    public int getWalksPerDay() {
        return walksPerDay;
    }

    // super.describe() calls the PARENT's version of this method, then we add to it
    @Override
    public String describe() {
        return "Dog: " + super.describe() + ", walks per day: " + walksPerDay;
    }

    // Overriding: completely replaces Pet's careNeeds()
    @Override
    public String careNeeds() {
        return walksPerDay + " walks a day, chew toy, bowl of water";
    }
}
