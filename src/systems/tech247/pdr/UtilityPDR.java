/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdr;

import systems.tech247.view.QueryCategory;
import systems.tech247.util.QueryDepartment;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.StatusDisplayer;
import org.openide.explorer.ExplorerManager;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import systems.tech247.hr.Awards;
import systems.tech247.hr.BankBranches;
import systems.tech247.hr.Banks;
import systems.tech247.hr.CompanyAssetIssue;
import systems.tech247.hr.CompanyAssets;
import systems.tech247.hr.Contacts;
import systems.tech247.hr.Countries;
import systems.tech247.hr.Ctypes;
import systems.tech247.hr.Currencies;
import systems.tech247.hr.EmployeeBankAccounts;
import systems.tech247.hr.Employees;
import systems.tech247.hr.Employment;
import systems.tech247.hr.Family;
import systems.tech247.hr.JDCategories;
import systems.tech247.hr.JobPositions;
import systems.tech247.hr.Kin;
import systems.tech247.hr.Locations;
import systems.tech247.hr.Nationalities;
import systems.tech247.hr.OrganizationUnitTypes;
import systems.tech247.hr.OrganizationUnits;
import systems.tech247.hr.PdContractTypes;
import systems.tech247.hr.Prof;
import systems.tech247.hr.Religions;
import systems.tech247.hr.Tribes;
import systems.tech247.pdreditors.EmployeeAwardEditor;
import systems.tech247.pdreditors.EmployeeBankAccountEditor;


import systems.tech247.pdreditors.EmployeeEmpHEditor;
import systems.tech247.pdreditors.EmployeeFamilyEditor;
import systems.tech247.pdreditors.EmployeeNOKEditor;
import systems.tech247.pdreditors.EmployeeProfEditor;


import systems.tech247.pdreditors.PDRBankBranchEditor;
import systems.tech247.pdreditors.PDRBankEditor;
import systems.tech247.pdreditors.PDRCompanyAssetEditor;
import systems.tech247.pdreditors.PDRContactTypeEditor;
import systems.tech247.pdreditors.PDRContractTypeEditor;
import systems.tech247.pdreditors.PDRCountryEditor;
import systems.tech247.pdreditors.PDRCurrencyEditor;
import systems.tech247.pdreditors.PDRDeparmentEditor;
import systems.tech247.pdreditors.PDRJDCategoryEditor;
import systems.tech247.pdreditors.PDRJDEditor;
import systems.tech247.pdreditors.PDRLocationEditor;
import systems.tech247.pdreditors.PDRNationalityEditor;
import systems.tech247.pdreditors.PDROUTypeEditor;
import systems.tech247.pdreditors.PDRPositionEditor;
import systems.tech247.pdreditors.PDRReligionEditor;
import systems.tech247.pdreditors.PDRTribeEditor;
import systems.tech247.pdrsetup.NodePDRSetup;
import systems.tech247.util.CapCreatable;
import systems.tech247.util.CapDeletable;
import systems.tech247.util.CapEditable;
import systems.tech247.api.ReloadableQueryCapability;

/**
 *
 * @author Wilfred
 */
public class UtilityPDR implements Lookup.Provider {
    
    static UtilityPDR instance;

    public static UtilityPDR getInstance() {
        if(instance==null){
            instance = new UtilityPDR();
        }
        return instance;
    }
    
    
    
    public static InstanceContent pdrIC = new InstanceContent();
    public Lookup lookup = new AbstractLookup(pdrIC);
    
    
    //load NoBirthdays
    public static ExplorerManager emNoBirthDays = new ExplorerManager();
    public static void loadNoBirthDays(List list){
        emNoBirthDays.setRootContext(new AbstractNode(Children.create(new FactoryBirthdays(list), true)));
    }
    
    //load Birthdays
    public static ExplorerManager emBirthDays = new ExplorerManager();
    public static void loadBirthDays(List list){
        emBirthDays.setRootContext(new AbstractNode(Children.create(new FactoryBirthdays(list), true)));
    }
    //load Contracts
    
    public static void loadEmployeeContracts(ExplorerManager em, List list){
        em.setRootContext(new AbstractNode(Children.create(new FactoryContracts(list), true)));
    }
    public static void loadEmployeeVisas(ExplorerManager em, List list){
        em.setRootContext(new AbstractNode(Children.create(new FactoryVisa(list), true)));
    }
    
    
    //load PDR Setup
    public static ExplorerManager emPDRsetup = new ExplorerManager();
    public static void loadPDRSetup(){
        emPDRsetup.setRootContext(new NodePDRSetup());
    }
    //Company Setup
    public static ExplorerManager emPDRCountry = new ExplorerManager();
    
    //Selectable Employee
    public static ExplorerManager emSelectableEmployee = new ExplorerManager();

    //Personnel Director
    /*Employee Info*/
    
    /* For Holding Savable Lookups */
    public static InstanceContent editorIC = new InstanceContent();
    
    
            ////Employee Contracts Starts Here ////
    public static ExplorerManager emEmployeeContracts = new ExplorerManager();
    static QueryContracts queryContracts;
    
    

