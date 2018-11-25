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
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.hr.Currencies;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryCurrency extends ChildFactory<Object>{

    QueryCurrency query;
    boolean add = false;
    
    public FactoryCurrency(QueryCurrency query){
        this.query = query;
    }
    
    public FactoryCurrency(){
        this.query = new QueryCurrency();
        String sql = "SELECT c FROM Currencies c";
        query.setSqlString(sql);
        this.add = true;
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
        //Add Tool
        if(add){
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
        if(key instanceof Currencies){
            return new CurrencyNode((Currencies)key);
        }else{
            return new NodeAddTool((AddTool)key);
        }
       
        
       
    }
    
    private class CurrencyNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        
        public CurrencyNode(Currencies emp){
            this(new InstanceContent(),emp);
        }
        
        private CurrencyNode (InstanceContent ic, Currencies emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            setIconBaseWithExtension("systems/tech247/util/icons/Currency.png");
            setDisplayName(emp.getCurrencyName());
        }
    
}
    
}
