/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import systems.tech247.api.NodeRefreshEvent;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import systems.tech247.hr.BankBranches;
import systems.tech247.hr.Banks;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryBankBranches extends ChildFactory<Object> implements LookupListener{

    QueryBankBranches query;
    Boolean edit;
    Lookup.Result<NodeRefreshEvent> result;
    
    public FactoryBankBranches(Banks bank, Boolean edit){
        this.query = new QueryBankBranches(bank);
        this.edit = edit;
        this.result = UtilityPDR.getInstance().getLookup().lookupResult(NodeRefreshEvent.class);
        result.addLookupListener(this);
    }
    
    public FactoryBankBranches(){
        this.edit = false;
        this.query = new QueryBankBranches(null);
        this.result = UtilityPDR.getInstance().getLookup().lookupResult(NodeRefreshEvent.class);
        result.addLookupListener(this);
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
        //Add The Tool
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
        if(key instanceof BankBranches){
            return new NodeBankBranch((BankBranches)key,edit);
        }else{
            return new NodeAddTool((AddTool)key);
        }
       
        
       
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result r = (Lookup.Result) le.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof NodeRefreshEvent){
                refresh(true);
            }
            
        }
    }
    
    
    
}
