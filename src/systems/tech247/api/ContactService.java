/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.api;

import java.beans.PropertyChangeSupport;
import systems.tech247.hr.Contacts;
import systems.tech247.hr.Employees;

/**
 *
 * @author Admin
 */
public class ContactService {
    
    
    private static ContactService instance = null;
    
    private static final String LABEL_TYPE = "LABEL";
    private transient final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    
    
    public static ContactService getInstance(){
        if(instance==null){
            instance = new ContactService();
        }
        return instance;
    }
    
    
    
    public void update(Contacts contact,Employees emp){
        
    }
    
}
