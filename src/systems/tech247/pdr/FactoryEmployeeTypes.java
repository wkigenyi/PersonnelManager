/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.JobPositions;
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
        if(add){
            list.add(new AddTool(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TopComponent tc = new EmpTypeEditorTopComponent();
                    tc.open();
                    tc.requestActive();
                }
            }));
            
        }
        //Populate the list of child entries
        list.addAll(DataAccess.searchPositions(sql));
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Object key){
        Node node = null;
        if(key instanceof TblEmployeeType){
            node = new EmpTypeNode((TblEmployeeType)key);
        }else if(key instanceof AddTool){
            node = new NodeAddTool((AddTool)key);
        }
        
        return node;
    }
    
    private class EmpTypeNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        TblEmployeeType position;
        
        public EmpTypeNode(TblEmployeeType emp){
            this(new InstanceContent(),emp);
        }
        
        private EmpTypeNode (InstanceContent ic, TblEmployeeType unit){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            position = unit;
            instanceContent.add(unit);
            setIconBaseWithExtension("systems/tech247/util/icons/position.png");
            setDisplayName(unit.getEmpTypeName());
        }

        @Override
        public Action getPreferredAction() {
            return new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TopComponent tc = new EmpTypeEditorTopComponent(position);
                    tc.open();
                    tc.requestActive();
                }
            };
        }
        
    
}
    
}
