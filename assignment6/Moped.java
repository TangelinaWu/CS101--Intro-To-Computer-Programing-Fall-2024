package edu.nyu.cs.assignment6;

import java.util.Arrays;

public class Moped {
    private int street;
    private int avenue;
    private String orientation;
    private int gasLevel;
    private boolean isParked;

    public Moped() {
        this(10, 5, "south");
    }

    public Moped(int street, int avenue, String direction) {
        this.street = street;
        this.avenue = avenue;
        this.orientation = direction.toLowerCase();
        this.gasLevel = 100;
        this.isParked = false;
    }

    public void setOrientation(String orientation) {
        if (orientation.equalsIgnoreCase("north") || orientation.equalsIgnoreCase("south")
                || orientation.equalsIgnoreCase("west") || orientation.equalsIgnoreCase("east")) {
            this.orientation = orientation.toLowerCase();
        }
    }

    public String getOrientation() {
        return this.orientation;
    }

    public void printLocation() {
        System.out.println("Now at " + this.street + getSuffix(this.street) + " St. and " +
                this.avenue + getSuffix(this.avenue) + " Ave, facing " + this.orientation + ".");
    }

    private String getSuffix(int number) {
        if (number % 10 == 1 && number != 11) return "st";
        if (number % 10 == 2 && number != 12) return "nd";
        if (number % 10 == 3 && number != 13) return "rd";
        return "th";
    }

    private boolean canMove() {
        switch (this.orientation) {
            case "north":
                return this.street < 200; // Can move north as long as it's not above 200th St.
            case "south":
                return this.street > 1; // Can move south as long as it's not below 1st St.
            case "east":
                return this.avenue > 1; // Can move east as long as it's not below 1st Ave.
            case "west":
                return this.avenue < 10; // Can move west as long as it's not above 10th Ave.
            default:
                return false; // Invalid orientation.
        }
    }


    public void goStraight() {
        if (!isParked && gasLevel > 0 && canMove()) {
            switch (this.orientation) {
                case "north":
                    if (this.street < 200) this.street++;
                    else System.out.println("Cannot move straight: Boundary at 200th Street.");
                    break;
                case "south":
                    if (this.street > 1) this.street--;
                    else System.out.println("Cannot move straight: Boundary at 1st Street.");
                    break;
                case "east":
                    if (this.avenue > 1) this.avenue--;
                    else System.out.println("Cannot move straight: Boundary at 10th Avenue.");
                    break;
                case "west":
                    if (this.avenue < 10) this.avenue++;
                    else System.out.println("Cannot move straight: Boundary at 1st Avenue.");
                    break;
            }
            if (canMove()) {
                decrementGas(5);
                System.out.println("Gas Level: " + this.gasLevel + ", Location: " + Arrays.toString(getLocation())
                        + ", Orientation: " + getOrientation());
            }
            
        }
    }

    public void goBackwards() {
        if (!isParked && gasLevel > 0) {
            switch (this.orientation) {
                case "north":
                    if (this.street > 1) this.street--;
                    else System.out.println("Cannot move straight: Boundary at 1st Street.");
                    break;
                case "south":
                    if (this.street < 200) this.street++;
                    else System.out.println("Cannot move straight: Boundary at 200th Street.");
                    break;
                case "east":
                    if (this.avenue < 10) this.avenue++;
                    else System.out.println("Cannot move straight: Boundary at 1st Avenue.");
                    break;
                case "west":
                    if (this.avenue > 1) this.avenue--;
                    else System.out.println("Cannot move straight: Boundary at 10th Avenue.");
                    break;
            }
            if (canMove()) {
                decrementGas(5);
                System.out.println("Gas Level: " + this.gasLevel + ", Location: " + Arrays.toString(getLocation())
                        + ", Orientation: " + getOrientation());
            }
        }
    }

    public void turnLeft() {
        if (!isParked) {
            switch (this.orientation) {
                case "north": this.orientation = "west"; break;
                case "west": this.orientation = "south"; break;
                case "south": this.orientation = "east"; break;
                case "east": this.orientation = "north"; break;
            }
        }
    }

    public void turnRight() {
        if (!isParked) {
            switch (this.orientation) {
                case "north": this.orientation = "east"; break;
                case "east": this.orientation = "south"; break;
                case "south": this.orientation = "west"; break;
                case "west": this.orientation = "north"; break;
            }
        }
    }

    public void goToXianFamousFoods() {
        while (this.street != 15 || this.avenue != 8) {
            if (this.street != 15) { // Adjust street first
                if (this.street < 15) {
                    this.street++;
                    printLocation();
                } else {
                    this.street--;
                    printLocation();
                }
            } else if (this.avenue != 8) { // Then adjust avenue
                if (this.avenue < 8) {
                    this.avenue++;
                    printLocation();
                } else {
                    this.avenue--;
                    printLocation();
                }
            }
    
            decrementGas(5);
    
            if (gasLevel <= 0) {
                System.out.println("Ran out of gas! Refueling...at " + this.street + getSuffix(this.street) + " St. and " +
                this.avenue + getSuffix(this.avenue) + " Ave, facing " + this.orientation + ". Gas is Refilled.");
                fillGas();
            }
        }
        System.out.println("Arrived at Xi'an Famous Foods at " + this.street + getSuffix(this.street) + " St. and " +
                this.avenue + getSuffix(this.avenue) + " Ave, facing " + this.orientation + ".");
    }
    
    


    public int getGasLevel() {
        return this.gasLevel;
    }

    public void fillGas() {
        this.gasLevel = 100;
    }

    public void park() {
        this.isParked = true;
        System.out.println("Moped parked.");
    }

    private void decrementGas(int amount) {
        if (gasLevel >= amount) {
            gasLevel -= amount;
        } else {
            gasLevel = 0;
        }
    }

    public void setLocation(int[] location) {
        if (location.length == 2) {
            this.street = location[0];
            this.avenue = location[1];
        }
    }

    public int[] getLocation() {
        return new int[]{this.street, this.avenue};
    }

    public static void main(String[] args) {
        Moped moped = new Moped();
        moped.printLocation();
        moped.goStraight();
        moped.printLocation();
        moped.turnLeft();
        moped.goStraight();
        moped.printLocation();
        moped.goToXianFamousFoods();
    }
}
