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
import systems.tech247.util.Marital;


/**
 *
 * @author Wilfred
 */
public class FactoryMarital extends ChildFactory<Marital>{

   
    
    public FactoryMarital(){
       
    }
    
    @Override
    protected boolean createKeys(List<Marital> list) {
        //get this ability from the look
        
        //Populate the list of child entries
        list.add(new Marital(Short.valueOf("1")));
        list.add(new Marital(Short.valueOf("2")));
        list.add(new Marital(Short.valueOf("3")));
        list.add(new Marital(Short.valueOf("4")));
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Marital key){
        Node node = new MaritalNode(key);
       
        
        return node;
    }
    
    private class MaritalNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        
        public MaritalNode(Marital emp){
            this(new InstanceContent(),emp);
        }
        
        private MaritalNode (InstanceContent ic, Marital emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName(emp.getDescription());
        }
    
}
    
}
