package com.github.majidshoorabi.saftonfun;

import com.github.majidshoorabi.saftonfun.model.ItemContainer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author majid.shoorabi
 * @created 2022-07-April
 * @project saftonfun
 */

public class Main {

    public static final String expresion = "//button[text()='ء' or text()='أ'  or  text()='ئ' or  text()='ؤ' or  text()='ا' or  text()='ب' or  text()='پ' or  text()='ت' or  text()='ث' or  text()='ج' or  text()='چ' or  text()='ح' or  text()='خ' or  text()='د' or  text()='ذ' or  text()='ر' or  text()='ز' or  text()='ژ' or  text()='س' or  text()='ش' or  text()='ص' or  text()='ض' or  text()='ط'  or  text()='ظ'  or  text()='ع'  or  text()='غ'  or  text()='ف'  or  text()='ق'  or  text()='ک'  or  text()='گ'  or  text()='ل'  or text()='م' or  text()='ن'  or  text()='و'  or  text()='ه'  or  text()='ی' ]";

    public static void main(String[] args) throws InterruptedException {


        WordsLoader loader = new WordsLoader();
        List<String> words = new LinkedList<>();
        try {
            words = loader.readFile();
        } catch (IOException e) {
            System.out.println("Error in read words from file!");
            return;
        }

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        // step1
        // driver.get("file:///home/majid/Downloads/New%20Folder%20(1)/page.html");
        driver.get("http://fun.kidzy.land/");
        driver.manage().window().maximize();
        Thread.sleep(20000);

        // step2
        driver.findElement(By.linkText("شروع بازی نوستالژی")).click();
        Thread.sleep(20000);

        // step3
        WebElement usernameInput = driver.findElement(By.xpath("//input[@type='text']"));
        usernameInput.sendKeys(args[0]);

        WebElement passwordInput = driver.findElement(By.xpath("//input[@type='password']"));
        passwordInput.sendKeys(args[1]);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        Thread.sleep(20000);

        //step 4
        List<WebElement> elements = driver.findElements(By.xpath(expresion));

        WebElement[][] table = new WebElement[10][10];

        for (int index = 0; index < elements.size(); index++) {
            if (index < 10)
                table[0][index] = elements.get(index);
            else if (index < 20)
                table[1][index - 10] = elements.get(index);
            else if (index < 30)
                table[2][index - 20] = elements.get(index);
            else if (index < 40)
                table[3][index - 30] = elements.get(index);
            else if (index < 50)
                table[4][index - 40] = elements.get(index);
            else if (index < 60)
                table[5][index - 50] = elements.get(index);
            else if (index < 70)
                table[6][index - 60] = elements.get(index);
            else if (index < 80)
                table[7][index - 70] = elements.get(index);
            else if (index < 90)
                table[8][index - 80] = elements.get(index);
            else if (index < 100)
                table[9][index - 90] = elements.get(index);
        }
        System.out.println(table);

        WebElement submitBtn = driver.findElement(By.xpath("//button[text()='ثبت']"));


        Finder finder = new Finder();
        for (String w : words) {
            List<List<ItemContainer>> lists = finder.find(w, table);

            for (List<ItemContainer> list : lists) {
                for (ItemContainer itemContainer : list) {
                    itemContainer.getWebElement().click();
                }
                submitBtn.click();
                Thread.sleep(20000);
                try {
                    driver.findElement(By.xpath("//button[text()='بستن']")).click();
                    Thread.sleep(5000);
                } catch (Exception ex) {
                    System.out.println("Exception occurred.");
                }
            }
        }

        System.out.println("*** Finish");

        driver.close();
        driver.quit();

    }


}
