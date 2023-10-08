package pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qaBase.BasePage;
import qaUtils.SeleniumUtils;

import java.util.ArrayList;
import java.util.List;

public class DynamicTableHandlingPage extends BasePage {

    SeleniumUtils utils = new SeleniumUtils();

    public DynamicTableHandlingPage() {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "addNewRecordButton")
    WebElement addNewRecBtn;

    @FindBy(id = "firstName")
    WebElement firstNameField;

    @FindBy(id = "lastName")
    WebElement lastNameField;

    @FindBy(id = "userEmail")
    WebElement userEmailField;

    @FindBy(id = "age")
    WebElement ageField;

    @FindBy(id = "salary")
    WebElement salaryField;

    @FindBy(id = "department")
    WebElement deptField;

    @FindBy(id = "submit")
    WebElement submitBtn;


    public void insertNewRecord(String fName, String lName, String age, String email, String salary, String dept){
        addNewRecBtn.click();
        utils.setText(firstNameField,fName);
        utils.setText(lastNameField,lName);
        utils.setText(userEmailField,email);
        utils.setText(ageField,age);
        utils.setText(salaryField,salary);
        utils.setText(deptField,dept);
        submitBtn.click();
    }

    public int getSizeOfTable() {
        int sizeOfTableContents = 0;
        int n = 1;
        for(int i = 1; i <= n; i++) {
            try {
                WebElement fNameField = driver.findElement(By.xpath("//div[@class='rt-tr-group']["+i+"]//div[@role='gridcell'][1]/span[text()]"));
                break;
            } catch (NoSuchElementException e) {
                sizeOfTableContents++;
                n++;
            }
        }
        System.out.println(sizeOfTableContents);
        return sizeOfTableContents;
    }

    public List<String> getFirstNameList() {
        List<String> fNameList = new ArrayList<>();
        for(int i = 1; i <= getSizeOfTable(); i++) {
            WebElement fName = driver.findElement(By.xpath("//div[@class='rt-tbody']//div[@class='rt-tr-group']["+i+"]//div[@role='gridcell'][1]"));
            fNameList.add(fName.getText());
        }
        return fNameList;
    }

    public void verifyTheUserDetailsInTheTable() {

    }
}
