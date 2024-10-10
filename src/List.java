/**
 * List class - This class represents a growable array-based implementation of a list to manage appointments.
 * The array expands its capacity dynamically as appointments are added.
 *
 * @ Rithi and Shaili
 */
public class List {

    private Appointment[] appointments;  // Array to store Appointment objects
    private int size;                    // Number of appointments currently stored in the list

    // Constants for the initial capacity of the array and the value for 'not found'
    private static final int INITIAL_CAPACITY = 4;
    private static final int NOT_FOUND = -1;

    /**
     * Constructor for the List class.
     * Initializes the list with a small initial capacity and sets the size to zero.
     */
    public List() {
        appointments = new Appointment[INITIAL_CAPACITY];  // Allocate memory for initial capacity
        size = 0;  // No appointments initially
    }

    /**
     * Method to get the array of appointments.
     *
     * @return the array of appointments currently in the list.
     */
    public Appointment[] getAppointments() {
        return appointments;
    }

    /**
     * Helper method to find the index of an appointment in the list.
     *
     * @param appointment the appointment to search for.
     * @return the index of the appointment if found, otherwise NOT_FOUND (-1).
     */
    private int find(Appointment appointment) {
        for (int i = 0; i < size; i++) {
            if (appointments[i].equals(appointment)) {
                return i;  // Return the index if the appointment is found
            }
        }
        return NOT_FOUND;  // Return -1 if the appointment is not found
    }

    /**
     * Adds a new appointment to the list. Expands the array if necessary.
     *
     * @param appointment the appointment to be added.
     */
    public void add(Appointment appointment) {
        if (size == appointments.length) {
            grow();  // If the array is full, increase its capacity
        }
        appointments[size++] = appointment;  // Add the new appointment and increment size
    }

    /**
     * Removes an existing appointment from the list.
     *
     * @param appointment the appointment to be removed.
     */
    public void remove(Appointment appointment) {
        int index = find(appointment);  // Find the index of the appointment to be removed
        if (index != NOT_FOUND) {
            // Shift elements to the left to overwrite the removed appointment
            for (int i = index; i < size - 1; i++) {
                appointments[i] = appointments[i + 1];
            }
            appointments[--size] = null;  // Null out the last element and decrement size
        }
    }

    /**
     * Helper method to increase the capacity of the array by INITIAL_CAPACITY.
     */
    private void grow() {
        // Create a new array with increased capacity
        Appointment[] newAppointments = new Appointment[appointments.length + INITIAL_CAPACITY];

        // Manually copy the existing appointments into the new array
        for (int i = 0; i < appointments.length; i++) {
            newAppointments[i] = appointments[i];  // Copy each element one by one
        }

        // Replace the old array with the new array
        appointments = newAppointments;
    }

    /**
     * Prints the list of appointments sorted by patient profile, then by date/timeslot.
     */
    public void printByPatient() {
        sortByPatient();  // Sort the appointments
        System.out.println("** Appointments ordered by patient/date/time **");
        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
        System.out.println("** end of list **");
    }

    /**
     * Prints the list of appointments sorted by location, then by date/timeslot.
     */
    public void printByLocation() {
        sortByLocation();  // Sort the appointments
        System.out.println("** Appointments ordered by county/date/time **");
        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
        System.out.println("** end of list **");
    }

    /**
     * Prints the list of appointments sorted by date/timeslot, then by provider.
     */
    public void printByAppointment() {
        sortByDateAndTimeslot();  // Sort the appointments
        System.out.println("** Appointments ordered by date/time/provider **");
        for (int i = 0; i < size; i++) {
            System.out.println(appointments[i]);
        }
        System.out.println("** end of list **");
    }

    /**
     * Sorts the appointments by patient profile, then by date and timeslot.
     * Uses a bubble sort for simplicity.
     */
    public void sortByPatient() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (appointments[j].getPatient().compareTo(appointments[j + 1].getPatient()) > 0) {
                    swap(j, j + 1);  // Swap if they are in the wrong order
                }
            }
        }
    }

    /**
     * Sorts the appointments by location, then by date and timeslot.
     * Uses a bubble sort for simplicity.
     */
    private void sortByLocation() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                int countyComparison = appointments[j].getProvider().getLocation().getCounty().compareTo(appointments[j + 1].getProvider().getLocation().getCounty());
                if (countyComparison > 0) {
                    swap(j, j + 1);
                } else if (countyComparison == 0) {
                    int dateComparison = appointments[j].getDate().compareTo(appointments[j + 1].getDate());
                    if (dateComparison > 0) {
                        swap(j, j + 1);
                    } else if (dateComparison == 0) {
                        int timeComparison = appointments[j].getTimeslot().compareTo(appointments[j + 1].getTimeslot());
                        if (timeComparison > 0) {
                            swap(j, j + 1);
                        }
                    }
                }
            }
        }
    }

    /**
     * Sorts the appointments by date and timeslot, then by provider.
     * Uses a bubble sort for simplicity.
     */
    private void sortByDateAndTimeslot() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                int dateComparison = appointments[j].getDate().compareTo(appointments[j + 1].getDate());
                if (dateComparison > 0) {
                    swap(j, j + 1);
                } else if (dateComparison == 0) {
                    int timeComparison = appointments[j].getTimeslot().compareTo(appointments[j + 1].getTimeslot());
                    if (timeComparison > 0) {
                        swap(j, j + 1);
                    } else if (timeComparison == 0) {
                        if (appointments[j].getProvider().getName().compareTo(appointments[j + 1].getProvider().getName()) > 0) {
                            swap(j, j + 1);
                        }
                    }
                }
            }
        }
    }

    /**
     * Swaps two appointments in the array.
     *
     * @param i the index of the first appointment.
     * @param j the index of the second appointment.
     */
    private void swap(int i, int j) {
        Appointment temp = appointments[i];
        appointments[i] = appointments[j];
        appointments[j] = temp;
    }

    /**
     * Checks if the list contains a specific appointment.
     *
     * @param newAppointment the appointment to check.
     * @return true if the appointment exists in the list, false otherwise.
     */
    public boolean contains(Appointment newAppointment) {
        return find(newAppointment) != NOT_FOUND;
    }

    /**
     * Returns the number of appointments currently in the list.
     *
     * @return the size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Retrieves the appointment at a specific index.
     *
     * @param i the index of the appointment.
     * @return the appointment at the given index.
     */
    public Appointment get(int i) {
        return appointments[i];
    }
}
