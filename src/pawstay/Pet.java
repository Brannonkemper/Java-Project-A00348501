package pawstay;

import java.util.ArrayList;
import java.util.List;


//Register a pet with fewer details (name only) or with full details.
// SEALED: only Dog, Cat and Bird may extend Pet - no other class can.
// ABSTRACT: you can't create a plain "Pet", it must be a specific kind of pet.

public sealed abstract class Pet permits Dog, Cat, Bird {

    private static final String UNKNOWN = "Unknown";

    private final String name;
    private final String breed;
    private double weightKg;     
    private final Owner owner;
    private final List<String> vaccinations = new ArrayList<>();   

    
    protected Pet(String name) {
        this(name, UNKNOWN);
    }

    // 2) Name and breed
    protected Pet(String name, String breed) {
        this(name, breed, 0.0, null);      
    }

    protected Pet(String name, String breed, double weightKg, Owner owner) {
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

    // US4 - VARARGS: String... lets the caller pass 0, 1 or many vaccines in one call.
    // Inside the method, vaccines is just a String[] array.
    // Rules: only one varargs parameter per method, and it must be the LAST parameter.
    public void addVaccinations(String... vaccines) {
        for (String vaccine : vaccines) {
            if (vaccine == null || vaccine.isBlank()) {
                throw new IllegalArgumentException("Vaccine name cannot be blank");
            }
            vaccinations.add(vaccine.strip());
        }
    }

    public boolean isVaccinatedAgainst(String vaccine) {
        return vaccinations.contains(vaccine);
    }

    // Defensive copy, same idea as Owner.getEmergencyContacts()
    public List<String> getVaccinations() {
        return new ArrayList<>(vaccinations);
    }

    // Basic description shared by every pet. Subclasses EXTEND this using super.describe()
    public String describe() {
        return name + " (" + breed + ", " + weightKg + " kg)";
    }

    // Abstract: every kind of pet MUST provide its own care needs.
    public abstract String careNeeds();

    public boolean hasFullDetails() {
        return !UNKNOWN.equals(breed) && weightKg > 0 && owner != null;
    }

    @Override
    public String toString() {
        return "Pet[name=" + name + ", breed=" + breed + ", weightKg=" + weightKg
                + ", owner=" + (owner == null ? "not yet registered" : owner.getName()) + "]";
    }
}
