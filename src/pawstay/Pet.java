package pawstay;


//Register a pet with fewer details (name only) or with full details.

public class Pet {

    private static final String UNKNOWN = "Unknown";

    private final String name;
    private final String breed;
    private double weightKg;     
    private final Owner owner;   

    
    public Pet(String name) {
        this(name, UNKNOWN);
    }

    // 2) Name and breed
    public Pet(String name, String breed) {
        this(name, breed, 0.0, null);      
    }

    public Pet(String name, String breed, double weightKg, Owner owner) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Pet name cannot be blank");
        }
        this.name = name.strip();            
        this.breed = breed;
        this.owner = owner;
        setWeightKg(weightKg);            
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public double getWeightKg() {
        return weightKg;
    }

    
    public void setWeightKg(double weightKg) {
        if (weightKg < 0) {
            throw new IllegalArgumentException("Weight cannot be negative: " + weightKg);
        }
        this.weightKg = weightKg;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean hasFullDetails() {
        return !UNKNOWN.equals(breed) && weightKg > 0 && owner != null;
    }

    @Override
    public String toString() {
        return "Pet[name=" + name + ", breed=" + breed + ", weightKg=" + weightKg
                + ", owner=" + (owner == null ? "not yet registered" : owner.getName()) + "]";
    }
}
