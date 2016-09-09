/**
 * Created by Ruben on 9/2/2016.
 */
public class Room {

    private double vierkante_meters;
    private double huurprijs;
    private String plaats;
    private String naam;
    private int slaapkamers;

    /**
     * Constructor for Room class
     * @param vierkante_meters
     * @param huurprijs
     * @param plaats
     * @param naam
     * @param slaapkamers
     */
    public Room(double vierkante_meters, double huurprijs, String plaats, String naam, int slaapkamers) {
        this.vierkante_meters = vierkante_meters;
        this.huurprijs = huurprijs;
        this.plaats = plaats;
        this.naam = naam;
        this.slaapkamers = slaapkamers;
    }
}
