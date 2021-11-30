import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestManualsOnSouceLabs {

    private WebDriver driver;
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    private ThreadLocal<String> idSession = new ThreadLocal<String>();


    protected WebDriver setUp() throws Exception {
        DesiredCapabilities capabillities = DesiredCapabilities.chrome();
        capabillities.setCapability("version", "73.0");
        capabillities.setCapability("platform", Platform.WIN10);
        capabillities.setCapability("name", "Basic Test");
        WebDriver dr = new RemoteWebDriver(
                new URL("https://nadyafed:a547d256-21e5-4676-8553-d31d61e60f62@ondemand.saucelabs.com:443/wd/hub"),
                capabillities);
        webDriver.set(dr);

        return webDriver.get();

    }

    @Test
    public void test1() throws Exception {

       driver=setUp();
        ArrayList<String> xpath = new ArrayList<String>();

        driver.get("https://hp.myway.com/productmanualsfinder/ttab02chr/index.html");
        driver.findElement(By.xpath("//*[@id=\'manualsbrowsebycategory\']")).click();
        Thread.sleep(5000);

        List<WebElement> listOfCategories2 =  driver.findElements(By.xpath("//li[@id]"));
        System.out.println("Found " + listOfCategories2.size() + " categories.");
        List<WebElement> listOfCategories = new ArrayList<WebElement>();
        listOfCategories.addAll(listOfCategories2);

        for (int i = 0; i < listOfCategories.size(); i++) {

            String categoryName = listOfCategories.get(i).getText(); //*[@id="Camera Lens & Accessories"]
            System.out.println("Working with category: " + i + " "  + " of " + listOfCategories.size() + " " + categoryName);
            String a = "//*[@id=\'" + categoryName + "']";
            System.out.println(a);
            xpath.add(a);

        }

        for (int i = 227; i < xpath.size(); i++) {
            String a = " ";
            try {
                a = xpath.get(i);
                driver.findElement(By.xpath(a)).click();
                Thread.sleep(10000);
                CollectManualsFromCategory(a,driver,"D:test1.txt");
                driver.findElement(By.xpath("//span[@class='breadcrumb-nav-tab']")).click();

                Thread.sleep(2000);
            }catch (Exception e) {
                System.out.println("Error with processing xpath: " + a);
                e.getMessage();
            }
        }
    }

    @Test
    public void test2() throws Exception {
        driver=setUp();
        ArrayList<String> xpath = new ArrayList<String>();

        driver.get("https://hp.myway.com/productmanualsfinder/ttab02chr/index.html");
        driver.findElement(By.xpath("//*[@id=\'manualsbrowsebycategory\']")).click();
        Thread.sleep(5000);

        List<WebElement> listOfCategories2 =  driver.findElements(By.xpath("//li[@id]"));
        System.out.println("Found " + listOfCategories2.size() + " categories.");
        List<WebElement> listOfCategories = new ArrayList<WebElement>();
        listOfCategories.addAll(listOfCategories2);

        for (int i = 0; i < listOfCategories.size(); i++) {

            String categoryName = listOfCategories.get(i).getText(); //*[@id="Camera Lens & Accessories"]
            System.out.println("Working with category: " + i + " "  + " of " + listOfCategories.size() + " " + categoryName);
            String a = "//*[@id=\'" + categoryName + "']";
            System.out.println(a);
            xpath.add(a);

        }

        for (int i = 225; i < 226; i++) {
            String a = " ";
            try {
                a = xpath.get(i);
                driver.findElement(By.xpath(a)).click();
                Thread.sleep(10000);
                CollectManualsFromCategory(a,driver,"D:test2.txt");
                driver.findElement(By.xpath("//span[@class='breadcrumb-nav-tab']")).click();
                Thread.sleep(2000);
            }catch (Exception e) {
                System.out.println("Error with processing xpath: " + a);
                e.getMessage();
            }
        }
    }

    @Test
    public void test3() throws Exception {
        driver=setUp();
        ArrayList<String> xpath = new ArrayList<String>();

        driver.get("https://hp.myway.com/productmanualsfinder/ttab02chr/index.html");
        driver.findElement(By.xpath("//*[@id=\'manualsbrowsebycategory\']")).click();
        Thread.sleep(5000);

        List<WebElement> listOfCategories2 =  driver.findElements(By.xpath("//li[@id]"));
        System.out.println("Found " + listOfCategories2.size() + " categories.");
        List<WebElement> listOfCategories = new ArrayList<WebElement>();
        listOfCategories.addAll(listOfCategories2);

        for (int i = 0; i < listOfCategories.size(); i++) {

            String categoryName = listOfCategories.get(i).getText(); //*[@id="Camera Lens & Accessories"]
            System.out.println("Working with category: " + i + " "  + " of " + listOfCategories.size() + " " + categoryName);
            String a = "//*[@id=\'" + categoryName + "']";
            System.out.println(a);
            xpath.add(a);

        }

        for (int i = 222; i < 224; i++) {
            String a = " ";
            try {
                a = xpath.get(i);
                driver.findElement(By.xpath(a)).click();
                Thread.sleep(10000);
                CollectManualsFromCategory(a,driver,"D:test3.txt");
                driver.findElement(By.xpath("//span[@class='breadcrumb-nav-tab']")).click();
                Thread.sleep(2000);
            }catch (Exception e) {
                System.out.println("Error with processing xpath: " + a);
                e.getMessage();
            }
        }
    }


    public void CollectManualsFromCategory (String category,WebDriver driver,String path)  {
        ArrayList <String> manualUrls = new ArrayList<String>();

        List <WebElement> manuals2 = driver.findElements(By.xpath("//h1[@class='manuals-info-title']"));
        List <WebElement> manuals = new ArrayList<WebElement>();
        manuals.addAll(manuals2);
        System.out.println("For category '"+category+"'"+ " found " + manuals.size() + " manuals.");
        String manualsUrl = " ";
        for (WebElement element: manuals) {
            try {
                String manual = element.getText();
                element.click();
                ArrayList tabs2 = new ArrayList(driver.getWindowHandles());
                String firsttab = tabs2.get(0).toString();
                String secondtab = tabs2.get(1).toString();
                driver.switchTo().window(secondtab);
                manualsUrl = driver.getCurrentUrl();
                System.out.println(manualsUrl);
                getResponseCode(manualsUrl, category, manual ,path);
                manualUrls.add(category + "  " + manualsUrl);
                driver.close();
                driver.switchTo().window(firsttab);
                Thread.sleep(2000);
            }catch (Exception e){
                System.out.println("Error while processing '"+category+"' " + manualsUrl);
            }

        }
    }

    public static void getResponseCode(String urlString, String category, String manual,String path) throws IOException {



        FileWriter fw = new FileWriter(path, true);




        try {

            URL u = new URL(urlString);
            HttpURLConnection huc = (HttpURLConnection) u.openConnection();
            huc.setRequestMethod("GET");
            huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
            huc.connect();
            if (huc.getResponseCode() > 399) {
                category  = category.substring(category.indexOf("'")+1);
                category=category.substring(0,category.indexOf("'"));
                System.out.println(urlString + ", response code = " + huc.getResponseCode() + ", manual: " + manual + ", category: " + category);
                fw.write(urlString + ", response code = " + huc.getResponseCode() + ", manual: " + manual + ", category: " + category);
                fw.write("\n");
            }

        } catch (IOException e) {
            System.out.println("failed to get a response code: " + urlString);
            fw.write("failed to get a response code: " + urlString);
            fw.write("\n");
        }
        fw.close();

    }

}
