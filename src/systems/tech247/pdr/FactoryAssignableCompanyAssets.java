/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.openide.awt.StatusDisplayer;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.dbaccess.BooleanEditor;
import systems.tech247.dbaccess.DataAccess;
import systems.tech247.hr.CompanyAssets;
import systems.tech247.hr.Employees;

import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class FactoryAssignableCompanyAssets extends ChildFactory<Asset> implements LookupListener{

    DataAccess da = new DataAccess();
    EntityManager entityManager =  da.getEntityManager();
    TopComponent empTc = WindowManager.getDefault().findTopComponent("EmployeesTopComponent");
    int eid;
    Employees emp;
    Lookup.Result<Employees> empRslt = empTc.getLookup().lookupResult(Employees.class);
    
    QueryCompanyAssets query;
    
    public FactoryAssignableCompanyAssets(QueryCompanyAssets query){
        this.query = query;
        empRslt.addLookupListener(this);
        resultChanged(new LookupEvent(empRslt));
    }
    
    @Override
    protected boolean createKeys(List<Asset> list) {
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
        List<CompanyAssets> a = query.getList();
        for(CompanyAssets c : a){
            list.add(new Asset(c,false));
        }
        
        //return true since we are set
        return true;
    }
    
    @Override
    protected Node createNodeForKey(Asset key){
        Node node = new AssetNode(key);
       
        
        return node;
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Lookup.Result<Employees> r = (Lookup.Result<Employees>)le.getSource();
        for(Employees e : r.allInstances()){
            emp = e;
            eid = e.getEmployeeID();
        }
    }
    
    private class AssetNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        
        public AssetNode(Asset emp){
            this(new InstanceContent(),emp);
        }
        
        private AssetNode (InstanceContent ic, Asset emp){
            super(Children.LEAF, new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(emp);
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName(emp.getAsset().getAssetName());
            
            setShortDescription("Asset Code: "+ emp.getAsset().getAssetCode());
        }
        
        @Override
    protected Sheet createSheet(){
        Sheet s = super.createSheet();
        Sheet.Set basic = Sheet.createPropertiesSet();
        basic.setDisplayName("Basic Info");
        final Asset asset = getLookup().lookup(Asset.class);
        
            final PropertySupport.Reflection assign;
            Property select;
            Property assetCode;
            
        try {
            
            assign = new PropertySupport.Reflection(asset, Boolean.class, "select");
            assign.setPropertyEditorClass(BooleanEditor.class);
            
            
            
            select = new PropertySupport(
                    "select", 
                    Boolean.class, 
                    "Select", 
                    "", true, true) {
                @Override
                public Boolean getValue() throws IllegalAccessException, InvocationTargetException {
                    return asset.getSelect();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
                
                @Override
                public void setValue(Object val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

                             
                        CompanyAssets cAsset = asset.getAsset();
                        boolean assigned = (Boolean)val;
                        // Assign the asset
                        
                        assignAsset(eid, cAsset, assigned);
                        
                        assign.setValue(assigned);
                        
                    
                }
            };
            
            assetCode = new PropertySupport("assetCode", String.class, "Asset Code", "", false, false) {
                @Override
                public Object getValue() throws IllegalAccessException, InvocationTargetException {
                    return asset.getAsset().getAssetCode();
                }
                
                @Override
                public void setValue(Object t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                    
                }
            };
            basic.put(assetCode);
            basic.put(select);
            
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        
            
        
            
            
            
            
            
        
        
        s.put(basic);
        
        
        return s;
    }
    
    
    void assignAsset(int eid,CompanyAssets casset,boolean removeadd){
        String sqlStringAdd = "INSERT INTO [dbo].[CompanyAssetIssue] ([Employee_ID],[AssetCode],[Comments],[DriverLicenceNo],[StartDate],[Expirydate])"
                + "VALUES (?,?,?,?,?,?)";
        String sqlStringDel = "DELETE FROM [dbo].[CompanyAssetIssue] WHERE AssetCode= ?";
        try{
        if(removeadd){
            //Asign the Asset
            entityManager.getTransaction().begin();
            Query query =  entityManager.createNativeQuery(sqlStringAdd);
            query.setParameter(1, eid);
            query.setParameter(2, casset.getAssetCode());
            query.executeUpdate();
            entityManager.getTransaction().commit();
            StatusDisplayer.getDefault().setStatusText("Asset Assigned");
            String sqlString ="SELECT * FROM CompanyAssets WHERE AssetCode NOT IN (SELECT DISTINCT AssetCode FROM CompanyAssetIssue )";
            UtilityPDR.loadAssignableAssets(sqlString);
            UtilityPDR.loadAssetIssue(emp);
        }else{
            //Asign the Asset
            entityManager.getTransaction().begin();
            Query query =  entityManager.createNativeQuery(sqlStringDel);
            query.setParameter(1, casset.getAssetCode());
            query.executeUpdate();
            entityManager.getTransaction().commit();
            StatusDisplayer.getDefault().setStatusText("Asset Collected");
        }
        }catch(Exception ex){
            StatusDisplayer.getDefault().setStatusText("Something went wrong");
        }
    }

    
    
    
    
    }
   
    
    
    
}
    

    