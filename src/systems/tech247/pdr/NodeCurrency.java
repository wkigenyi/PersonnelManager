/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.nodes.Sheet.Set;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import systems.tech247.hr.Currencies;
import systems.tech247.pdreditors.PDRCurrencyEditorTopComponent;
import systems.tech247.util.CapEditable;

/**
 *
 * @author Wilfred
 */
public class NodeCurrency extends AbstractNode{
        
        private final InstanceContent instanceContent;
        
        
        public NodeCurrency(Currencies emp){
            this(new InstanceContent(),emp);
        }
        
        private NodeCurrency (InstanceContent ic, final Currencies emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            instanceContent.add(new CapEditable() {
                @Override
                public void edit() {
                    TopComponent tc = new PDRCurrencyEditorTopComponent(emp);
                    tc.open();
                    tc.requestActive();
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/Currency.png");
            setDisplayName(emp.getCurrencyName());
        }

    @Override
    protected Sheet createSheet() {
        final Currencies bean = getLookup().lookup(Currencies.class);
        final NumberFormat nf = new DecimalFormat("#,###.00");
        Sheet sheet = Sheet.createDefault();
        Set set = Sheet.createPropertiesSet();
        
        Property code = new PropertySupport("code", String.class, "Code", "Code", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return bean.getCurrencyCode();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        Property base = new PropertySupport("base", Boolean.class, "Base", "Base", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return bean.getIsBaseCurrency();
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
                
        Property rate = new PropertySupport("rate", String.class, "Base", "Base", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return nf.format(bean.getConversionRate());
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        Property number = new PropertySupport("number", String.class, "Number", "Number", true, false) {
            @Override
            public Object getValue() throws IllegalAccessException, InvocationTargetException {
                return bean.getEmployeesCollection().size()+"";
            }
            
            @Override
            public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        
        set.put(number);
        set.put(rate);
        set.put(code);
        set.put(base);
        
        sheet.put(set);
        
        return sheet; //To change body of generated methods, choose Tools | Templates.
    }
        
        
    
}
