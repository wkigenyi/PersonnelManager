/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.TblEmployeeType;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;



/**
 *
 * @author Wilfred
 */
public class FactoryEmployeeTypes extends ChildFactory<Object>{

    
    
    String sql;
    boolean add;
    public FactoryEmployeeTypes(String sql){
        this(sql, false);
    }
    
    public FactoryEmployeeTypes(String sql, boolean add){
        this.add = add;
        this.sql = sql;
    }
    
    @Override
    protected boolean createKeys(List<Object> list) {
        
        //Populate the list of child entries
        list.addAll(DataAccess.searchPositions(sql));
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Object key){
        Node node = null;
        if(key instanceof TblEmployeeType){
            node = new NodeEmployeeType((TblEmployeeType)key);
        }else if(key instanceof AddTool){
            node = new NodeAddTool((AddTool)key);
        }
        
        return node;
    }
    

    
}
