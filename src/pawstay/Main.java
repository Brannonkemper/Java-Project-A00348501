package pawstay;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== PawStay Kennel Management ===\n");
        Owner owner = us1RegisterOwner();
        us2RegisterPets(owner);
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

        System.out.println();
        return owner;
    }

    
    // For register pets with fewer or full details

   
    private static void us2RegisterPets(Owner owner) {
        System.out.println("--- US2: Register pets ---");

        Pet quick = new Pet("Biscuit");                               //name only
        Pet partial = new Pet("Luna", "Border Collie");                 // name + breed
        Pet full = new Pet("Rex", "German Shepherd", 34.5, owner);      // full details

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
}
