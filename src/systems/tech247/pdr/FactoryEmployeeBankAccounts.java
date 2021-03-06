/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.beans.IntrospectionException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.EmployeeBankAccounts;
import systems.tech247.hr.Employees;
import systems.tech247.util.AddTool;
import systems.tech247.util.NodeAddTool;

/**
 *
 * @author Admin
 */
public class FactoryEmployeeBankAccounts extends ChildFactory<Object> implements LookupListener {
    
    Result<NodeRefreshBankAccountEvent> result;
    private static final Logger logger = Logger.getLogger(FactoryEmployeeBankAccounts.class.getName());
    Employees emp;
    
    public FactoryEmployeeBankAccounts(){
        this(null);
    }
    
    public FactoryEmployeeBankAccounts(Employees emp){
        this.emp = emp;
        
        this.result = UtilityPDR.getInstance().getLookup().lookupResult(NodeRefreshBankAccountEvent.class);
        result.addLookupListener(this);
    }
    
    

    @Override
    protected boolean createKeys(List<Object> list) {
//        list.add(new AddTool(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                TopComponent tc  = new AccountEditorTopComponent(emp);
//                tc.open();
//                tc.requestActive();
//            }
//        }));
        
            
        
            list.addAll( DataAccess.searchBankAccounts(emp));
        
        
        
        
        return true;
    }
    @Override
    protected Node createNodeForKey(Object key) {
        
        Node node =  null;
        if(key instanceof AddTool){
            node= new NodeAddTool((AddTool)key);
        }else if(key instanceof EmployeeBankAccounts){
            try {
                node= new NodeEmployeeBankAccount((EmployeeBankAccounts)key);
            } catch (IntrospectionException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        
        return node;
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result r = (Lookup.Result) le.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof NodeRefreshBankAccountEvent){
                refresh(true);
            }
            
        }
    }
    
}
