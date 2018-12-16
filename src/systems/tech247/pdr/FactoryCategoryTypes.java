/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.CSSSCategories;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;


/**
 *
 * @author Wilfred
 */
public class FactoryCategoryTypes extends ChildFactory<Object>{

    
    public FactoryCategoryTypes(){
                
    }
    
    
    
    @Override
    protected boolean createKeys(List<Object> list) {
        
      
        //Populate the list of child entries
        list.addAll(DataAccess.searchDefaultCategories());
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Object key){
        if(key instanceof CSSSCategories){
            return new NodeSetupCompanyStructure((CSSSCategories)key);
        }else{
            return new NodeAddTool((AddTool)key);
        }
       
        
       
    }
    
    
    
}
