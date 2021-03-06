package nl.kamerverhuur;

/**
 * Created by Ruben on 9/2/2016.
 */
public class Residence {

    private double squareMeters;
    private double rent;
    private String city;
    private String name;
    private int bedrooms;

    /**
     * The constructor method for nl.kamerverhuur.Residence
     * @param squareMeters the square meters of the residence
     * @param rent the rent of the residence
     * @param city the city the residence is located in
     * @param name the name of the residence
     * @param bedrooms the amount of bedrooms the residence has
     */
    public Residence(double squareMeters, double rent, String city, String name, int bedrooms) {
        this.squareMeters = squareMeters;
        this.rent = rent;
        this.city = city;
        this.name = name;
        this.bedrooms = bedrooms;
    }

    public double getSquareMeters() {
        return squareMeters;
    }

    public double getRent() {
        return rent;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public String toHTML(){
        return "Plaats: " + getCity() + "</br>" +
                "Aantal vierkante meters: " + getSquareMeters() + "</br>" +
                "Aantal slaapkamers: " + getBedrooms() + "</br>" +
                "Huurprijs: " + getRent() + "</br>" +
                "Verhuurder: " + getName() + "</br></br>";
    }
}
