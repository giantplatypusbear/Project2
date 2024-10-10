
import java.util.Scanner;

public class Scheduler {
    private MedicalRecord medicalRecord;
    private List appointmentList;


    // Constructor
    public Scheduler() {
        this.medicalRecord = new MedicalRecord();
        this.appointmentList = new List();

    }

    // Entry point for running the scheduler and handling commands
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scheduler is running.");

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;  // Ignore empty input
            }

            if (input.equals("Q")) {
                System.out.println("Scheduler has been terminated.");
                break;  // Quit the loop and stop execution
            }

            handleCommand(input);  // Process user commands
        }

        scanner.close();
    }

    // Main handler for routing the commands
    private void handleCommand(String input) {
        String[] tokens = input.split(",");
        if (tokens.length == 0) {
            System.out.println("Invalid command.");
            return;
        }
        String command = tokens[0].trim();
        switch (command) {
            case "S":
                if (tokens.length < 7) {
                    System.out.println("Invalid command.");
                    return;
                }
                scheduleAppointment(tokens);
                break;
            case "C":
                cancelAppointment(tokens);
                break;
            case "R":
                rescheduleAppointment(tokens);
                break;
            case "PA":
                printByAppointment();
                break;
            case "PP":
                printByPatient();
                break;
            case "PL":
                printByLocation();
                break;
            case "PS":
                printBillingStatements();
                break;
            default:
                System.out.println("Invalid command!");
        }
    }

    // Method to process the scheduling of an appointment ("S" command)
    private void scheduleAppointment(String[] sInput) {
        String dateInput = sInput[1].trim();
        String timeslotInput = sInput[2].trim();
        String firstName = sInput[3].trim();
        String lastName = sInput[4].trim();
        String dobInput = sInput[5].trim();
        String providerLastName = sInput[6].trim();

        Date appointmentDate = parseDate(dateInput);
        boolean validAppDate = appointmentDate.isValid();
        Date dob = parseDate(dobInput);
        boolean validDob = dob.isValid();
        Timeslot timeslot = parseTimeslot(timeslotInput);
        if (timeslot == null) {
            System.out.println(timeslotInput +" is not a valid time slot.");
            return;
        }

        if (!validAppDate || !validDob) {
            if (!validAppDate) System.out.println("Appointment date: "+ dateInput+" is not a valid calendar date.");
            if (!validDob) System.out.println("Patient dob: " +dobInput+" is not a valid calendar date.");
            return;
        }
        if(dob.isAfterToday()){
            System.out.println("Patient dob: " +dobInput+" is a date after today.");
            return;
        }
        if(appointmentDate.isBeforeToday()){
            System.out.println("Appointment date: "+ dateInput+" is today or a date before today.");
            return;
        }
        if(!appointmentDate.isWithinSixMonths(appointmentDate)){
            System.out.println("Appointment date: "+ dateInput+" is not within six months.");
            return;
        }
        if(appointmentDate.isWeekend(appointmentDate)){
            System.out.println("Appointment date: "+ dateInput+" is Saturday or Sunday.");
            return;
        }
        Profile patientProfile = new Profile(firstName, lastName, dob);
        Patient patient = getOrCreatePatient(patientProfile);
        Provider provider = findProvider(providerLastName);
        if (provider == null) {
            System.out.println(providerLastName+ " - provider doesn't exist.");
            return;
        }
        if (!isProviderFreeForTimeslot(provider, timeslot)) {
            System.out.println(provider.toString() + " is not available at slot "+ timeslotInput+ ".");
            return;
        }
        Appointment newAppointment = new Appointment(appointmentDate, timeslot, patientProfile, provider);
        if (appointmentList.contains(newAppointment)) {
            System.out.println(patientProfile.toString()+ " has an existing appointment at the same time slot.");
            return;
        }
        appointmentList.add(newAppointment);

        System.out.println( new Appointment(appointmentDate, timeslot, patientProfile, provider).toString()+ " booked.");
    }

    // Validate and parse a date string in MM/DD/YYYY format
    private Date parseDate(String dateString) {
        String[] parts = dateString.split("/");

        int month = Integer.parseInt(parts[0].trim());
        int day = Integer.parseInt(parts[1].trim());
        int year = Integer.parseInt(parts[2].trim());

        return new Date(year, month, day);
    }


    // Parse timeslot from string to enum
    private Timeslot parseTimeslot(String timeslotInput) {
        try {
            Integer.parseInt(timeslotInput);
        } catch (NumberFormatException e) {
            return null;
        }

        switch (Integer.parseInt(timeslotInput)) {
            case 1: return Timeslot.SLOT1;
            case 2: return Timeslot.SLOT2;
            case 3: return Timeslot.SLOT3;
            case 4: return Timeslot.SLOT4;
            case 5: return Timeslot.SLOT5;
            case 6: return Timeslot.SLOT6;
            default: return null;
        }

    }

    // Retrieve a provider by their last name
    private Provider findProvider(String providerLastName) {
        for (Provider provider : Provider.values()) {
            if (provider.getName().equalsIgnoreCase(providerLastName)) {
                return provider;
            }
        }
        return null;  // Provider not found
    }

    // Get an existing patient or create a new one if they don't exist
    private Patient getOrCreatePatient(Profile profile) {
        Patient existingPatient = medicalRecord.findPatient(profile);
        if (existingPatient != null) {
            return existingPatient;
        }

        // Create and add a new patient
        Patient newPatient = new Patient(profile);
        medicalRecord.add(newPatient);
        return newPatient;
    }

    // Check if the provider is available for a given timeslot
    private boolean isProviderFreeForTimeslot(Provider provider, Timeslot timeslot) {
        // Add specific logic here if necessary; assuming providers are always free
        return true;
    }

    // Method to process the 'C' command to cancel an appointment
    private void cancelAppointment(String[] tokens) {

        // Extract and parse the data tokens
        String dateInput = tokens[1].trim();   // Appointment date
        String timeslotInput = tokens[2].trim();  // Timeslot
        String firstName = tokens[3].trim();   // Patient's first name
        String lastName = tokens[4].trim();    // Patient's last name
        String dobInput = tokens[5].trim();    // Patient's date of birth
        String providerLastName = tokens[6].trim();  // Provider's last name

        // Step 1: Parse the appointment date and patient's date of birth
        Date appointmentDate = parseDate(dateInput);
        Date dob = parseDate(dobInput);

        // Step 2: Parse the timeslot (index from 1 to 6)
        Timeslot timeslot = parseTimeslot(timeslotInput);

        // Step 4: Create the patient's profile using the provided details
        Profile patientProfile = new Profile(firstName, lastName, dob);
        Provider provider= findProvider(providerLastName);

        // Step 5: Search for the appointment in the appointment list
        Appointment appointmentToCancel = new Appointment(appointmentDate, timeslot, patientProfile, provider);
        boolean listContains= appointmentList.contains(appointmentToCancel);

        String forTime= Appointment.forTimeslot(timeslot);

        if (listContains) {
            appointmentList.remove(appointmentToCancel);  // Remove the appointment
            System.out.printf("%s %s %s %s has been canceled.%n",
                    formatDate(appointmentDate), forTime, firstName + " " + lastName, formatDate(dob));
        } else {
            System.out.printf("%s %s %s %s does not exist.%n",
                    formatDate(appointmentDate), forTime, firstName + " " + lastName, formatDate(dob));
        }
    }

    // Helper method to format a Date object as MM/DD/YYYY
    private String formatDate(Date date) {
        return String.format("%d/%d/%d", date.getMonth(), date.getDay(), date.getYear());
    }
    public void rescheduleAppointment(String[] sInput) {
        String dateInput = sInput[1].trim();
        String originalTimeslotInput = sInput[2].trim();
        String firstName = sInput[3].trim();
        String lastName = sInput[4].trim();
        String dobInput = sInput[5].trim();
        String newTimeslotInput = sInput[6].trim();

        Date appointmentDate = parseDate(dateInput);
        Timeslot originalTimeslot = parseTimeslot(originalTimeslotInput);
        Timeslot newTimeslot = parseTimeslot(newTimeslotInput);
        Date dob = parseDate(dobInput);

        Profile patientProfile = new Profile(firstName, lastName, dob);
        Appointment existingAppointment = findAppointment(appointmentDate, originalTimeslot, patientProfile);

        if (existingAppointment == null) {
            System.out.printf("%s %s %s %s does not exist.%n", dateInput, Appointment.forTimeslot(originalTimeslot), firstName + " " + lastName, dobInput);
            return;
        }

        if (newTimeslot == null) {
            System.out.println(newTimeslotInput + " is not a valid time slot.");
            return;
        }

        Provider provider = existingAppointment.getProvider();

        if (!isProviderFreeForTimeslot(provider, newTimeslot)) {
            System.out.printf("[%s, %s, %s %s, %s] is not available at slot %s.%n",
                    provider.getName(),
                    provider.getLocation().getCity(),
                    provider.getLocation().getCounty(),
                    provider.getLocation().getZip(),
                    provider.getSpecialty(),
                    Appointment.forTimeslot(newTimeslot));
            return;
        }

        // Remove the old appointment and add the new one
        appointmentList.remove(existingAppointment);
        Appointment rescheduledAppointment = new Appointment(appointmentDate, newTimeslot, patientProfile, provider);
        appointmentList.add(rescheduledAppointment);

        System.out.printf("Rescheduled to %s %s %s [%s, %s, %s %s, %s]%n",
                dateInput,
                Appointment.forTimeslot(newTimeslot),
                firstName + " " + lastName,
                provider.getName(),
                provider.getLocation().getCity(),
                provider.getLocation().getCounty(),
                provider.getLocation().getZip(),
                provider.getSpecialty());
    }

    private Appointment findAppointment(Date date, Timeslot timeslot, Profile patientProfile) {
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment appointment = appointmentList.get(i);
            if (appointment.getDate().equals(date) &&
                    appointment.getTimeslot().equals(timeslot) &&
                    appointment.getPatient().equals(patientProfile)) {
                return appointment;
            }
        }
        return null;
    }
    // Print appointments by date/time/provider
    public void printByAppointment() {
        if (appointmentList.size() == 0) {
            System.out.println("The schedule calendar is empty.");
            return;
        }
        appointmentList.printByAppointment();

    }

    // Print appointments by patient/date/time
    public void printByPatient() {
        if (appointmentList.size() == 0) {
            System.out.println("The schedule calendar is empty.");
            return;
        }
        appointmentList.printByPatient();

    }

    // Print appointments by location/date/time
    public void printByLocation() {
        if (appointmentList.size() == 0) {
            System.out.println("The schedule calendar is empty.");
            return;
        }
        appointmentList.printByLocation();

    }

    private void printBillingStatements() {
        appointmentList.sortByPatient();
        Appointment[] allAppointments = appointmentList.getAppointments();

        BillingStatement[] billingStatements = new BillingStatement[allAppointments.length];
        int billingCount = 0;

        // Calculate the total amount due for each patient
        for (Appointment appointment : allAppointments) {
            Profile patientProfile = appointment.getPatient();
            Specialty specialty = appointment.getProvider().getSpecialty();
            double rate = specialty.getCharge();

            // Check if the patient already has a billing statement
            boolean found = false;
            for (int i = 0; i < billingCount; i++) {
                if (billingStatements[i].getPatientProfile().equals(patientProfile)) {
                    billingStatements[i].addAmount(rate);
                    found = true;
                    break;
                }
            }

            // If not found, create a new billing statement
            if (!found) {
                billingStatements[billingCount++] = new BillingStatement(patientProfile, rate);
            }
        }
        System.out.println("** Billing statement ordered by patient **");
        for (int i = 0; i < billingCount; i++) {
            BillingStatement bs = billingStatements[i];
            System.out.printf("(%d) %s %s %s [amount due: $%,.2f]%n",
                    i + 1,
                    bs.getPatientProfile().getFirstName(),
                    bs.getPatientProfile().getLastName(),
                    bs.getPatientProfile().getDateOfBirth(),
                    bs.getTotalAmount());
        }
        System.out.println("** end of list **");
    }

}