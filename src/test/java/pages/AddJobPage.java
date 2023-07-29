package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class AddJobPage extends CommonMethods {
    @FindBy(id="btnAdd")
    public WebElement addButton;
    @FindBy(id="jobTitle_jobTitle")
    public WebElement jobTitleF;
    @FindBy(id="jobTitle_jobDescription")
    public WebElement jobDescF;
    @FindBy(id="jobTitle_note")
    public WebElement jobNoteF;
    public AddJobPage(){
        PageFactory.initElements(driver,this);
    }
}
