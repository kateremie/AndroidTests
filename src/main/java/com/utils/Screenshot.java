package com.utils;

import io.appium.java_client.*;
import io.appium.java_client.pagefactory.*;
import org.apache.commons.io.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.io.*;
import java.util.concurrent.*;

import static com.var.consts.folder_name;

public class Screenshot extends com.methods.Common {

    protected Logger logger;

    public Screenshot(AppiumDriver<MobileElement> driver)
    {
        super(driver);
        logger = Logger.getLogger("Screenshot");
        PageFactory.initElements(new AppiumFieldDecorator(driver, com.var.consts.Timeout, TimeUnit.SECONDS), this);
    }

    public void captureScreenShots(String device, String testName) throws IOException
    {

            File f= driver.getScreenshotAs(OutputType.FILE);
            String file_path = folder_name + device +"_"+ testName +"_"+ "fail.png";
            //create dir
            new File(folder_name).mkdir();
            FileUtils.copyFile(f, new File(file_path));

            logger.info(device + ": Screenshot saved as " + file_path);

    }

}

