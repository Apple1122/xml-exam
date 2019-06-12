/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlFinalExam;

/**
 *
 * @author Jason
 */
public class Order {
    private String Item;
    private String Address;
    
    public Order(){
        
    }
    
    public Order(String Item, String Address){
        this.Item = Item;
        this.Address = Address;
    }

    /**
     * @return the Item
     */
    public String getItem() {
        return Item;
    }

    /**
     * @param Item the Item to set
     */
    public void setItem(String Item) {
        this.Item = Item;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }
    
    
    
}
