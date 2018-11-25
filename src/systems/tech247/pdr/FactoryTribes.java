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
import systems.tech247.hr.Tribes;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryTribes extends ChildFactory<Object>{

    QueryTribe query;
    boolean  add = false;
    
    public FactoryTribes(QueryTribe query){
        this.query = query;
    }
    
    public FactoryTribes(String sql, boolean add){
        this.query = new QueryTribe();
        query.setSqlString(sql);
        this.add = add;
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
        if(key instanceof Tribes){
            return new TribeNode((Tribes)key);
        }else{
            return new NodeAddTool((AddTool)key);
        }
        
       
        
        
    }
    
    private class TribeNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        
        public TribeNode(Tribes emp){
            this(new InstanceContent(),emp);
        }
        
        private TribeNode (InstanceContent ic, Tribes emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            setIconBaseWithExtension("systems/tech247/util/icons/tribe.png");
            setDisplayName(emp.getTribe());
        }
    
}
    
}
