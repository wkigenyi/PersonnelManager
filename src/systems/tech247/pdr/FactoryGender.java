/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import systems.tech247.util.Gender;


/**
 *
 * @author Wilfred
 */
public class FactoryGender extends ChildFactory<Gender>{

   
    
    public FactoryGender(){
       
    }
    
    @Override
    protected boolean createKeys(List<Gender> list) {
        //get this ability from the look
        
        //Populate the list of child entries
        list.add(new Gender(Short.valueOf("1")));
        list.add(new Gender(Short.valueOf("2")));
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Gender key){
        Node node = new NodeGender(key);
       
        
        return node;
    }
    

    
}
