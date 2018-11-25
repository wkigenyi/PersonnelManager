/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import systems.tech247.hr.Contracts;
import systems.tech247.hr.Visa;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;



/**
 *
 * @author Wilfred
 */
public class FactoryVisa extends ChildFactory<Object>{

    
    
    List list;
    
    public FactoryVisa(List list){
        this.list = list;
    }
    
    
    
    
    
    
    
    @Override
    protected boolean createKeys(List<Object> toPopulate) {

        toPopulate.addAll(list);
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Object key){
        Node node = null;
        if(key instanceof Visa){
            node = new NodeEmployeeVisa((Visa)key);
        }else if(key instanceof AddTool){
            node = new NodeAddTool((AddTool)key);
        }
        
        return node;
    }
    
    
    
}
