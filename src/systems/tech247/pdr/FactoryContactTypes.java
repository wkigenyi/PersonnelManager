/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.hr.Ctypes;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryContactTypes extends ChildFactory<Object> implements LookupListener{
    
    Lookup.Result<NodeContractTypesRefreshEvent> result;
    QueryContactTypes query;
    Boolean add;
    
    public FactoryContactTypes(QueryContactTypes query,Boolean add){
        this.query = query;
        this.add = add;
        this.result = UtilityPDR.getInstance().getLookup().lookupResult(NodeContractTypesRefreshEvent.class);
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
        //Allow add 

        
        
        //Populate the list of child entries
        list.addAll(query.getList());
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Object key){
        Node node = null;
        if(key instanceof Ctypes){
            node = new NodeCtype((Ctypes)key);
        }else if(key instanceof AddTool){
            node = new NodeAddTool((AddTool)key);
        }
       
        
        return node;
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    
}
