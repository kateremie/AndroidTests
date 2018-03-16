package NGParallelTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ParallelTestExecution {
    
    String port;
    String device;

    @Parameters({"server_port", "device"})
    public ParallelTestExecution(String port, String device) {
        this.port = port;
        this.device = device;
    }

    private static AppiumDriver<MobileElement> driver;
    private Methods App;

    DesiredCapabilities capabilities = new DesiredCapabilities();

    void ParallelSetup() {
        capabilities.setCapability("deviceName", device);
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "ru.averia.tracker");
        capabilities.setCapability("appActivity", "ru.averia.tracker.ui.activities.SplashActivity");

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:" + port + "/wd/hub"), capabilities);
            //Thread.sleep(5000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(Constants.Timeout, TimeUnit.SECONDS);

        App = new Methods(driver);

    }

    public void Quit() {
        driver.quit();
    }

    @BeforeTest(alwaysRun = true)
    void BeforeSuite() {
        ParallelSetup();
    }
    @AfterTest
    void AfterSuite() {
        Quit();
    }

    @Test
    void TestRegister() {
        App.SplashScreen();
        App.Register();
    }

}