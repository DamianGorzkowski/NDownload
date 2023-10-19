package narutodownloadsteps;

import pages.EpisodePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.time.Duration;


public class NarutoDownloadSteps {
    private WebDriver driver;
    private EpisodePage episodePage;
    private WebDriverWait wait;
    private Actions actions;


//    @Given("Launch browser")
    @Given("Launch browser")
    public void launchBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        episodePage = new EpisodePage(driver);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

//    @And("Navigate to url {string}")
    @And("Navigate to url {string}")
    public void navigateToUrl(String url) {
        driver.get(url);
    }

//    @And("Download link clicked with file {string}")
    @And("Download link clicked with file {string}")
    public void downloadLinkClicked(String fileName) throws InterruptedException {
        episodePage.setCookiedaccept();
        episodePage.chooseDownloadLink();
        String currentURL = driver.getCurrentUrl();
        ((JavascriptExecutor) driver).executeScript("window.open();");
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        driver.get(currentURL);


        WebElement imgElement = driver.findElement(By.cssSelector("#tresc_lewa > center > iframe"));
        String srcValue = imgElement.getAttribute("src");
        String modifiedUrl = srcValue.replace("/embed-", "/");
        driver.get(modifiedUrl);

        WebElement freeDownloadButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("method_free")));
        freeDownloadButton.click();
        Thread.sleep(35000);
        WebElement targetElement = driver.findElement(By.id("downloadbtn"));
        actions.moveByOffset(1186, 801).click().perform();
        String currentHandle = driver.getWindowHandle();
        driver.switchTo().window(currentHandle);

        boolean clicked = false;
        for (int i = 0; i < 6 && !clicked; i++) {
            try {
                targetElement.click();
                clicked = true;
            } catch (org.openqa.selenium.ElementClickInterceptedException e) {
                actions.click().perform();
                driver.switchTo().window(currentHandle);
            }
        }

        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/" + fileName);
        File downloadDir = new File("C:/Users/A/Downloads");
        boolean fileExists = false;
        for (int i = 0; i < 60; i++) {
            File[] files = downloadDir.listFiles();

            for (File file : files) {
                if (pathMatcher.matches(Paths.get(file.toURI()))) {
                    fileExists = true;
                    break;
                }
            }

            if (fileExists) {
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(2000);
        driver.quit();
    }
}