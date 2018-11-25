/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package systems.tech247.pdrsetup;

import systems.tech247.util.SetupItem;
import systems.tech247.util.NodeSetupItem;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import systems.tech247.pdr.FactoryBanks;
import systems.tech247.pdr.FactoryCategoryTypes;
import systems.tech247.pdr.FactoryContactTypes;
import systems.tech247.pdr.FactoryContractTypes;
import systems.tech247.pdr.FactoryCountry;
import systems.tech247.pdr.FactoryCurrency;
import systems.tech247.pdr.FactoryLocation;
import systems.tech247.pdr.FactoryNations;
import systems.tech247.pdr.FactoryPositions;
import systems.tech247.pdr.FactoryReligions;
import systems.tech247.pdr.FactoryTribes;
import systems.tech247.pdr.FactoryDepartments;
import systems.tech247.pdr.FactoryEmployeeTypes;
import systems.tech247.pdr.QueryContactTypes;
import systems.tech247.pdr.QueryContractTypes;

/**
 *
 * @author WKigenyi
 */
public class FactoryPDRSetup extends ChildFactory<SetupItem> {
    
    @Override
    protected boolean createKeys(List<SetupItem> toPopulate) {
        String sqlDepartments= "SELECT d FROM OrganizationUnits d";
        toPopulate.add(new SetupItem("Employee Departments", Children.create(new FactoryDepartments(sqlDepartments,true), true),"systems/tech247/util/icons/department.png"));
        toPopulate.add(new SetupItem("Company Structure", Children.create(new FactoryCategoryTypes(), true),"systems/tech247/util/icons/EmpCategory.png"));
        
        String sqlJob= "SELECT d FROM JobPositions d";
        toPopulate.add(new SetupItem("Job Positions", Children.create(new FactoryPositions(sqlJob, true), true),"systems/tech247/util/icons/position.png"));
        String sqlE= "SELECT d FROM TblEmployeeType d";
        toPopulate.add(new SetupItem("Employee Types", Children.create(new FactoryEmployeeTypes(sqlE, true), true),"systems/tech247/util/icons/position.png"));
        String sqlCountries= "SELECT d FROM Countries d";
        toPopulate.add(new SetupItem("Countries",Children.create(new FactoryCountry(sqlCountries,true), true),"systems/tech247/util/icons/earth.png"));
        String sqlNationalities= "SELECT d FROM Nationalities d";
        toPopulate.add(new SetupItem("Nationalities",Children.create(new FactoryNations(sqlNationalities, true), true),"systems/tech247/util/icons/earth.png"));
        String sqlTribes = "SELECT d FROM Tribes d";
        toPopulate.add(new SetupItem("Tribes",Children.create(new FactoryTribes(sqlTribes, true), true),"systems/tech247/util/icons/tribe.png"));
        String sqlReligions = "SELECT d FROM Religions d";
        toPopulate.add(new SetupItem("Religions",Children.create(new FactoryReligions(sqlReligions, true), true)));
        toPopulate.add(new SetupItem("Banks",Children.create(new FactoryBanks(), true),"systems/tech247/util/icons/bank.png"));
        toPopulate.add(new SetupItem("Currencies",Children.create(new FactoryCurrency(), true),"systems/tech247/util/icons/Currency.png"));
        toPopulate.add(new SetupItem("Locations",Children.create(new FactoryLocation(), true)));
        QueryContactTypes query = new QueryContactTypes();
        toPopulate.add(new SetupItem("Contact Types",Children.create(new FactoryContactTypes(query, Boolean.TRUE), true)));
        QueryContractTypes query2 = new QueryContractTypes();
        query2.setSqlString("SELECT p FROM PdContractTypes p");
        
        toPopulate.add(new SetupItem("Contract Types",Children.create(new FactoryContractTypes(query2, Boolean.TRUE), true)));


//toPopulate.add(new SetupItem("Match Departments TO SUN"));
        /*toPopulate.add(new SetupItem("Salary Calculator",new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TopComponent salaryTc = WindowManager.getDefault().findTopComponent("SalaryCalculatorTopComponent");
                salaryTc.open();
            }
        }));*/
        
        
        return true;
    }
    
    @Override
    protected Node createNodeForKey(SetupItem key) {
        
        Node node =  null;
        try {
            
            node = new NodeSetupItem(key);
            
        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
        return node;
    }
}
