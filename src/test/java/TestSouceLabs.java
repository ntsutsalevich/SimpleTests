import com.sun.glass.events.KeyEvent;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TestSouceLabs extends TestCase {

    private RemoteWebDriver driver;


    @BeforeTest

    protected void setUp() throws Exception {
        DesiredCapabilities capabillities = DesiredCapabilities.chrome();
        capabillities.setCapability("version", "73.0");
        capabillities.setCapability("platform", Platform.WIN10);
        capabillities.setCapability("name", "Basic Test");
        driver = new RemoteWebDriver(
                new URL("https://nadyafed:a547d256-21e5-4676-8553-d31d61e60f62@ondemand.saucelabs.com:443/wd/hub"),
                capabillities);
        driver.setFileDetector(new LocalFileDetector());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void test1() throws InterruptedException, AWTException {
        driver.get("https://hp.myway.com/easyphotoedit/ttab02chr/index.html");

        ArrayList<String> list = new ArrayList<String>();

        //        list.add("apples.jpg");
//        list.add("watermelon.jpg");
//        list.add("vase.jpg");
//        list.add("camomiles.jpg");
        Thread.sleep(2000);
        WebElement editImageButton = driver.findElement(By.xpath("//*[@title='Edit Images']"));
        System.out.println(editImageButton.getAttribute("title"));
        editImageButton.click();

        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                uploadFile(list.get(i));
            } else {
                // Thread.sleep(2000);
                WebDriverWait wait = new WebDriverWait(driver, 2);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='import button']")))).click();
                Thread.sleep(1000);
                driver.findElement(By.xpath("//div[@class='yes-button button']")).click();
                uploadFile(list.get(i));
            }


        }
        driver.findElement(By.xpath("//div[@class='tab']")).click();
        boolean exists = driver.findElement(By.xpath("//div[@class='gallery-thumbnail-container']")).isDisplayed();
        if (exists) {
            System.out.println("Able to find the image in the gallery - TEST PASSED");
        } else {
            System.out.println("Unable to find the image in the gallery - TEST FAILED");
        }
        //close photo editor widget
        //driver.findElement(By.xpath("//div[@class='photo-editor-close']")).click();

    }

    public void uploadFile(String fileName) throws AWTException, InterruptedException {

        Thread.sleep(4000);
        WebElement uploadYourImage =
                driver.findElement(By.xpath("//button[@class='pesdk-react-splashScreen__row__button pesdk-react-button pesdk-react-button--uppercase']"));
        uploadYourImage.click();
        StringSelection ss = new StringSelection("D:\\" + fileName);


        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

//imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();
        robot.delay(250);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(50);
        robot.keyRelease(KeyEvent.VK_ENTER);
        ss = null;

        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='export button']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='yes-button button']")).click();
        driver.quit();
    }
}

