/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.api.NodeRefreshEvent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.BankBranches;
import systems.tech247.pdreditors.BankBranchEditorTopComponent;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;

/**
 *
 * @author Wilfred
 */
public class NodeBankBranch extends AbstractNode{
        
        private final InstanceContent instanceContent;
        Boolean edit;
        public NodeBankBranch(BankBranches emp,Boolean edit){
            this(new InstanceContent(),emp,edit);
        }
        
        private NodeBankBranch (InstanceContent ic, final BankBranches branch, boolean edit){
            super(Children.LEAF, new AbstractLookup(ic));
            this.edit= edit;
            instanceContent = ic;
            instanceContent.add(branch);
            
            
            
            instanceContent.add(new CapEditable() {
                @Override
                public void edit() {
                    TopComponent tc = new BankBranchEditorTopComponent(branch);
                    tc.open();
                    tc.requestActive();
                            
                }
            });
            
            instanceContent.add(new CapDeletable() {
                @Override
                public void delete() {
                    
                    Object result = DialogDisplayer.getDefault().notify(new NotifyDescriptor.Confirmation("Delete Branch?","Delete Branch"));
                    if(result == NotifyDescriptor.YES_OPTION){
                        BankBranches bb = DataAccess.entityManager.find(BankBranches.class, branch.getBankBranchID());
                        if(bb.getEmployeeBankAccountsCollection().isEmpty()){
                            DataAccess.entityManager.getTransaction().begin();
                            DataAccess.entityManager.remove(bb);
                            DataAccess.entityManager.getTransaction().commit();
                            UtilityPDR.pdrIC.set(Arrays.asList(new NodeRefreshEvent()), null);
                        }else{
                            StatusDisplayer.getDefault().setStatusText("This Branch has accounts, Move Them Before Deleting");
                        }
                    }
                    
                }
            });
            
            
            
            setIconBaseWithExtension("systems/tech247/util/icons/bankBranch.png");
            setDisplayName(branch.getBranchName());
        }
        
            @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        final BankBranches branch = getLookup().lookup(BankBranches.class);
        
        Property number = new PropertySupport("number", String.class, "Employee Number", "Number", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return branch.getEmployeeBankAccountsCollection().size();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property bank = new PropertySupport("bank", String.class, "Bank", "Bank", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return branch.getBankID().getBankName();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        
        
        
        set.put(bank);
        set.put(number);
        sheet.put(set);
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
