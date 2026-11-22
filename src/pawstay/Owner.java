package pawstay;

import java.util.ArrayList;
import java.util.List;

//Register an owner.
 
public final class Owner {

    private final String name;
    private final String phone;
    private final List<String> emergencyContacts;   // List is mutable, so it needs protecting

    public Owner(String name, String phone, List<String> emergencyContacts) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Owner name cannot be blank");
        }
        if (phone == null || !phone.matches("[0-9 +()-]{7,15}")) {
            throw new IllegalArgumentException("Invalid phone number: " + phone);
        }
        this.name = name.strip();
        this.phone = phone;

        // Fix: new ArrayList<>(null) threw a NullPointerException,
        // so an owner with no emergency contacts now gets an empty list.
        this.emergencyContacts = (emergencyContacts == null)
                ? new ArrayList<>()
                : new ArrayList<>(emergencyContacts);
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

  
    public List<String> getEmergencyContacts() {
        return new ArrayList<>(emergencyContacts);
    }

    @Override
    public String toString() {
        return "Owner[name=" + name + ", phone=" + phone
                + ", emergencyContacts=" + emergencyContacts + "]";
    }
}
