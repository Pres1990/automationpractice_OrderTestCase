package com.filipWojiechowski;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class OrderTestCase {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pres\\Desktop\\selenium\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,2);
        driver.manage().window().maximize(); //maximize window
        driver.get("http://automationpractice.com/index.php"); // open website
        Actions action = new Actions(driver);

        action.moveToElement(driver.findElement(By.xpath("//a[@title='Women']"))).build().perform(); //move mouse to women tab
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='block_top_menu']/ul/li[1]/ul")));// timeout
        driver.findElement(By.xpath("//*[@id=\"block_top_menu\"]/ul/li[1]/ul/li[1]/ul/li[1]/a")).click(); // click on the t-shirt

        driver.findElement(By.xpath("//*[@id=\"layered_id_attribute_group_1\"]")).click(); // set size
        driver.findElement(By.xpath("//*[@id=\"layered_id_attribute_group_14\"]")).click(); // set color
        driver.findElement(By.xpath("//*[@id=\"layered_condition_new\"]")).click(); // condition
        Select selectSortBy = new Select(driver.findElement(By.xpath("//*[@id=\"selectProductSort\"]")));
        selectSortBy.selectByVisibleText("Product Name: A to Z"); // select sort By filter
        action.moveToElement(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li"))).build().perform(); //move over mouse to result of the search
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[1]")));

        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li/div/div[2]/div[2]/a[2]")).click(); // click move button
        driver.findElement(By.xpath("//*[@id=\"quantity_wanted_p\"]/a[2]")).click(); // add one to the Quantity
        Select sortSize = new Select(driver.findElement(By.xpath("//*[@id=\"group_1\"]")));
        sortSize.selectByValue("2"); //select size
        driver.findElement(By.xpath("//*[@id=\"color_14\"]")).click(); //select color
        driver.findElement(By.xpath("//*[@id=\"add_to_cart\"]/button")).click(); // add to the cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='layer_cart']/div[1]/div[2]"))); // timeout

        driver.findElement(By.xpath("//*[@id=\"layer_cart\"]/div[1]/div[2]/div[4]/a")).click(); // click on the proceed to checkout
        String actualPage = driver.getTitle(); // check current window
        String expectedPage = "Order - My Store";
        if(actualPage.equals(expectedPage)){
            System.out.println("Test successfull");

        }else {
            System.out.println("Test unsuccessful");
        }
        driver.close();






    }

}

