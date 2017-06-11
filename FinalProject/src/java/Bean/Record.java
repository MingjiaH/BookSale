/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

/**
 *
 * @author Jane
 */
public class Record {
    private String id;
    private String productUrl;
    private String productName;
    private String productAuthor;
    private int productCount;
    private String userName;  
    private double cost;

    public Record(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductUrl() {
        return productUrl;
    } 
    
    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }
    
    public String getProductName() {
        return productName;
    } 
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getProductAuthor() {
        return productAuthor;
    } 
    
    public void setProductAuthor(String productAuthor) {
        this.productAuthor = productAuthor;
    }
    
    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
}
