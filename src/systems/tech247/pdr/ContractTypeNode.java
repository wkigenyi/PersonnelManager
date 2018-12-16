/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.PdContractTypes;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;

/**
 *
 * @author Wilfred
 */
public class ContractTypeNode extends AbstractNode implements LookupListener {
    private final InstanceContent instanceContent;
        Boolean edit;
        PdContractTypes p;
        Lookup.Result<NodeContractTypesRefreshEvent> result;
        public ContractTypeNode(PdContractTypes emp, Boolean edit){
            this(new InstanceContent(),emp,edit);
            this.p = emp;
        }
        
        private ContractTypeNode (InstanceContent ic, PdContractTypes emp,Boolean edit){
            super(Children.LEAF, new AbstractLookup(ic));
            this.edit = edit;
            p = emp;
            instanceContent = ic;
            instanceContent.add(emp);
            instanceContent.add(new CapEditable() {
                @Override
                public void edit() {
                    TopComponent tc = new ContractTypeEditorTopComponent(p);
                    tc.open();
                    tc.requestActive();
                }
            });
            instanceContent.add(new CapDeletable() {
                @Override
                public void delete() {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/capex.png");
            setDisplayName(emp.getDescription());
            this.result = UtilityPDR.getInstance().getLookup().lookupResult(NodeContractTypesRefreshEvent.class);
            result.addLookupListener(this);
        }

        @Override
        public void resultChanged(LookupEvent ev) {
            Lookup.Result r = (Lookup.Result) ev.getSource();
        Collection c = r.allInstances();
        for (Object object : c){
            if (object instanceof NodeContractTypesRefreshEvent){
                PdContractTypes pdc = DataAccess.entityManager.find(PdContractTypes.class, p.getId());
                setDisplayName(pdc.getDescription());
            }
            
        }
        
        
        
    
}
        @Override
    protected Sheet createSheet() {
        Sheet sheet = Sheet.createDefault();
        Sheet.Set set = Sheet.createPropertiesSet();
        final PdContractTypes ctype = getLookup().lookup(PdContractTypes.class);
        
        Property period = new PropertySupport("period", String.class, "Period", "Period", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                if(ctype.getInDays()){
                    return "Days";
                }else if(ctype.getInWeeks()){
                    return "Weeks";
                }else if(ctype.getInMonths()){
                    return "Months";
                }else if(ctype.getInYears()){
                    return "Years";
                }else{
                    return "Unknown";
                }
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        Property length = new PropertySupport("length", String.class, "Length", "Length", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return ctype.getCorrespondingValue();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        set.put(period);
        set.put(length);
        sheet.put(set);
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
}