package mid_stu;

/**
 *
 * @author Jason
 */
public class Car {
    private String name;
    private int price;
    private boolean imported;

    public Car(){
        
    }
    
    public Car(String name, int price, boolean imported){
        this.name = name;
        this.price = price;
        this.imported = imported;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the imported
     */
    public boolean isImported() {
        return imported;
    }

    /**
     * @param imported the imported to set
     */
    public void setImported(boolean imported) {
        this.imported = imported;
    }
}
