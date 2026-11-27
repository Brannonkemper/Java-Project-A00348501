package pawstay;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== PawStay Kennel Management ===\n");
        Owner owner = us1RegisterOwner();
        us2RegisterPets(owner);
        us3PetTypes(owner);
        us4Vaccinations(owner);
    }

    
    // US1: Register an owner (immutable type + defensive copying)
   
    private static Owner us1RegisterOwner() {
        System.out.println("--- US1: Register an owner ---");

        List<String> contacts = new ArrayList<>();
        contacts.add("Mary Murphy 087 111 2222");
        contacts.add("John Murphy 086 333 4444");

        Owner owner = new Owner("Sean Murphy", "087 123 4567", contacts);
        System.out.println("Created:  " + owner);

   
        contacts.add("STRANGER 000 000 0000");
        System.out.println("After changing the ORIGINAL list:  " + owner.getEmergencyContacts());

        
        List<String> fromGetter = owner.getEmergencyContacts();
        fromGetter.clear();
        System.out.println("After clearing the RETURNED list:  " + owner.getEmergencyContacts());

       
        try {
            new Owner("", "087 123 4567", List.of());
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected bad owner: " + e.getMessage());
        }

        // An owner with no emergency contacts used to crash with a NullPointerException
        Owner noContacts = new Owner("Aoife Kelly", "085 765 4321", null);
        System.out.println("Owner with no contacts:  " + noContacts);

        System.out.println();
        return owner;
    }

    
    // For register pets with fewer or full details

   
    private static void us2RegisterPets(Owner owner) {
        System.out.println("--- US2: Register pets ---");

        // Fix: Pet is abstract now, so we register Dogs instead of plain Pets.
        // The overloaded constructors still chain through Pet's this(...) calls.
        Pet quick = new Dog("Biscuit");                                   // name only
        Pet partial = new Dog("Luna", "Border Collie");                   // name + breed
        Pet full = new Dog("Rex", "German Shepherd", 34.5, owner, 3);     // full details

        System.out.println("Name only:     " + quick + "  complete? " + quick.hasFullDetails());
        System.out.println("Name + breed:  " + partial + "  complete? " + partial.hasFullDetails());
        System.out.println("Full details:  " + full + "  complete? " + full.hasFullDetails());

        // Encapsulation weight can only change through the validated setter.
        full.setWeightKg(35.2);
        System.out.println("Rex weighed again: " + full.getWeightKg() + " kg");
        try {
            full.setWeightKg(-3);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected bad weight: " + e.getMessage());
        }

        System.out.println();
    }

    // US3: Dog, Cat and Bird each have their own care needs
    // (inheritance, super() vs super., overriding, polymorphism)
    private static void us3PetTypes(Owner owner) {
        System.out.println("--- US3: Dogs, cats and birds ---");

        // POLYMORPHISM: the list's type is Pet, but each object is a Dog, Cat or Bird.
        // At runtime Java calls the OVERRIDDEN version of describe() and careNeeds().
        List<Pet> guests = new ArrayList<>();
        guests.add(new Dog("Rex", "German Shepherd", 34.5, owner, 3));
        guests.add(new Dog("Biscuit"));
        guests.add(new Cat("Misty", "Persian", 4.2, owner, true));
        guests.add(new Bird("Kiwi", "African Grey", 0.4, owner, true));

        for (Pet pet : guests) {
            System.out.println(pet.describe());
            System.out.println("   Care: " + pet.careNeeds());
        }

        try {
            new Dog("Lazy", "Basset Hound", 25, owner, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected dog: " + e.getMessage());
        }

        System.out.println();
    }

    // US4: Record a pet's vaccinations in one call (varargs)
    private static void us4Vaccinations(Owner owner) {
        System.out.println("--- US4: Record vaccinations ---");

        Dog rex = new Dog("Rex", "German Shepherd", 34.5, owner, 3);

        rex.addVaccinations();                                        // zero arguments is allowed
        rex.addVaccinations("Rabies");                                // one argument
        rex.addVaccinations("Distemper", "Parvovirus", "Kennel Cough"); // many arguments
        System.out.println("Rex's vaccinations: " + rex.getVaccinations());

        // You can also pass an array, because varargs IS an array underneath
        String[] catVaccines = {"Cat Flu", "Feline Leukaemia"};
        Cat misty = new Cat("Misty", "Persian", 4.2, owner, true);
        misty.addVaccinations(catVaccines);
        System.out.println("Misty's vaccinations: " + misty.getVaccinations());

        System.out.println("Is Rex protected against Kennel Cough? " + rex.isVaccinatedAgainst("Kennel Cough"));
        System.out.println("Is Misty protected against Rabies? " + misty.isVaccinatedAgainst("Rabies"));

        try {
            rex.addVaccinations("Leptospirosis", " ");
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected vaccine: " + e.getMessage());
        }

        System.out.println();
    }
}
