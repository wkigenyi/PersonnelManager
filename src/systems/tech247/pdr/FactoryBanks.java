/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import systems.tech247.hr.Banks;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryBanks extends ChildFactory<Object>{

    QueryBanks query;
    Boolean edit;
    
    public FactoryBanks(QueryBanks query, Boolean edit){
        this.query = query;
        this.edit = edit; 
                
    }
    
    public FactoryBanks(){
        this.query = new  QueryBanks();
        String sql = "SELECT b FROM Banks b";
        query.setSqlString(sql);
        this.edit = true;
    }
    
    @Override
    protected boolean createKeys(List<Object> list) {
        //get this ability from the look
        ReloadableQueryCapability r = query.getLookup().lookup(ReloadableQueryCapability.class);
        //Use the ability
        if(r != null){
            try{
                r.reload();
            }catch(Exception ex){
                
            }
        }
        //Add The Add Tool
        if(edit){
            list.add(new AddTool(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            }));
        }
        //Populate the list of child entries
        list.addAll(query.getList());
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Object key){
        if(key instanceof Banks){
            return new NodeBank((Banks)key,edit);
        }else{
            return new NodeAddTool((AddTool)key);
        }
       
        
       
    }
    
    
    
}
