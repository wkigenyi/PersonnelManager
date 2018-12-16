/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.lang.reflect.InvocationTargetException;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.hr.BankBranches;
import systems.tech247.hr.Banks;
import systems.tech247.pdreditors.PDRBankBranchEditor;
import systems.tech247.pdreditors.PDRBankEditor;
import systems.tech247.util.CapCreatable;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;

/**
 *
 * @author Wilfred
 */
public class NodeBank extends AbstractNode{
        
        
        
        public NodeBank(Banks bank,boolean edit){
            this(new InstanceContent(),bank,edit);
        }
        
        private NodeBank (InstanceContent ic, final Banks bank,boolean edit){
            super(Children.create(new FactoryBankBranches(bank,edit), true), new AbstractLookup(ic));
            ic.add(bank);
            
                ic.add(new CapEditable() {
                    @Override
                    public void edit() {
                        NotifyDescriptor nd = new NotifyDescriptor(new PDRBankEditor(bank), "Edit Bank", NotifyDescriptor.OK_CANCEL_OPTION,
                        NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                        //nd.setNoDefaultClose(true);  
                        DialogDisplayer.getDefault().notifyLater(nd);
                    }
                });
                
                ic.add(new CapCreatable() {
                    @Override
                    public void create() {
                        NotifyDescriptor nd = new NotifyDescriptor(new PDRBankBranchEditor(bank), "Add New Branch", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
        //nd.setNoDefaultClose(true);  
        DialogDisplayer.getDefault().notifyLater(nd);
                    }
                });
                
                ic.add(new CapDeletable() {
                    @Override
                    public void delete() {
                        StatusDisplayer.getDefault().setStatusText("Delete the Bank");
                    }
                });
            
            
            
            
            
            
            setIconBaseWithExtension("systems/tech247/util/icons/bank.png");
            setDisplayName(bank.getBankName());
        }

    @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        final Banks bank = getLookup().lookup(Banks.class);
        
        Property number = new PropertySupport("number", String.class, "Employee Number", "Number", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                int i = 0;
                for(BankBranches b : bank.getBankBranchesCollection()){
                    i += b.getEmployeeBankAccountsCollection().size();
                }
                return i;
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        set.put(number);
        sheet.put(set);
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
        
        
    
}
