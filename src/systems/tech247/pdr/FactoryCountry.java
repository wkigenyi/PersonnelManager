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
import systems.tech247.hr.Countries;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryCountry extends ChildFactory<Object>{

    QueryCountry query;
    boolean add = false;
    
    public FactoryCountry(QueryCountry query){
        this.query = query;
    }
    public FactoryCountry(String sql){
        this.query = new QueryCountry();
        query.setSqlString(sql);
    }
    
    public FactoryCountry(String sql, boolean add){
        this.query = new QueryCountry();
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
        //Add Add Tool
        if(add){
            list.add(new AddTool(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //For Adding a new country
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
        Node node = null;
        if(key instanceof Countries){
            node = new NationNode((Countries)key);
        }else if(key instanceof AddTool){
            node = new NodeAddTool((AddTool)key);
        }
       
        
        return node;
    }
    
    private class NationNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        
        public NationNode(Countries emp){
            this(new InstanceContent(),emp);
        }
        
        private NationNode (InstanceContent ic, Countries emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            setIconBaseWithExtension("systems/tech247/util/icons/nation.png");
            setDisplayName(emp.getCountryName());
        }
    
}
    
}
