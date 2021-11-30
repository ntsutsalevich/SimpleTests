import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

//import java.util.NoSuchElementException;

public class TestLinks {

    WebDriver driver;
    WebDriverWait wait;


    @BeforeTest
    public void startDriver() {
        System.setProperty(
                "webdriver.chrome.driver",
                "D:/Test/chromedriver.exe"); //F:\ChromeDriver

        driver = new ChromeDriver();
        driver.get("https://www.tut.by");


    }


    // @AfterTest
    public void closeDriver() {
        driver.close();
    }

    //"D:/gamesTest/gamesAppJSONPCallback.txt"

    // extracts Game Names from the .txt file with JSON and puts the names into a 'set'
    public Set<String> createListGames(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));


        Set<String> set=new TreeSet<>();
        String s = null;
        while ((s = br.readLine()) != null) {
            if (s.contains("\"name\"")) {
                String[] s1 = s.split("\": \"");

                s1[1] = s1[1].replaceAll("[^-\\da-zA-Z &]", "");
                set.add(s1[1]);

            }
        }
        br.close();
        return set;
    }


    @Test
    public void sortFiles() throws IOException {
        ArrayList<String> withoutToolbar = new ArrayList<String>();
        ArrayList<String> withoutToolbar2 = new ArrayList<String>();
        String inputFile = "D:/Test/games2.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String str;
        while ((str = reader.readLine()) != null) {
            withoutToolbar.add(str);
        }

        for (int j = 0; j < withoutToolbar.size(); j++) {
            String[] a = withoutToolbar.get(j).split(" ");
            String b = a[0];
            withoutToolbar2.add(b);

        }
        ArrayList<String> allWTTlinks = new ArrayList<String>();
        String inputFile2 = "D:/Test/Links.txt";
        BufferedReader reader2 = new BufferedReader(new FileReader(inputFile2));
        String str2;
        while ((str2 = reader2.readLine()) != null) {
            allWTTlinks.add(str2);
        }

        ArrayList<String> sortedLinks = new ArrayList<String>();
        FileWriter fw4 = new FileWriter("D:/Test/With");

        for (int i = 0; i < withoutToolbar2.size(); i++) {
            if (allWTTlinks.contains(withoutToolbar2.get(i))) {
                allWTTlinks.remove(withoutToolbar2.get(i));
            }

        }


        for (int j = 0; j < allWTTlinks.size(); j++) {
            fw4.write(allWTTlinks.get(j) + "\n");
        }
        fw4.close();

    }

    @Test
    public void addLinesToLinks() throws IOException {

        ArrayList<String> pagelinks = new ArrayList<String>();
        String inputFile = "D:/Test/Cobrands.txt";
        //String inputFile = "D:/Test/Difficult-links.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String str;
        while ((str = reader.readLine()) != null) {
            pagelinks.add(str);
        }

        // FileWriter fw = new FileWriter("D:/Test/ProdREsults2");


        // driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        for (int i = 0; i < pagelinks.size(); i++) {
            String url = pagelinks.get(i) + "||";
            System.out.print(url);
        }
    }

    @Test
    public void startCheckLinks() throws IOException {

        ArrayList<String> pagelinks = new ArrayList<String>();
        String inputFile = "D:/Cobrands.txt";
        //String inputFile = "D:/Test/Difficult-links.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String str;
        while ((str = reader.readLine()) != null) {
            pagelinks.add(str);
        }

        FileWriter fw = new FileWriter("D:/Test/ProdREsults2");
        FileWriter fw2 = new FileWriter("D:/Test/GoodWTTs");
        FileWriter fw3 = new FileWriter("D:/Test/WTTs_without_toolbar");

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        for (int i = 0; i < pagelinks.size(); i++) {
            String url = pagelinks.get(i);
            driver.manage().window().maximize();
            driver.get(url);
            System.out.println(i + ". " + url);


            //driver.findElement(By.cssSelector("div.chrome-assist-background-area"));


            //close all prompts and modals
            try {
                driver.findElement(By.cssSelector("i.icon-close")).click();

            } catch (NoSuchElementException e) {
            }
            try {
                driver.findElement(By.cssSelector("div.x-button")).click();

            } catch (NoSuchElementException e) {
            }
            try {
                driver.findElement(By.cssSelector("i.icon-close")).click();

            } catch (NoSuchElementException e) {

            }
            try {
                driver.findElement(By.cssSelector("div.generic-modal-content"));
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_ESCAPE);
                robot.keyPress(KeyEvent.VK_ESCAPE);

            } catch (AWTException e) {
                e.printStackTrace();
            } catch (NoSuchElementException e) {

            } catch (ElementClickInterceptedException e) {
                e.printStackTrace();
            } catch (ElementNotInteractableException e) {

            }


            //check that toolbar exists
//            try {
//                WebElement element = driver.findElement(By.cssSelector("div.toolbar-container"));
//            } catch (NoSuchElementException exception) {
//                fw3.write(url + " No toolbar on the WTT" + "\n");
//            }
//            fw2.write(url + "\n");
            //a[@title='Wetter']"


            //check that weather button exists in toolbar
            //div.toolbar-button.weather-toolbar-button
            //img[@src='https://ak.staticimgfarm.com/images/webtooltab/assets/weather/MostlyCloudy.png']
            //img[@src='//ak.staticimgfarm.com/images/webtooltab/widgets/weatherblink/com.mindspark.weatherblink.unbranded-en/images/weather/mostlycloudy.png']

            try {
                // WebElement weather_button = driver.findElement(By.xpath("//div[@title='37°F Minsk, Minsk City, Belarus']"));
                WebElement weather_button = driver.findElement(By.xpath("//div[@class='toolbar-button weather-toolbar-button']"));
                weather_button.click();

                //check that app modal opens by click
                try {
                    driver.findElement(By.id("weatherAppModal"));
                    continue;
                } catch (NoSuchElementException exception4) {
                    fw.write(url + " WeatherAppModal does not appear after click" + "\n");
                    continue;
                }

            } catch (NoSuchElementException exception) {
                //   fw.write(url + " No toolbar button in the toolbar" + "\n");
            } catch (ElementClickInterceptedException e) {
                fw.write(url + " Could not click because of the prompt" + "\n");
                continue;
            } catch (ElementNotInteractableException e) {
                fw.write(url + " Could not click because of the prompt" + "\n");
                continue;
            }


            //2. если кнопка есть, но в ней линка
            // check that weather app button does not contain a href
            ////img[@src='//ak.staticimgfarm.com/images/webtooltab/widgets/weatherblink/com.mindspark.weatherblink.unbranded-en/images/weather/MainIcon.png']
            try {
                checkLanguage("//img[@src='//ak.staticimgfarm.com/images/webtooltab/widgets/weatherblink/com.mindspark.weatherblink.unbranded-en/images/weather/MainIcon.png']", fw, url);
                continue;
            } catch (NoSuchElementException e) {
            }

            //3. если кнопки нету
            fw.write(url + " No toolbar button in the toolbar" + "\n");

        }


        fw.close();
        fw2.close();
        fw3.close();
    }

    public void checkLanguage(String lang, FileWriter fw, String url) throws NoSuchElementException, IOException {

        String href = driver.findElement(By.xpath(lang)).getAttribute("alt");
        if (href.contains("Weather") || (href.contains("Wetter")) || href.contains("Clima") || href.contains("Météo") || href.contains("Tempo") || href.contains("Tiempo") || href.contains("Meteo") || href.contains("天気")) {

            fw.write(url + "  Href contains 'Weather' " + "\n");
        }


    }

    @Test
    public void testGamingWonderland() throws IOException {
        ArrayList<String> onlyNames = new ArrayList<String>();
        //ArrayList<String> withoutToolbar2 = new ArrayList<String>();
        String inputFile = "D:/Test/up3363";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String str;
        while ((str = reader.readLine()) != null) {
            onlyNames.add(str);
        }
        driver.get("https://ak.staticimgfarm.com/images/pagecms/1173/245/?env=test");
        driver.findElement(By.xpath("//*[@id=\"gamesAppButton\"]/i")).click();
        driver.findElement(By.xpath("//*[@id=\"gamesAppModal\"]"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String i : onlyNames) {

            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[5]/div/div[2]/div[2]/div/div/div[1]/div/input")).sendKeys(i);
            try {
                Thread.sleep(100);
                String result = driver.findElement(By.xpath("//*[@id=\"gamesAppModal\"]/div[2]/div/div/div[2]/div[1]/p")).getText();
                if (result.contains("No")) {
                    //System.out.println(result);
                    System.out.println(i);
                }
            } catch (NoSuchElementException e) {
                System.out.println("exception for " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[5]/div/div[2]/div[2]/div/div/div[1]/div/input")).clear();
        }
    }
    //  }

    @Test
    public void testActionClassicGames() throws IOException {
        ArrayList<String> onlyNames = new ArrayList<String>();
        ArrayList<String> withoutToolbar2 = new ArrayList<String>();
        String inputFile = "D:/Test/up3363";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String str;
        while ((str = reader.readLine()) != null) {
            onlyNames.add(str);
        }
        driver.get("https://ak.staticimgfarm.com/images/pagecms/20519/72/?env=test");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"appModalModal\"]/button/i")).click();
        driver.findElement(By.xpath("//*[@id=\"ProductSearchOnBoardingModalModal\"]/button/i")).click();
        driver.findElement(By.xpath("//*[@id=\"gamesAppButton\"]/i")).click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        driver.findElement(By.xpath("//*[@id=\"gamesAppModal\"]/div[2]/div/div/div[1]/div/input")).click();


        for (String i : onlyNames) {
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[5]/div/div[2]/div[2]/div/div/div[1]/div/input")).sendKeys(i);

            try {
                Thread.sleep(100);

                String result = driver.findElement(By.xpath("//*[@id=\"gamesAppModal\"]/div[2]/div/div/div[2]/div[1]/p")).getText();
                if (result.contains("No")) {
                    //System.out.println(result);
                    System.out.println(i);
                }
            } catch (NoSuchElementException e) {
                System.out.println("exception for " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.xpath("/html/body/div[1]/div/div/div[5]/div/div[2]/div[2]/div/div/div[1]/div/input")).clear();
        }
    }

  //  "https://www.gamingwonderland.com/search?q=gytgruigtil&go-search="
    @Test
    public void testGamingWonderlandPage(String fileNameRead,String fileNameWrite,String url) throws IOException {
        Set<String> listGames = createListGames(fileNameRead);
        ArrayList<String> onlyNames = new ArrayList<String>();
        BufferedWriter fw = new BufferedWriter(new FileWriter(fileNameWrite));
        driver.get(url);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"search\"]")).clear();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (String i : listGames) {
            driver.findElement(By.xpath("//*[@id=\"search\"]")).sendKeys(i);
            driver.findElement(By.xpath("//*[@id=\"go-search\"]")).click();
            try {
                Thread.sleep(200);
                String result = driver.findElement(By.cssSelector("#page-container > div.box-middle.container > div > div > div.search-results > p > strong")).getText();
                if (!result.contains("Unfortunately")) {
                   fw.write(i+"\n");
                }
            } catch (NoSuchElementException e) {
                System.out.println("exception for " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.xpath("//*[@id=\"search\"]")).clear();
        }
    }

    @Test
    public void findAppJs() throws IOException, InterruptedException {
        ArrayList<String> allFile = new ArrayList<String>();
        String inputFile = "D:/Test/live product urls.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String str;
        while ((str = reader.readLine()) != null) {
            allFile.add(str);
        }

        for (String i : allFile) {
            String link = "view-source:" + i;
            driver.get(link);
            try {
                java.util.List<WebElement> elem = driver.findElements(By.xpath("//*[@target='_blank'][last()]"));
                elem.get(elem.size() - 1).click();
                //driver.findElement(By.xpath("//*[@target='_blank'][last()]")).click();
                ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(tabs2.get(1));
                String line = driver.getCurrentUrl();
                System.out.println(line);
                driver.close();
                driver.switchTo().window(tabs2.get(0));
            } catch (Exception e) {
                System.out.println(i + " какая-то ошибка");

            }

        }


    }

    @Test
    public void trimGregsFile() throws IOException {
        ArrayList<String> allFile = new ArrayList<String>();
        String inputFile = "D:/Test/up3644games.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String str;
        while ((str = reader.readLine()) != null) {
            allFile.add(str);
        }

        for (String i : allFile) {
            if (i.contains("\"name\": \"")) {
                System.out.println(i);
            }

        }
    }

    @Test
    public void readFile() throws IOException {
        ArrayList<String> allFile = new ArrayList<String>();
        String inputFile = "D:/Test/linksFor.txt";
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String str;
        while ((str = reader.readLine()) != null) {
            allFile.add(str);
        }

        for (String i : allFile) {
            driver.get(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
//}

