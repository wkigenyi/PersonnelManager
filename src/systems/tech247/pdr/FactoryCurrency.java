/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;
import javax.swing.AbstractAction;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import systems.tech247.hr.Currencies;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryCurrency extends ChildFactory<Object> implements LookupListener{
    
    Lookup.Result<NodeRefreshCurrencyEvent> result;
    QueryCurrency query;
    boolean add = false;
    
    public FactoryCurrency(QueryCurrency query){
        this.query = query;
    }
    
    public FactoryCurrency(){
        this.query = new QueryCurrency();
        
        String sql = "SELECT c FROM Currencies c";
        query.setSqlString(sql);
        this.result = UtilityPDR.getInstance().getLookup().lookupResult(NodeRefreshCurrencyEvent.class);
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

        //Populate the list of child entries
        list.addAll(query.getList());
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Object key){
        if(key instanceof Currencies){
            return new NodeCurrency((Currencies)key);
        }else{
            return new NodeAddTool((AddTool)key);
        }
       
        
       
    }
    
    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result r = (Lookup.Result) le.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof NodeRefreshCurrencyEvent){
                refresh(true);
            }
            
        }
    }
    
    
    
}
