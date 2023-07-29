package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    String fnFirstName;
    String fnMiddleName;
    String fnLastName;
    static String empId;
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
       // WebElement pimOption= driver.findElement(By.xpath("//a[@id='menu_pim_viewPimModule']"));
      //  pimOption.click();
        click(dashboardPage.pimOption);
    }
    @When("user clicks on add employee button")
    public void user_clicks_on_add_employee_button() {
      // WebElement addEmployeeBtn=driver.findElement(By.id("menu_pim_addEmployee"));
      // addEmployeeBtn.click();
        click(dashboardPage.addEmployeeButton);
    }
    @When("user enters firstname and lastname")
    public void user_enters_firstname_and_lastname() {
       // WebElement firstNameTextField = driver.findElement(By.id("firstName"));
       // firstNameTextField.sendKeys("aendro");
        sendText("aendro",addEmployeePage.firstNameField);

        //WebElement lastNameTextField = driver.findElement(By.id("lastName"));
       // lastNameTextField.sendKeys("farewell");
        sendText("farewell",addEmployeePage.lastNameField);
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        //WebElement saveButton = driver.findElement(By.id("btnSave"));
       // saveButton.click();
        click(addEmployeePage.saveButton);

    }
    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("employee added successfully");
    }
    @When("user enters {string} and {string} and {string}")
    public void user_enters_and_and(String firstName, String middleName, String lastName) {
       sendText(firstName,addEmployeePage.firstNameField);
       sendText(middleName,addEmployeePage.middleNameField);
       sendText(lastName,addEmployeePage.lastNameField);
    }
    @When("user enters {string} and {string} and {string} in data driven format")
    public void user_enters_and_and_in_data_driven_format(String firstName, String middleName, String lastName) {
        this.fnFirstName =firstName;
        this.fnMiddleName =middleName;
        this.fnLastName=lastName;
        sendText(firstName,addEmployeePage.firstNameField);
        sendText(middleName,addEmployeePage.middleNameField);
        sendText(lastName,addEmployeePage.lastNameField);
        empId=addEmployeePage.employeeIdField.getAttribute("value");
        System.out.println("Employee ID is ::"+empId);
    }
    @When("user enters firstname and middlename and verify employee has added")
    public void user_enters_firstname_and_middlename_and_verify_employee_has_added
            (io.cucumber.datatable.DataTable dataTable) {
        //we need list of maps to get multiple values
        List<Map<String,String>> employeeNames=dataTable.asMaps();
        for(Map<String,String> employee:employeeNames) {
            String firstNameValue = employee.get("firstName");
            String middleNameValue = employee.get("middleName");
            String lastNameValue = employee.get("lastName");
            sendText(firstNameValue, addEmployeePage.firstNameField);
            sendText(middleNameValue, addEmployeePage.middleNameField);
            sendText(lastNameValue, addEmployeePage.lastNameField);
            click(addEmployeePage.saveButton);
            //after adding one we will add another employee
            //after we are clicking on add emp in loop itself
            click(dashboardPage.addEmployeeButton);
        }
        }
        @When("user adds multiple employees using excel from {string} and verify it")
        public void user_adds_multiple_employees_using_excel_from_and_verify_it(String sheetName) throws InterruptedException {
        List<Map<String,String>> newEmployees=
            ExcelReader.read(sheetName, Constants.EXCEL_READER_PATH);
            Iterator<Map<String,String>> itr= newEmployees.iterator();
            while (itr.hasNext()){
                //in this map,we have data from every employee
               Map<String,String> mapNewEmp=itr.next();
               sendText(mapNewEmp.get("firstName"),addEmployeePage.firstNameField);
               sendText(mapNewEmp.get("middleName"),addEmployeePage.middleNameField);
               sendText(mapNewEmp.get("lastName"),addEmployeePage.lastNameField);
               sendText(mapNewEmp.get("photograph"),addEmployeePage.photograph);
               if(!addEmployeePage.checkBoxLocator.isSelected()){
                   click(addEmployeePage.checkBoxLocator);
               }
               sendText(mapNewEmp.get("username"),addEmployeePage.usernameTextFieldBox);
               sendText(mapNewEmp.get("password"),addEmployeePage.passwordTextFieldBox);
               sendText(mapNewEmp.get("confirmPassword"),addEmployeePage.confirmPasswordBox);

              String empIdValue= addEmployeePage.employeeIdField.getAttribute("value");
                Assert.assertTrue(addEmployeePage.saveButton.isDisplayed());
               click(addEmployeePage.saveButton);
               Thread.sleep(3000);
               //verify emp is added
                click(dashboardPage.empListOption);
                sendText(empIdValue, employeeSearchPage.idTextField);
                click(employeeSearchPage.searchButton);
                //print value from table row
                List<WebElement> rowData = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
                for(int i=0; i<rowData.size(); i++){
                    System.out.println("I am inside loop");

                    String rowText=rowData.get(i).getText();
                    System.out.println(rowText);
                    String expectedData=empIdValue+" "+mapNewEmp.get("firstName")+" "+
                            mapNewEmp.get("middleName")+" "+mapNewEmp.get("lastName");
                    Assert.assertEquals(rowText,expectedData);
                    //can also use below one
                    //Assert.assertTrue(expectedData.equals(rowText));
                }



//to add more employees we need to click on add emp button
               click(dashboardPage.addEmployeeButton);
        }
    }


    @Then("verify employee is stored in database")
    public void verifyEmployeeIsStoredInDatabase() {
       // String query="select emp_firstname,emp_middle_name,emp_lastname from hs_hr_employees where employee_id="+empId+";";
       String query= "select emp_firstName,emp_middle_name,emp_lastname from hs_hr_employees where employee_id=" +empId+ ";";
        System.out.println(query);
        List<Map<String,String>> mapList=DBUtils.fetch(query);
       Map<String,String> firstRow= mapList.get(0);
       String dbFirstName=firstRow.get("emp_firstName");
       String dbMiddleName=firstRow.get("emp_middle_Name");
       String dbLastName=firstRow.get("emp_lastName");
        System.out.println("FirstName "+dbFirstName);
        System.out.println("middleName "+dbMiddleName);
        System.out.println("lastname "+dbLastName);
       DOMConfigurator.configure("log4j.xml");
       Log.info("FirstName");
       Assert.assertEquals("FirstName from frontend does not match with firstname in database", fnFirstName,dbFirstName);
       //Assert.assertEquals("MiddleName from frontend does not match with middlename in database", fnMiddleName,dbMiddleName);
      // Assert.assertEquals("LastName from frontend does not match with lastname in database", fnLastName,dbLastName);


    }
        }