    @Override
    public Lookup getLookup() {
        return lookup;
    }
    
    
    

    
    ///Contracts Ends Here ///
    
    
        ////Employee Contacts Starts Here ////
    public static ExplorerManager emEmployeeContacts = new ExplorerManager();
    static QueryContacts queryContacts;
    
    public static void loadContacts(Employees emp){
        queryContacts = new QueryContacts(emp);
        emEmployeeContacts.setRootContext(new ContactsRootNode(emp));
    }
    
    private static class ContactsRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public ContactsRootNode(Employees emp) {
            this(new InstanceContent(),emp);
        }
        private ContactsRootNode (InstanceContent ic,final Employees emp){
            super(Children.create(new FactoryContacts(queryContacts), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    //NotifyDescriptor nd = new NotifyDescriptor(new EmployeeContactEditor(emp), "New Contact", NotifyDescriptor.OK_CANCEL_OPTION,
                    //NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    //DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Contact Here");
        }
    }
    
    private static class FactoryContacts extends ChildFactory<Contacts>{

        QueryContacts query;
        
        public FactoryContacts(QueryContacts query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Contacts> list) {
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
        protected Node createNodeForKey(Contacts key){
            Node node = new ContactNode(key);
            return node;
        }
    
        private class ContactNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public ContactNode(Contacts emp){
                    this(new InstanceContent(),emp);
                }
        
                private ContactNode (InstanceContent ic, final Contacts emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            //NotifyDescriptor nd = new NotifyDescriptor(new EmployeeContactEditor(emp), "Contact Editor", NotifyDescriptor.OK_CANCEL_OPTION,
                            //NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            //DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/phonebook.png");
                    setDisplayName(emp.getContact());
                }
            }
        }
    
    ///Contacts Ends Here ///
    
            ////Employee Referees Starts Here ////
    public static ExplorerManager emEmployeeReferees = new ExplorerManager();
    static QueryReferees queryReferees;
    
    
    
    
    
    
    
    ///Referees Ends Here ///
    
            ////Employee Family Starts Here ////
    public static ExplorerManager emEmployeeFamily = new ExplorerManager();
    static QueryFamily queryFamily;
    
    public static void loadFamily(Employees emp){
        queryFamily = new QueryFamily(emp);
        emEmployeeFamily.setRootContext(new FamilyRootNode(emp));
    }
    
    private static class FamilyRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public FamilyRootNode(Employees emp) {
            this(new InstanceContent(),emp);
        }
        private FamilyRootNode (InstanceContent ic,final Employees emp){
            super(Children.create(new FactoryFamily(queryFamily), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new EmployeeFamilyEditor(emp), "New Family", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Family Here");
        }
    }
    
    private static class FactoryFamily extends ChildFactory<Family>{

        QueryFamily query;
        
        public FactoryFamily(QueryFamily query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Family> list) {
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
        protected Node createNodeForKey(Family key){
            Node node = new FamilyNode(key);
            return node;
        }
    
        private class FamilyNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public FamilyNode(Family emp){
                    this(new InstanceContent(),emp);
                }
        
                private FamilyNode (InstanceContent ic, final Family emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new EmployeeFamilyEditor(emp), "Family Editor", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/person.png");
                    setDisplayName(emp.getSurName()+" "+emp.getOtherNames());
                }
            }
        }
    
    ///Family Ends Here ///
    
    ////Employee NOK Starts Here ////
    public static ExplorerManager emEmployeeKins = new ExplorerManager();
    static QueryKins queryNOK;
    
    public static void loadNOK(Employees emp){
        queryNOK = new QueryKins(emp);
        emEmployeeKins.setRootContext(new NOKRootNode(emp));
    }
    
    private static class NOKRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public NOKRootNode(Employees emp) {
            this(new InstanceContent(),emp);
        }
        private NOKRootNode (InstanceContent ic,final Employees emp){
            super(Children.create(new FactoryNOK(queryNOK), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new EmployeeNOKEditor(emp), "New Next Of Kin", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Next Of Kin Here");
        }
    }
    
    private static class FactoryNOK extends ChildFactory<Kin>{

        QueryKins query;
        
        public FactoryNOK(QueryKins query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Kin> list) {
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
        protected Node createNodeForKey(Kin key){
            Node node = new KinNode(key);
            return node;
        }
    
        private class KinNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public KinNode(Kin emp){
                    this(new InstanceContent(),emp);
                }
        
                private KinNode (InstanceContent ic, final Kin emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new EmployeeNOKEditor(emp), "Next Of Kin Editor", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/person.png");
                    setDisplayName(emp.getSurName()+" "+emp.getOtherNames());
                }
            }
        }
    
    ///Next Of Kin Ends Here ///
    
    ////Employment History Starts Here ////
    public static ExplorerManager emEmployeeEmploymentHistory = new ExplorerManager();
    static QueryEmployment queryEmp;
    
    public static void loadEmployment(Employees emp){
        queryEmp = new QueryEmployment(emp);
        emEmployeeEmploymentHistory.setRootContext(new EmploymentRootNode(emp));
    }
    
    private static class EmploymentRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public EmploymentRootNode(Employees emp) {
            this(new InstanceContent(),emp);
        }
        private EmploymentRootNode (InstanceContent ic,final Employees emp){
            super(Children.create(new FactoryEmployment(queryEmp), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new EmployeeEmpHEditor(emp), "New Employment History", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Employment Here");
        }
    }
    
    private static class FactoryEmployment extends ChildFactory<Employment>{

        QueryEmployment query;
        
        public FactoryEmployment(QueryEmployment query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Employment> list) {
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
        protected Node createNodeForKey(Employment key){
            Node node = new EmploymentNode(key);
            return node;
        }
    
        private class EmploymentNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public EmploymentNode(Employment emp){
                    this(new InstanceContent(),emp);
                }
        
                private EmploymentNode (InstanceContent ic, final Employment emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new EmployeeEmpHEditor(emp), "Employment Editor", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/company.png");
                    setDisplayName(emp.getEmployer());
                }
            }
        }
    
    ///Employment History Ends Here ///
    
                ////Employee Awards Starts Here ////
    public static ExplorerManager emEmployeeAwards = new ExplorerManager();
    static QueryAwards queryAwards;
    
    public static void loadAwards(Employees emp){
        queryAwards = new QueryAwards(emp);
        emEmployeeAwards.setRootContext(new AwardsRootNode(emp));
    }
    
    private static class AwardsRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public AwardsRootNode(Employees emp) {
            this(new InstanceContent(),emp);
        }
        private AwardsRootNode (InstanceContent ic,final Employees emp){
            super(Children.create(new FactoryAwards(queryAwards), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new EmployeeAwardEditor(emp), "New Award", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Awards Here");
        }
    }
    
    private static class FactoryAwards extends ChildFactory<Awards>{

        QueryAwards query;
        
        public FactoryAwards(QueryAwards query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Awards> list) {
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
        protected Node createNodeForKey(Awards key){
            Node node = new AwardNode(key);
            return node;
        }
    
        private class AwardNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public AwardNode(Awards emp){
                    this(new InstanceContent(),emp);
                }
        
                private AwardNode (InstanceContent ic, final Awards emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new EmployeeAwardEditor(emp), "Award Editor", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    if(emp.getAward() != null)
                    setDisplayName(emp.getAward());
                    else
                    setDisplayName(emp.getCourse());    
                }
            }
        }
    
    ///Awards Ends Here ///
    
    
    
    ////Education Starts Here ////
    public static ExplorerManager emEmployeeEducation = new ExplorerManager();
    static QueryEducation queryEdu;
    
    
    
    
    
    
    
    ///Education Ends Here ///

    
    ////Proffessional Starts Here ////
    public static ExplorerManager emEmployeeProf = new ExplorerManager();
    static QueryProf queryProf;
    
    public static void loadProf(Employees emp){
        queryProf = new QueryProf(emp);
        emEmployeeProf.setRootContext(new ProfRootNode(emp));
    }
    
    private static class ProfRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public ProfRootNode(Employees emp) {
            this(new InstanceContent(),emp);
        }
        private ProfRootNode (InstanceContent ic,final Employees emp){
            super(Children.create(new FactoryProf(queryProf), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new EmployeeProfEditor(emp), "New Prof", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Proffessionals Here");
        }
    }
    
    private static class FactoryProf extends ChildFactory<Prof>{

        QueryProf query;
        
        public FactoryProf(QueryProf query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Prof> list) {
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
        protected Node createNodeForKey(Prof key){
            Node node = new ProfNode(key);
            return node;
        }
    
        private class ProfNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public ProfNode(Prof emp){
                    this(new InstanceContent(),emp);
                }
        
                private ProfNode (InstanceContent ic, final Prof emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new EmployeeProfEditor(emp), "Education Editor", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getAward()+ " from " + emp.getInstitution());
                }
            }
        }
    
    ///Proffessional Ends Here ///    
    
        ////Asset Issue Starts Here ////
    public static ExplorerManager emEmployeeAssetIssue = new ExplorerManager();
    public static ExplorerManager emAssignableAsset = new ExplorerManager();
    static QueryAssetsIssue queryAssetsIssue;
    
    public static void loadAssetIssue(Employees emp){
        queryAssetsIssue = new QueryAssetsIssue(emp);
        emEmployeeAssetIssue.setRootContext(new AssetIssueRootNode(emp));
    }
    
    public static void loadAssignableAssets(String sql){
        QueryCompanyAssets queryAssets = new QueryCompanyAssets();
        queryAssets.setSqlString(sql);
        emAssignableAsset.setRootContext(new AbstractNode(Children.create(new FactoryAssignableCompanyAssets(queryAssets), true)));
    }
    
    private static class AssetIssueRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
        
        TopComponent assetTc = WindowManager.getDefault().findTopComponent("AssetsTopComponent");
       
        public AssetIssueRootNode(Employees emp) {
            this(new InstanceContent(),emp);
        }
        private AssetIssueRootNode (InstanceContent ic,final Employees emp){
            super(Children.create(new FactoryAssetIssue(queryAssetsIssue), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(assetTc, "Assign Asset", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Assign Assets Here");
        }
    }
    
    private static class FactoryAssetIssue extends ChildFactory<CompanyAssetIssue>{

        QueryAssetsIssue query;
        
        public FactoryAssetIssue(QueryAssetsIssue query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<CompanyAssetIssue> list) {
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
        protected Node createNodeForKey(CompanyAssetIssue key){
            Node node = new AssetIssueNode(key);
            return node;
        }
    
        private class AssetIssueNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public AssetIssueNode(CompanyAssetIssue emp){
                    this(new InstanceContent(),emp);
                }
        
                private AssetIssueNode (InstanceContent ic, final CompanyAssetIssue emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                               
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getAssetCode().getAssetName());
                }
            }
        }
    
    ///Assets Issue Ends Here ///
    
                    ////Employee Accounts Starts Here ////
    public static ExplorerManager emEmployeeBankAccounts = new ExplorerManager();
    static QueryBankAccounts queryAccounts;
    
    public static void loadAccounts(Employees emp){
        queryAccounts = new QueryBankAccounts(emp);
        emEmployeeBankAccounts.setRootContext(new AccountsRootNode(emp));
    }
    
    private static class AccountsRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public AccountsRootNode(Employees emp) {
            this(new InstanceContent(),emp);
        }
        private AccountsRootNode (InstanceContent ic,final Employees emp){
            super(Children.create(new FactoryAccounts(queryAccounts), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new EmployeeBankAccountEditor(emp), "New Account", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Account Here");
        }
    }
    
    private static class FactoryAccounts extends ChildFactory<EmployeeBankAccounts>{

        QueryBankAccounts query;
        
        public FactoryAccounts(QueryBankAccounts query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<EmployeeBankAccounts> list) {
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
        protected Node createNodeForKey(EmployeeBankAccounts key){
            Node node = new BankAccountNode(key);
            return node;
        }
    
        private class BankAccountNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public BankAccountNode(EmployeeBankAccounts emp){
                    this(new InstanceContent(),emp);
                }
        
                private BankAccountNode (InstanceContent ic, final EmployeeBankAccounts emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new EmployeeBankAccountEditor(emp), "Bank Account Editor", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getAccountName()+" " + emp.getAccountNumber()+" "+ emp.getBankBranchID().getBankID().getBankName());
                }
            }
        }
    
    ///Accounts Ends Here ///
    
                        ////Employee Accounts Starts Here ////

    

    
       
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*PDR Setup*/
    public static InstanceContent editorPDRIC = new InstanceContent();
    
    
    ////Contract Types Starts Here ////
    public static ExplorerManager pdrContractTypes = new ExplorerManager();
    static QueryContractTypes queryContractTypes = new QueryContractTypes();
    
    public static void loadContractTypes(String sqlString){
        queryContractTypes.setSqlString(sqlString);
        pdrContractTypes.setRootContext(new ContractTypesRootNode());
    }
    
    private static class ContractTypesRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public ContractTypesRootNode() {
            this(new InstanceContent());
        }
        private ContractTypesRootNode (InstanceContent ic){
            super(Children.create(new FactoryContTypes(queryContractTypes), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRContractTypeEditor(), "New Contract Type", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Contract Types Here");
        }
    }
    
    private static class FactoryContTypes extends ChildFactory<PdContractTypes>{

        QueryContractTypes query;
        
        public FactoryContTypes(QueryContractTypes query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<PdContractTypes> list) {
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
        protected Node createNodeForKey(PdContractTypes key){
            Node node = new CtypeNode(key);
            return node;
        }
    
        private class CtypeNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public CtypeNode(PdContractTypes emp){
                    this(new InstanceContent(),emp);
                }
        
                private CtypeNode (InstanceContent ic, final PdContractTypes emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRContractTypeEditor(emp), "Edit Contract Type", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getDescription());
                }
            }
        }
    
    ///Contract Type Ends Here ///
    
    ////Religion Starts Here ////
    public static ExplorerManager pdrReligions = new ExplorerManager();
    static QueryReligions queryReligion = new QueryReligions();
    
    public static void loadReligions(String sqlString){
        queryReligion.setSqlString(sqlString);
        pdrReligions.setRootContext(new ReligionRootNode());
    }
    
    private static class ReligionRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public ReligionRootNode() {
            this(new InstanceContent());
        }
        private ReligionRootNode (InstanceContent ic){
            super(Children.create(new FactoryReligions(queryReligion), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRReligionEditor(), "New Religion", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Religions Here");
        }
    }
    
    private static class FactoryReligions extends ChildFactory<Religions>{

        QueryReligions query;
        
        public FactoryReligions(QueryReligions query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Religions> list) {
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
        protected Node createNodeForKey(Religions key){
            Node node = new ItemNode(key);
            return node;
        }
    
        private class ItemNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public ItemNode(Religions emp){
                    this(new InstanceContent(),emp);
                }
        
                private ItemNode (InstanceContent ic, final Religions emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRReligionEditor(emp), "Edit Religion", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getReligion());
                }
            }
        }
    
    ///Religion Ends Here ///
    
    
    ////Job Description Starts Here ////
    public static ExplorerManager pdrJD = new ExplorerManager();
    static QueryJDCategoryForJob queryJD;
    
    public static void loadJD(JobPositions position){
        queryJD = new QueryJDCategoryForJob(position);
        pdrJD.setRootContext(new JDRootNode(position));
    }
    
    private static class JDRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public JDRootNode(JobPositions position) {
            this(new InstanceContent(),position);
        }
        private JDRootNode (InstanceContent ic, final JobPositions position){
            super(Children.create(new FactoryJD(position), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRJDEditor(position), "New JD", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add JD Here");
        }
    
    
    
        }
    
    ///Religion Ends Here ///
    
    ////Locations Starts Here ////
    public static ExplorerManager pdrLocations = new ExplorerManager();
    static QueryLocation queryLocation = new QueryLocation();
    
    public static void loadLocations(String sqlString){
        queryLocation.setSqlString(sqlString);
        pdrLocations.setRootContext(new LocationRootNode());
    }
    
    private static class LocationRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public LocationRootNode() {
            this(new InstanceContent());
        }
        private LocationRootNode (InstanceContent ic){
            super(Children.create(new FactoryLocations(queryLocation), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRLocationEditor(), "New Location", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Locations Here");
        }
    }
    
    private static class FactoryLocations extends ChildFactory<Locations>{

        QueryLocation query;
        
        public FactoryLocations(QueryLocation query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Locations> list) {
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
        protected Node createNodeForKey(Locations key){
            Node node = new ItemNode(key);
            return node;
        }
    
        private class ItemNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public ItemNode(Locations emp){
                    this(new InstanceContent(),emp);
                }
        
                private ItemNode (InstanceContent ic, final Locations emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRLocationEditor(emp), "Edit Location", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getLocationName());
                }
            }
        }
    
    ///Locations Ends Here ///
    
    ////Countries Starts Here ////
    public static ExplorerManager pdrCountries = new ExplorerManager();
    static QueryCountry queryCountry = new QueryCountry();
    
    public static void loadCountry(String sqlString){
        queryCountry.setSqlString(sqlString);
        pdrCountries.setRootContext(new CountryRootNode());
    }
    
    private static class CountryRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public CountryRootNode() {
            this(new InstanceContent());
        }
        private CountryRootNode (InstanceContent ic){
            super(Children.create(new FactoryCountry(queryCountry), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRCountryEditor(), "New Country", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Country Here");
        }
    }
    
    private static class FactoryCountry extends ChildFactory<Countries>{

        QueryCountry query;
        
        public FactoryCountry(QueryCountry query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Countries> list) {
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
        protected Node createNodeForKey(Countries key){
            Node node = new ItemNode(key);
            return node;
        }
    
        private class ItemNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public ItemNode(Countries emp){
                    this(new InstanceContent(),emp);
                }
        
                private ItemNode (InstanceContent ic, final Countries emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRCountryEditor(emp), "Edit Location", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getCountryName());
                }
            }
        }
    
    ///Countries End Here ///
    
   
    
    ////Job Positions Starts Here ////
    public static ExplorerManager pdrJobs = new ExplorerManager();
    static QueryPosition queryJob = new QueryPosition();
    
    public static void loadJobPositions(String sqlString){
        queryJob.setSqlString(sqlString);
        pdrJobs.setRootContext(new AbstractNode(Children.create(new FactoryPositions(queryJob), true)));
    }
    
    private static class PositionsRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public PositionsRootNode() {
            this(new InstanceContent());
        }
        private PositionsRootNode (InstanceContent ic){
            super(Children.create(new FactoryPositions(queryJob), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRPositionEditor(), "New Position", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Position Here");
        }
    }
    
    private static class FactoryPositions extends ChildFactory<JobPositions>{

        QueryPosition query;
        
        public FactoryPositions(QueryPosition query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<JobPositions> list) {
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
        protected Node createNodeForKey(JobPositions key){
            Node node = new PositionNode(key);
            return node;
        }
    
        private class PositionNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public PositionNode(JobPositions emp){
                    this(new InstanceContent(),emp);
                }
        
                private PositionNode (InstanceContent ic, final JobPositions emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRPositionEditor(emp), "Edit Position", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getPositionName());
                }
            }
        }
    
    ///Job Positions Ends Here ///
    
    ////JD Categories Starts Here ////
    public static ExplorerManager pdrJDcategories = new ExplorerManager();
    static QueryJDCategories queryJDCategory = new QueryJDCategories();
    
    public static void loadJDCategories(String sqlString){
        queryJDCategory.setSqlString(sqlString);
        pdrJDcategories.setRootContext(new JDCategoriesRootNode());
    }
    
    private static class JDCategoriesRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public JDCategoriesRootNode() {
            this(new InstanceContent());
        }
        private JDCategoriesRootNode (InstanceContent ic){
            super(Children.create(new FactoryJDCategories(queryJDCategory), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRJDCategoryEditor(), "New JD Cateory", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add JD Category Here");
        }
    }
    
    private static class FactoryJDCategories extends ChildFactory<JDCategories>{

        QueryJDCategories query;
        
        public FactoryJDCategories(QueryJDCategories query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<JDCategories> list) {
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
        protected Node createNodeForKey(JDCategories key){
            Node node = new PositionNode(key);
            return node;
        }
    
        private class PositionNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public PositionNode(JDCategories emp){
                    this(new InstanceContent(),emp);
                }
        
                private PositionNode (InstanceContent ic, final JDCategories emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRJDCategoryEditor(emp), "Edit JD Category", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getCategoryName());
                }
            }
        }
    
    ///Job Positions Ends Here ///

    

    
    ////Nationality Starts Here ////
    public static ExplorerManager pdrNationalities = new ExplorerManager();
    static QueryNation queryNationality = new QueryNation();
    
    public static void loadNationalities(String sqlString){
        queryNationality.setSqlString(sqlString);
        pdrNationalities.setRootContext(new NationalityRootNode());
    }
    
    private static class NationalityRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public NationalityRootNode() {
            this(new InstanceContent());
        }
        private NationalityRootNode (InstanceContent ic){
            super(Children.create(new FactoryNations(queryNationality), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRNationalityEditor(), "New Nationality", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Nationalities Here");
        }
    }
    
    private static class FactoryNations extends ChildFactory<Nationalities>{

        QueryNation query;
        
        public FactoryNations(QueryNation query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Nationalities> list) {
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
        protected Node createNodeForKey(Nationalities key){
            Node node = new NationNode(key);
            return node;
        }
    
        private class NationNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public NationNode(Nationalities emp){
                    this(new InstanceContent(),emp);
                }
        
                private NationNode (InstanceContent ic, final Nationalities emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRNationalityEditor(emp), "Edit Nationality", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getNationality());
                }
            }
        }
    
    ///Nationality Ends Here ///
    
    ////Currency Starts Here ////
    public static ExplorerManager pdrCurrency = new ExplorerManager();
    static QueryCurrency queryCurrency = new QueryCurrency();
    
    public static void loadCurrency(String sqlString){
        queryCurrency.setSqlString(sqlString);
        pdrCurrency.setRootContext(new CurrencyRootNode());
    }
    
    private static class CurrencyRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public CurrencyRootNode() {
            this(new InstanceContent());
        }
        private CurrencyRootNode (InstanceContent ic){
            super(Children.create(new FactoryCurrency(queryCurrency), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRCurrencyEditor(), "New Currency", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Currency Here");
        }
    }
    
    private static class FactoryCurrency extends ChildFactory<Currencies>{

        QueryCurrency query;
        
        public FactoryCurrency(QueryCurrency query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Currencies> list) {
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
        protected Node createNodeForKey(Currencies key){
            Node node = new CurrencyNode(key);
            return node;
        }
    
        private class CurrencyNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public CurrencyNode(Currencies emp){
                    this(new InstanceContent(),emp);
                }
        
                private CurrencyNode (InstanceContent ic, final Currencies emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRCurrencyEditor(emp), "Edit Currency", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getCurrencyName());
                }
            }
        }
    
    ///Currency Ends Here ///
    
    
    
    
    
    
    
    ////Tribes Starts Here ////
    public static ExplorerManager pdrTribes = new ExplorerManager();
    static QueryTribe queryTribe = new QueryTribe();
    
    public static void loadTribes(String sqlString){
        queryTribe.setSqlString(sqlString);
        pdrTribes.setRootContext(new TribeRootNode());
    }
    
    private static class TribeRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public TribeRootNode() {
            this(new InstanceContent());
        }
        private TribeRootNode (InstanceContent ic){
            super(Children.create(new FactoryTribes(queryTribe), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRTribeEditor(), "New Tribe", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Tribes Here");
        }
    }
    
    private static class FactoryTribes extends ChildFactory<Tribes>{

        QueryTribe query;
        
        public FactoryTribes(QueryTribe query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Tribes> list) {
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
        protected Node createNodeForKey(Tribes key){
            Node node = new TribeNode(key);
            return node;
        }
    
        private class TribeNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public TribeNode(Tribes emp){
                    this(new InstanceContent(),emp);
                }
        
                private TribeNode (InstanceContent ic, final Tribes emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRTribeEditor(emp), "Edit Nationality", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getTribe());
                }
            }
        }
    
    ///Tribes Ends Here ///
    
     ////Bank Starts Here ////
    public static ExplorerManager pdrBanks = new ExplorerManager();
    static QueryBanks queryBank = new QueryBanks();
    
    public static void loadBanks(String sqlString){
        queryBank.setSqlString(sqlString);
        pdrBanks.setRootContext(new BanksRootNode());
    }
    
    private static class BanksRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public BanksRootNode() {
            this(new InstanceContent());
        }
        private BanksRootNode (InstanceContent ic){
            super(Children.create(new FactoryBanks(queryBank), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRBankEditor(), "New Bank", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Banks Here");
        }
    }
    
    private static class FactoryBanks extends ChildFactory<Banks>{

        QueryBanks query;
        
        public FactoryBanks(QueryBanks query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Banks> list) {
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
        protected Node createNodeForKey(Banks key){
            Node node = new BankNode(key);
            return node;
        }
    
        private class BankNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public BankNode(Banks emp){
                    this(new InstanceContent(),emp);
                }
        
                private BankNode (InstanceContent ic, final Banks emp){
                    super(Children.create(new FactoryBankBranches(new QueryBankBranches(emp)), true), new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRBankEditor(emp), "Edit Bank", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    
                    instanceContent.add(new CapCreatable() {
                        @Override
                        public void create() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRBankBranchEditor(emp), "New Branch", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                        }
                    });
                    
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getBankName());
                }
            }
        }
    //Bank Branches Factory
    private static class FactoryBankBranches extends ChildFactory<BankBranches>{

        QueryBankBranches query;
        
        public FactoryBankBranches(QueryBankBranches query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<BankBranches> list) {
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
        protected Node createNodeForKey(BankBranches key){
            Node node = new BankBranchNode(key);
            return node;
        }
    
        private class BankBranchNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public BankBranchNode(BankBranches emp){
                    this(new InstanceContent(),emp);
                }
        
                private BankBranchNode (InstanceContent ic, final BankBranches emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRBankBranchEditor(emp), "Edit Bank", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getBranchName());
                }
            }
        }
    ///Bank Ends Here ///
    
        ////Company Assets Starts Here ////
    public static ExplorerManager pdrCompanyAssets = new ExplorerManager();
    static QueryCompanyAssets queryCompanyAssets = new QueryCompanyAssets();
    
    public static void loadCompanyAssets(String sqlString){
        queryCompanyAssets.setSqlString(sqlString);
        pdrCompanyAssets.setRootContext(new CompanyAssetsRootNode());
    }
    
    private static class CompanyAssetsRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public CompanyAssetsRootNode() {
            this(new InstanceContent());
        }
        private CompanyAssetsRootNode (InstanceContent ic){
            super(Children.create(new FactoryCompanyAssets(queryCompanyAssets), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRCompanyAssetEditor(), "New Asset", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Assets Here");
        }
    }
    
    private static class FactoryCompanyAssets extends ChildFactory<CompanyAssets>{

        QueryCompanyAssets query;
        
        public FactoryCompanyAssets(QueryCompanyAssets query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<CompanyAssets> list) {
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
        protected Node createNodeForKey(CompanyAssets key){
            Node node = new AssetNode(key);
            return node;
        }
    
        private class AssetNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public AssetNode(CompanyAssets emp){
                    this(new InstanceContent(),emp);
                }
        
                private AssetNode (InstanceContent ic, final CompanyAssets emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRCompanyAssetEditor(emp), "Edit Asset", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getAssetName());
                }
            }
        }
    
    ///Assets Ends Here ///
    
    ////Contact Types Starts Here ////
    public static ExplorerManager pdrContactTypes = new ExplorerManager();
    static QueryContactTypes queryContactTypes = new QueryContactTypes();
    
    public static void loadContactTypes(String sqlString){
        queryContactTypes.setSqlString(sqlString);
        pdrContactTypes.setRootContext(new ContactTypesRootNode());
    }
    
    private static class ContactTypesRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public ContactTypesRootNode() {
            this(new InstanceContent());
        }
        private ContactTypesRootNode (InstanceContent ic){
            super(Children.create(new FactoryCTypes(queryContactTypes), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRContactTypeEditor(), "New Contact Type", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add Contact Types Here");
        }
    }
    
    private static class FactoryCTypes extends ChildFactory<Ctypes>{

        QueryContactTypes query;
        
        public FactoryCTypes(QueryContactTypes query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<Ctypes> list) {
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
        protected Node createNodeForKey(Ctypes key){
            Node node = new CtypeNode(key);
            return node;
        }
    
        private class CtypeNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public CtypeNode(Ctypes emp){
                    this(new InstanceContent(),emp);
                }
        
                private CtypeNode (InstanceContent ic, final Ctypes emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRContactTypeEditor(emp), "Edit Contact Type", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getDescription());
                }
            }
        }
    
    ///Contact Type Ends Here ///
    
        ////OU Types Starts Here ////
    public static ExplorerManager pdrOrganizationUnitTypes = new ExplorerManager();
    static QueryOUType queryOUTypes = new QueryOUType();
    
    public static void loadOUTypes(String sqlString){
        queryOUTypes.setSqlString(sqlString);
        pdrOrganizationUnitTypes.setRootContext(new OUTypesRootNode());
    }
    
    private static class OUTypesRootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public OUTypesRootNode() {
            this(new InstanceContent());
        }
        private OUTypesRootNode (InstanceContent ic){
            super(Children.create(new FactoryOUTypes(queryOUTypes), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDROUTypeEditor(), "New OU Type", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add OU Type");
        }
    }
    
    private static class FactoryOUTypes extends ChildFactory<OrganizationUnitTypes>{

        QueryOUType query;
        
        public FactoryOUTypes(QueryOUType query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<OrganizationUnitTypes> list) {
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
        protected Node createNodeForKey(OrganizationUnitTypes key){
            Node node = new OUtypeNode(key);
            return node;
        }
    
        private class OUtypeNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public OUtypeNode(OrganizationUnitTypes emp){
                    this(new InstanceContent(),emp);
                }
        
                private OUtypeNode (InstanceContent ic, final OrganizationUnitTypes emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDROUTypeEditor(emp), "Edit Contact Type", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getOrganizationUnitTypeName());
                }
            }
        }
    
    ///OU Type Ends Here ///
    
        
        ////OU Starts Here ////
    public static ExplorerManager pdrOrganizationUnits = new ExplorerManager();
    static QueryDepartment queryOU = new QueryDepartment("SELECT r from OrganizationUnits r");
    
    public static void loadOUs(String sqlString){
        queryOU.setSqlString(sqlString);
        pdrOrganizationUnits.setRootContext(new OURootNode());
    }
    
        ////OU Starts Here ////
    public static ExplorerManager pdrCategories = new ExplorerManager();
    static QueryCategory queryCat = new QueryCategory();
    
    public static void loadCategories(String sqlString){
        queryCat.setSqlString(sqlString);
        pdrCategories.setRootContext(new OURootNode());
    }
    
    
    
    
    
    private static class OURootNode extends AbstractNode{
        
        private final InstanceContent instanceContent;
       
        public OURootNode() {
            this(new InstanceContent());
        }
        private OURootNode (InstanceContent ic){
            super(Children.create(new FactoryOU(queryOU), true),new AbstractLookup(ic));
            instanceContent = ic;
            instanceContent.add(new CapCreatable() {
                @Override
                public void create() {     
                    NotifyDescriptor nd = new NotifyDescriptor(new PDRDeparmentEditor(), "New OU", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                    //nd.setNoDefaultClose(true);  
                    DialogDisplayer.getDefault().notifyLater(nd);
                }
            });
            setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
            setDisplayName("Add OU");
        }
    }
    
    private static class FactoryOU extends ChildFactory<OrganizationUnits>{

        QueryDepartment query;
        
        public FactoryOU(QueryDepartment query){
            this.query = query;
        }
    
        @Override
        protected boolean createKeys(List<OrganizationUnits> list) {
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
        protected Node createNodeForKey(OrganizationUnits key){
            Node node = new OUtypeNode(key);
            return node;
        }
    
        private class OUtypeNode extends AbstractNode{
                
            private final InstanceContent instanceContent;
                
                public OUtypeNode(OrganizationUnits emp){
                    this(new InstanceContent(),emp);
                }
        
                private OUtypeNode (InstanceContent ic, final OrganizationUnits emp){
                    super(Children.LEAF, new AbstractLookup(ic));
                    instanceContent = ic;
                    instanceContent.add(emp);
                    instanceContent.add(new CapEditable() {
                        @Override
                        public void edit() {
                            NotifyDescriptor nd = new NotifyDescriptor(new PDRDeparmentEditor(emp), "Edit OU", NotifyDescriptor.OK_CANCEL_OPTION,
                            NotifyDescriptor.PLAIN_MESSAGE, new Object[]{}, null);
                            //nd.setNoDefaultClose(true);  
                            DialogDisplayer.getDefault().notifyLater(nd);
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });           
                    instanceContent.add(new CapDeletable() {
                        @Override
                        public void delete() {
                            StatusDisplayer.getDefault().setStatusText("We are not deleting these days");
                        }
                    });
                    setIconBaseWithExtension("systems/tech247/util/icons/settings.png");
                    setDisplayName(emp.getOrganizationUnitName());
                }
            }
        }
    
    ///OU Ends Here ///
    
    
    
    /*PDR Ends Here */

    //Security
    
    //Payroll
    
    //Getting Photos
    public static ImageIcon getImage(byte[] dbInfo) throws IOException{
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(dbInfo));
        int h = image.getHeight();
        int w = image.getWidth();
        int scaledheight = 288*h/w;
        Image resizedImage = image.getScaledInstance(288,scaledheight,WIDTH);
        //StatusDisplayer.getDefault().setStatusText("Image Size:" + image.getHeight()+" x "+ image.getWidth());
        ImageIcon icon = new ImageIcon(resizedImage);
        return icon;
    }
}
