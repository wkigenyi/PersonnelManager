/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.util.List;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.hr.JDCategories;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryJDCategories extends ChildFactory<JDCategories>{

    QueryJDCategories query;
    
    public FactoryJDCategories(QueryJDCategories query){
        this.query = query;
    }
    
    @Override
    protected boolean createKeys(List<JDCategories> list) {
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
    protected Node createNodeForKey(JDCategories key){
        Node node = new TribeNode(key);
       
        
        return node;
    }
    
    private class TribeNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        
        public TribeNode(JDCategories emp){
            this(new InstanceContent(),emp);
        }
        
        private TribeNode (InstanceContent ic, JDCategories emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName(emp.getCategoryName());
        }
    
}
    
}
