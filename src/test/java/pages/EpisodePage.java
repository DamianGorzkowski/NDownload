package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EpisodePage {

    @FindBy(css = "body > div.app_gdpr--SPx19r > div.ulheJb0a > div.NMxiZ8JX > div > div.jA5ME2tC > button.qxOn2zvg.e1sXLPUy > div > b > span")
    private WebElement cookiedaccept;
    @FindBy(xpath = "/html/body/div[2]/div/div[6]/button[6]")
    private WebElement drugiDownload;
    @FindBy(css = ".lista_hover:nth-child(2) .odtwarzacz_link")
    private WebElement downloadLinkOnPlayer;


    public EpisodePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void chooseDownloadLink() {
        downloadLinkOnPlayer.click();
    }
    public void setCookiedaccept () {
        cookiedaccept.click();
    }
    public void setDrugiDownload () {
        drugiDownload.submit();
    }

}