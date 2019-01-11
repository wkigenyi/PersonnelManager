/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Employees;
import systems.tech247.hr.Family;
import systems.tech247.hr.Kin;
import systems.tech247.pdreditors.FamilyEditorTopComponent;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

/**
 *
 * @author Admin
 */
public class FactoryEmployeeFamily extends ChildFactory<Object> implements LookupListener {
    
    Result<NodeRefreshFamilyEvent> result;
    private static final Logger logger = Logger.getLogger(FactoryEmployeeFamily.class.getName());
    Employees emp;
    
    
    public FactoryEmployeeFamily(Employees emp){
        this.emp = emp;
        
        
        result = UtilityPDR.getInstance().getLookup().lookupResult(NodeRefreshFamilyEvent.class);
        result.addLookupListener(this);
    }
    
    

    @Override
    protected boolean createKeys(List<Object> list) {
//        list.add(new AddTool(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                TopComponent tc  = new FamilyEditorTopComponent(emp);
//                tc.open();
//                tc.requestActive();
//            }
//        }));
        list.addAll(new DataAccess().searchFamily(emp));
        
        
        return true;
    }
    @Override
    protected Node createNodeForKey(Object key) {
        
        Node node =  null;
        if(key instanceof AddTool){
            node= new NodeAddTool((AddTool)key);
        }else if(key instanceof Family){
            node= new NodeFamily((Family)key);
        }
        
        return node;
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result r = (Lookup.Result) le.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof NodeRefreshFamilyEvent){
                refresh(true);
            }
            
        }
    }
    
}
