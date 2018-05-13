import Tools.SpreadsheetData;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DriveDataBeginning {
    private static WebDriver driver;

    private String height;
    private String weight;
    private String bmi;
    private String bmiCategory;
    private String error;

    @Parameterized.Parameters
    public static Collection testData() throws Exception {
        InputStream spreadsheet = new FileInputStream("./src/test/resources/TestData/Data.xlsx");
        return new SpreadsheetData(spreadsheet).getData();
    }

    public DriveDataBeginning(String height, String weight, String bmi,
                              String bmiCategory, String error) {
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bmiCategory = bmiCategory;
        this.error = error;
    }

    @BeforeClass
    public static void setUp() throws Exception {
// Create a new instance of the Firefox driver
        driver = new FirefoxDriver();
        driver.get("http://cookbook.seleniumacademy.com/bmicalculator.html");
    }

    @Test
    public void testBMICalculator() throws Exception {
// Get the Height element and set the value using height variable
        WebElement heightField = driver.findElement(By.name("heightCMS"));
        heightField.clear();
        heightField.sendKeys(height);
// Get the Weight element and set the value using weight variable
        WebElement weightField = driver.findElement(By.name("weightKg"));
        weightField.clear();
        weightField.sendKeys(weight);
// Click on Calculate Button
        WebElement calculateButton = driver.findElement(By.id("Calculate"));
        calculateButton.click();
// Get the Bmi element and verify its value using bmi variable
        WebElement bmiLabel = driver.findElement(By.name("bmi"));
        assertEquals(bmi, bmiLabel.getAttribute("value"));
// Get the Bmi Category element and verify its value using
// bmiCategory variable
        WebElement bmiCategoryLabel = driver.findElement(By.name("bmi_category"));
        assertEquals(bmiCategory, bmiCategoryLabel.getAttribute("value"));
    }

    @AfterClass
    public static void tearDown() throws Exception {
// Close the browser
        driver.quit();
    }
}
