package com.tests;

/* Smoke Tests */

import com.methods.*;
import com.utils.*;
import com.var.*;
import io.appium.java_client.*;
import io.appium.java_client.android.*;
import org.apache.log4j.*;
import org.openqa.selenium.remote.*;
import org.testng.*;
import org.testng.annotations.*;

import java.net.*;
import java.util.concurrent.*;

public class TestingTests {

    Logger logger = Logger.getLogger("AndroidTestLogger");

    String port;
    public String device;
    public String testName;

    public Start_screen start;
    public Screenshot screenshot;
    public GetDeviceInfo deviceinfo;
    public Common common;
    public Profile_screen profile_screen;
    public Main_screen main_screen;
    public Pet_screen pet_screen;
    public Socials social;
    static AppiumDriver<MobileElement> driver;

    DesiredCapabilities caps = new DesiredCapabilities();

    @Parameters({"server_port","device"})
    public TestingTests(@Optional("4731") String port, @Optional("default") String device)
    {
        this.port = port;
        this.device = device;
    }

    public void StartUp()
    {

        logger.info(device + ": Starting app");

        caps.setCapability("deviceName", device);
        caps.setCapability("platformName", "Android");
        caps.setCapability("appPackage", "ru.averia.tracker");
        caps.setCapability("appActivity", "ru.averia.tracker.ui.activities.SplashActivity");
        caps.setCapability("udid",consts.phone_lg);
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("gpsEnabled", true);
        caps.setCapability("noReset", true);
        caps.setCapability("fullReset", false);

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:" + port + "/wd/hub"), caps);
            //Thread.sleep(1000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //Adding all needed methods and utils
        start = new Start_screen(driver);
        screenshot = new Screenshot(driver);
        deviceinfo = new GetDeviceInfo(driver);
        common = new Common(driver);
        profile_screen = new Profile_screen(driver);
        main_screen = new Main_screen(driver);
        pet_screen = new Pet_screen(driver);
        social = new Socials(driver);

        driver.manage().timeouts().implicitlyWait(consts.Timeout, TimeUnit.SECONDS);

        logger.info(device + ": App launched");

    }

    public void Exit() {

        logger.info(device + ": Closing app");
        driver.quit();

    }

    @BeforeTest(alwaysRun = true)
    void BeforeSuite()
    {

        logger.info("-----");
        logger.info(device+": "+"Initial Settings and App Startup");

        StartUp();

        logger.info(device+": "+"Settings Applied");

        deviceinfo.getDeviceInfo(device);

    }

    @AfterMethod
    void afterMethod(ITestResult result)
    {

        testName = result.getName();

        try
        {
            if(result.getStatus() == ITestResult.SUCCESS)
            {
                logger.info(device + ": Passed " + testName);
            }

            else if(result.getStatus() == ITestResult.FAILURE)
            {
                logger.info(device + ": Failed " + testName);
                screenshot.captureScreenShots(device, testName);
            }

            else if(result.getStatus() == ITestResult.SKIP )
            {
                logger.info(device + ": Skiped " + testName);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
/*
    @Test
        void LoginExistingUser()
        {

            start.SplashScreen();
            start.Login_old(device);

        }
*/

    @Test
    void AddTempPet() {

        common.gotoProfileScreen(device);
        pet_screen.addPetProfileScreen(device);
        pet_screen.deletePetProfileScreen(device);

    }
}
