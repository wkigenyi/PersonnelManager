/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdreditors;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.actions.AbstractSavable;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.Employees;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//systems.tech247.pdreditors//EmployeeDisability//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "EmployeeDisabilityTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)


@Messages({
    "CTL_EmployeeDisabilityAction=Disability Info",
    "CTL_EmployeeDisabilityTopComponent=Disability Info",
    "HINT_EmployeeDisabilityTopComponent=Disability Info"
})
public final class EmployeeDisabilityTopComponent extends TopComponent{
    
    InstanceContent ic = new InstanceContent();

    Employees updatable;
    DataAccess da = new DataAccess();
    EntityManager entityManager = DataAccess.getEntityManager();
    Employees emp;
    
    
    
    
    
    public EmployeeDisabilityTopComponent(){
        this(null);
    }

    public EmployeeDisabilityTopComponent(Employees e) {
        initComponents();
        setName("Disability Info ->"+ e.getSurName()+" "+e.getOtherNames());
        setToolTipText(Bundle.HINT_EmployeeDisabilityTopComponent());
        fillEmployee(e);
        
        emp = e;
        
        if(null!=e){
            updatable = entityManager.find(Employees.class, e.getEmployeeID());
        }
        
        jtaDisability.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatable.setDisabilityDetails(jtaDisability.getText());
                modify();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatable.setDisabilityDetails(jtaDisability.getText());
                modify();//To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatable.setDisabilityDetails(jtaDisability.getText());
                modify();
                 //To change body of generated methods, choose Tools | Templates.
            }
        });
        

        

        
        
        

        

        

        

        
        

        
        
        
        
        
        


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbDisability = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDisability = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(jcbDisability, org.openide.util.NbBundle.getMessage(EmployeeDisabilityTopComponent.class, "EmployeeDisabilityTopComponent.jcbDisability.text")); // NOI18N
        jcbDisability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDisabilityActionPerformed(evt);
            }
        });

        jtaDisability.setColumns(20);
        jtaDisability.setRows(5);
        jScrollPane1.setViewportView(jtaDisability);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EmployeeDisabilityTopComponent.class, "EmployeeDisabilityTopComponent.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbDisability)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jcbDisability)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(179, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbDisabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDisabilityActionPerformed
        updatable.setIsPhysicallyDisabled(jcbDisability.isSelected());
        modify();
    }//GEN-LAST:event_jcbDisabilityActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JCheckBox jcbDisability;
    private javax.swing.JTextArea jtaDisability;
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {

    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    
    
    void fillEmployee(Employees emp){
                Employees e = DataAccess.entityManager.find(Employees.class, emp.getEmployeeID());
                setName(e.getSurName()+" "+e.getOtherNames()+ " > Disability Details");
                
                
                
                try{
                jcbDisability.setSelected(e.getIsPhysicallyDisabled());
                }catch(Exception ex){
                    
                }
                
                try{
                    jtaDisability.setText(e.getDisabilityDetails());
                }catch(NullPointerException ex){
                    
                }

                
               
                
                

                

                

                

                //Set the photo   
             
                
                
                
                
        
                
    }
    
    void modify(){
        
        
                if(getLookup().lookup(EmpSavable.class)==null){
                            ic.add(new EmpSavable());
                }
            
    }
    
    private class EmpSavable extends AbstractSavable{
        
        EmpSavable(){
            register();
        }

        @Override
        protected String findDisplayName() {
            
                return emp.getSurName()+" "+emp.getOtherNames();
            
        }
        
        EmployeeDisabilityTopComponent tc(){
            return EmployeeDisabilityTopComponent.this;
        }

        @Override
        protected void handleSave() throws IOException {
            
            tc().ic.remove(this);
            unregister();
            
                //Creating a new Employee
                entityManager.getTransaction().begin();
                entityManager.getTransaction().commit();
                tc().close();
                    
                
            
        }

        @Override
        public boolean equals(Object o) {
            if(o instanceof EmpSavable){
                EmpSavable e = (EmpSavable)o;
                return tc() == e.tc();
            }
            return false;        }

        @Override
        public int hashCode() {
            return tc().hashCode();
        }
        
    }
}
