/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Bean.Production;
import Bean.Record;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jane
 */
public class VisitorService {
    
    //when users haven't logged in
    public Record getByProductionName(String name){
        ProductionService ps=new ProductionService();
        Production book01= ps.getByProductId(name);
        Record book=new Record();
        book.setProductName(name);
        book.setCost(book01.getPrice());
        return book;        
    }
    
    public List<Record> getByUserName(String name){
        List<Record> book= new ArrayList<>();
        return book;        
    }
    
}
