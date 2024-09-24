import java.util.Scanner;
import java.util.ArrayList;

public class driver {
    // method for verifying userOption
    static boolean verifyUserOption (int userOption) {
        if (userOption <= 0 || userOption > 5) {
            System.out.println("Invalid Option!");
            System.out.println();
            return false;
        } else  System.out.println(); return true;

    }

    // method for option 1
    static WeatherEvent addWeatherEvent(Scanner input){
        System.out.println("1. Rain");
        System.out.println("2. Snow");
        System.out.println("3. Fog");
        System.out.println("4. Particle");

        System.out.print("What type of weather event is being added? ");
        int typeOfWeather = Integer.parseInt(input.next());


        switch (typeOfWeather) {
            case 1 -> {
                System.out.print("Where is the event happening? ");
                String location = input.next();

                System.out.print("What is the rate of fall (in/h) ");
                double rateOfFall = Double.parseDouble(input.next());

                System.out.print("What is the diameter of the drops? ");
                double dropSize = Double.parseDouble(input.next());

                System.out.println("Rain event added");

                return new Rain(location , true, rateOfFall, dropSize);

            }
            case 2 -> {
                System.out.print("Where is the event happening? ");
                String location = input.next();

                System.out.print("What is the rate of fall (in/h) ");
                double rateOfFall = Double.parseDouble(input.next());

                System.out.print("What is the temperature? (F) ");
                int temperature = Integer.parseInt(input.next());

                System.out.println("Snow event added");

                return new Snow(location, true, rateOfFall, temperature);
            }

            case 3 -> {
                System.out.print("Where is the event happening? ");
                String location = input.next();

                System.out.print("What is the visibility? (1/8 mi) ");
                int visibility = Integer.parseInt(input.next());

                System.out.print("Is the fog freezing? (y/n) ");
                String freezing = input.next();
                boolean isItFreezing;
                isItFreezing = freezing.equalsIgnoreCase("y");

                System.out.println("Fog event added");

                return new Fog(location, true, visibility, isItFreezing);
            }
            case 4 -> {
                System.out.print("Where is the event happening? ");
                String location = input.next();

                System.out.print("What is the visibility? (1/8 mi) ");
                int visibility = Integer.parseInt(input.next());

                System.out.print("What is the obscuration made of? (Sand/Dust/Ash/Other) ");
                String particleType = input.next();

                System.out.println("Particle event added ");
                return new Particle(location, true, visibility, particleType);

            }
            default -> {
                System.out.println("Error! That type of weather event doesn't exist! ");
                return null;
            }
        }

    }

    // method for option 2
    static void updateLocation(ArrayList<WeatherEvent> weatherEventArrayList, Scanner input){
        System.out.print("Enter id of weather event: ");
        int idOfEvent = input.nextInt();

        if (idOfEvent < 0 || idOfEvent > weatherEventArrayList.size()-1) {
            System.out.println("No event with that id.");
        }
        else {
            System.out.print("Enter the new location of the weather event (Currently \"" +
                    weatherEventArrayList.get(idOfEvent).getLocation() +"\") : ");

            String newLocation = input.next();
            weatherEventArrayList.get(idOfEvent).setLocation(newLocation);
            System.out.println("Location Updated");
        }
    }

    // method for option 3
    static void updateActive(ArrayList<WeatherEvent> weatherEventArrayList, Scanner input) {
        System.out.print("Enter id of weather event: ");
        int idOfEvent = input.nextInt();

        if (idOfEvent < 0 || idOfEvent > weatherEventArrayList.size()-1) {
            System.out.println("No event with that id.");
        } else {
            weatherEventArrayList.get(idOfEvent).setActive(false);
            System.out.println("Event set to \"inactive\"");
        }

    }

    // method for option 4
    static void viewAllEvents(ArrayList<WeatherEvent> weatherEventArrayList) {
        for (WeatherEvent x : weatherEventArrayList) {
            System.out.print(x.toString());
            System.out.println("\n");
        }
    }

    // actual main
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        ArrayList<WeatherEvent> weatherEventArrayList = new ArrayList<>();
        boolean keepRunning = true;
        System.out.println("[Weather Tracking System]");

        while(keepRunning) {
            System.out.println("1. Add weather event");
            System.out.println("2. Update location");
            System.out.println("3. Update active");
            System.out.println("4. View all events");
            System.out.println("5. Quit");
            System.out.print("Enter your option: ");
            int userOption = input.nextInt();
            //quick method to verify user option is valid
            if (!verifyUserOption(userOption)) continue;

            switch (userOption) {
                case 1 -> weatherEventArrayList.add(addWeatherEvent(input));
                case 2 -> updateLocation(weatherEventArrayList, input);
                case 3 -> updateActive(weatherEventArrayList, input);
                case 4 -> viewAllEvents(weatherEventArrayList);
                case 5 -> {
                    System.out.println("Shutting off...");
                    keepRunning = false;
                }
            }
            System.out.println();

        }
    }
}
