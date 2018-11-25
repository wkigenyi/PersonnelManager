/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.util.List;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.JDCategories;
import systems.tech247.hr.JobPositionJDs;
import systems.tech247.hr.JobPositions;
import systems.tech247.pdreditors.PDRJDEditor;
import systems.tech247.util.CapCreatable;
import systems.tech247.util.CapEditable;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryJD extends ChildFactory<Integer>{

    QueryJDCategoryForJob query;
    JobPositions position;
    
    public FactoryJD(JobPositions position){
        this.query = new QueryJDCategoryForJob(position);
        this.position = position;
    }
    
    @Override
    protected boolean createKeys(List<Integer> list) {
        //get this ability from the look
        ReloadableQueryCapability r = query.getLookup().lookup(ReloadableQueryCapability.class);
        //Use the ability
        if(r != null){
            try{
                r.reload();
            }catch(Exception ex){
                
            }
        }
        //Populate the list of child entries
        list.addAll(query.getList());
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Integer key){
        Node node = new JDCategoryNode(key);
       
        
        return node;
    }
    
    private class JDCategoryNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        
        public JDCategoryNode(Integer emp){
            this(new InstanceContent(),emp);
        }
        
        private JDCategoryNode (InstanceContent ic, Integer emp){
            super(Children.create(new FactoryJDsForJob(position, emp), true), new AbstractLookup(ic));
            final JDCategories jd = DataAccess.getEntityManager().find(JDCategories.class, emp);
            instanceContent = ic;
            instanceContent.add(emp);
            
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRJDEditor(position,jd), "New Contact", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName(jd.getCategoryName());
        }
    
}
    private class FactoryJDsForJob extends  ChildFactory<JobPositionJDs>{
        
        
        
        QueryJDForJobAndCategory query;
        
        
        public FactoryJDsForJob(JobPositions position, int category){
            query = new QueryJDForJobAndCategory(position, category);
        }

        @Override
        protected boolean createKeys(List<JobPositionJDs> list) {
            //get this ability from the look
        ReloadableQueryCapability r = query.getLookup().lookup(ReloadableQueryCapability.class);
        //Use the ability
        if(r != null){
            try{
                r.reload();
            }catch(Exception ex){
                
            }
        }
        //Populate the list of child entries
        list.addAll(query.getList());
        
        //return true since we are set
        return true;
        }
        
        @Override
    protected Node createNodeForKey(JobPositionJDs key){
        Node node = new JDNode(key);
       
        
        return node;
    }
    
    private class JDNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        
        public JDNode(JobPositionJDs emp){
            this(new InstanceContent(),emp);
        }
        
        private JDNode (InstanceContent ic, final JobPositionJDs emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            instanceContent.add(new CapEditable() {
                @Override
                public void edit() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRJDEditor(emp.getPositionID(),emp.getJDCategoryID(),emp), "Edit JD", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName(emp.getFieldValue());
        }

    }
    }
}
    

