package com.methods;

import io.appium.java_client.*;
import org.apache.log4j.*;
import org.openqa.selenium.*;

import java.util.*;

import static com.vars.consts.*;
import static com.vars.vars.*;

public class Common {

    public AppiumDriver driver;

    Logger logger = Logger.getLogger(Common.class);

    public Common(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void androidAllowAccess() {
        try {
            driver.findElementById("com.android.packageinstaller:id/permission_allow_button").click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("No Permissions requested");
        }

    }

    public void sleep (Integer seconds) {

        try {
            Thread.sleep(1000 * seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void swipeUp() {

        logger.info("Swipe Up");
        int starty = (int) (screensize.height * 0.50);
        int endy = (int) (screensize.height * 0.20);
        int startx = screensize.width / 2;
        driver.swipe(startx,starty,startx,endy,300);
        driver.swipe(startx,starty,startx,endy,300);
        sleep(2);

    }

    public void swipeDown() {

        logger.info("Swipe Down");
        int starty = (int) (screensize.height * 0.20);
        int endy = (int) (screensize.height * 0.70);
        int startx = screensize.width / 2;
        driver.swipe(startx,starty,startx,endy,300);
        driver.swipe(startx,starty,startx,endy,300);
        sleep(2);

    }

    public void gotoMainScreen(String device) {

        logger.info(device + ": GOTO Main Screen");
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_pet").click();

    }

    public void gotoMapScreen(String device) {

        logger.info(device + ": GOTO Map Screen");
        driver.findElementById("ru.averia.tracker:id/maim_menu_action_map").click();

    }

    public void gotoProfileScreen(String device) {

        logger.info(device + ": GOTO Profile Screen");
        driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();

    }

    public void ScreensShuffle() {

        for(int i=0; i<2; i++) {
            driver.findElementById("ru.averia.tracker:id/maim_menu_action_map").click();
            sleep(1);
            driver.findElementById("ru.averia.tracker:id/main_menu_action_profile").click();
            sleep(1);
            swipeUp();
            swipeDown();
            driver.findElementById("ru.averia.tracker:id/maim_menu_action_pet").click();
            sleep(1);
            swipeUp();
            swipeDown();
        }
    }

    public void phonePhoto() {
        androidAllowAccess();
        switch (devicename) {

            case (phone_sony_xperia):
                //fkn experia does not want to focus an image
                logger.info("Sony Xperia X Perfomance photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]").click();
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.RelativeLayout").click();

                break;

            case (phone_asuszenpad):
                logger.info("Asus Zenpad photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.View/android.widget.ListView/android.widget.LinearLayout[1]").click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
            case (phone_htc):
                List<MobileElement> choiseslist_htc = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist_htc);
                choiseslist_htc.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
            case (phone_samsung_j1):
                logger.info("Samsung J1 photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/com.android.internal.widget.ResolverDrawerLayout/android.widget.GridView/android.widget.LinearLayout[1]/android.widget.ImageView").click();
                driver.findElementByAccessibilityId("MENUID_SHUTTER").click();
                driver.findElementById("com.sec.android.app.camera:id/okay").click();

                break;
            case (phone_samsung_edge):
                logger.info("Samsung Edge photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ScrollView/android.widget.LinearLayout/com.android.internal.widget.ViewPager/android.widget.LinearLayout/android.widget.GridView/android.widget.LinearLayout[1]").click();
                sleep(5);
                driver.findElementByXPath("(//GLButton[@content-desc=\"NONE\"])[3]").click();
                driver.findElementById("com.sec.android.app.camera:id/okay").click();

                break;
            case (phone_lg):
                logger.info("LG photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout/android.widget.LinearLayout[2]").click();
                sleep(2);
                (new TouchAction(driver)).tap(109, 203).perform();
                sleep(2);
                (new TouchAction(driver)).tap(109, 203).perform();
                sleep(1);

                break;
            case (phone_meizu_n5):
                List<MobileElement> choiseslist_meizu_n5 = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist_meizu_n5);
                choiseslist_meizu_n5.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
            case (phone_xiomi_x4_note):
                logger.info("Xiomi X4 Note photo sequence applied");
                List<MobileElement> choiseslist_xiomi_x4_note = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist_xiomi_x4_note);
                choiseslist_xiomi_x4_note.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
            case (phone_honor_c3):
                logger.info("Honor 3C photo sequence applied");
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.view.ViewPager/android.widget.GridView/android.widget.LinearLayout[1]").click();
                driver.findElementByAccessibilityId("Сфотографировать").click();
                driver.findElementByAccessibilityId("Готово").click();

                break;
            case (phone_nexus_5):
                logger.info("Nexus 5 photo sequence applied");
                androidAllowAccess();
                driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout").click();
                driver.findElementByAccessibilityId("Затвор").click();
                driver.findElementByAccessibilityId("Готово").click();

                break;
            default:
                logger.info("Default photo action applied");
                sleep(2);
                androidAllowAccess();
                List<MobileElement> choiseslist = driver.findElements(By.className("android.widget.LinearLayout"));
                logger.info("List: " + choiseslist);
                choiseslist.get(1).click();
                driver.findElement(By.id("com.asus.camera:id/button_capture")).click();
                driver.findElement(By.id("com.asus.camera:id/button_used")).click();

                break;
        }
    }

    public void swipeUpToElementId(String elementId) {

        boolean isFoundTheElement = driver.findElements(By.id(elementId)).size() > 0;
        while (!isFoundTheElement){
            swipeUp();
            isFoundTheElement  = driver.findElements(By.id(elementId)).size() > 0;
        }

    }

    public void swipeDownToElementId(String elementId){

        boolean isFoundTheElement = driver.findElements(By.id(elementId)).size() > 0;
        while (!isFoundTheElement){
            swipeDown();
            isFoundTheElement  = driver.findElements(By.id(elementId)).size() > 0;
        }

    }

}
