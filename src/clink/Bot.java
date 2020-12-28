/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clink;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author alvo
 */
public class Bot{
    static String minPrice,maxPrice,avPrice; 
    static boolean isLoggedIn, isClosed=false;
   //count number of available orders
   static int availableOrders(WebDriver driver){
       
        WebDriverWait waitForOrderCount=new WebDriverWait(driver,100);
        WebElement foundOrders=waitForOrderCount.until(ExpectedConditions.visibilityOfElementLocated(By.className("irbJoc")));
        String foundOrdersString=foundOrders.getText();
        String[] foundArray=foundOrdersString.split(" ");
        int numberOfOrders = Integer.parseInt(foundArray[1]);
       return numberOfOrders;
   } 
   
   public static boolean login(WebDriver driver,String email,String password){
       //open the browser and log in
    driver.get("https://studybay.app/");
    
    WebDriverWait waitForEmailField=new WebDriverWait(driver,10);
    WebElement emailField=waitForEmailField.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
    emailField.sendKeys(email);
    
    WebDriverWait waitForPassField=new WebDriverWait(driver,10);
    WebElement passField=waitForPassField.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
    passField.sendKeys(password);
    
    driver.findElement(By.xpath("//button")).sendKeys(Keys.RETURN);

    try{
    WebDriverWait waitForLogin=new WebDriverWait(driver,10);
    WebElement loginError=waitForLogin.until(ExpectedConditions.visibilityOfElementLocated(By.className("cx-login-form-error")));
    }catch(Exception e){
        isLoggedIn=true;
        driver.get("https://studybay.app/order/search");
    }
    
    return isLoggedIn;
   }
   
   public static void bid(WebDriver driver,int delay,String priceLevel,String bidOn,String[] filter, javax.swing.JTextArea displayLog) throws InterruptedException, IOException{
       int errorHeperer=0;
        int i=0;     
        WebDriverWait wait=new WebDriverWait(driver,15);
        
       //first time refresh
        if(errorHeperer==0){
           errorHeperer++;
           driver.navigate().refresh();
        }
        //end of first time refresh
        while(i>=0){
        List<WebElement> orders=driver.findElements(By.xpath("//div[@class='orderA__order' or @class=' orderA__order--read' or @class='orderA__order--paid'  or @class='orderA__order--premium'] "));
        
        //if no new order,wait before refresh
        if(orders.size()<=0){
         driver.manage().timeouts().implicitlyWait(delay, TimeUnit.SECONDS);
         orders=driver.findElements(By.xpath("//div[@class='orderA__order' or @class=' orderA__order--read' or @class='orderA__order--paid'  or @class='orderA__order--premium'] "));
        }
        
        System.out.println("found "+orders.size()+" orders");
        displayLog.append("found "+orders.size()+" order(s) \n");
        displayLog.append("-----------------------------------------------------------------------------"+"\n");
        
        for(WebElement order:orders){
                //click the more button
                try{
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='orderA__order' or @class=' orderA__order--read' or @class='orderA__order--paid'  or @class='orderA__order--premium']/div[@class='orderA__order__wrapper']/div[@class='orderA__contentWrapper']/div[@class='orderA__wrapper']/div[@class='orderA__meta']/div[@class='orderA__actions']/button[contains(.,'More')]"))).click();
                    System.out.println("expand btn clicked");
                    displayLog.append("Bidding order... \n");
                    
                }catch(ElementClickInterceptedException e){
                    driver.navigate().refresh();
                    displayLog.append("clicking the more button was intercepted, fixing... \n");
//                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label='Close']"))).click();
//                    driver.findElement(By.xpath("//span[@aria-label='Close']")).click();
                    Config.errorFileWriter(e.toString());
                    continue;
                }
                catch(StaleElementReferenceException e){
                    Config.errorFileWriter(e.toString());
                    displayLog.append("the \"more\" button is stale, fixing... \n");
                    driver.navigate().refresh();
                    continue;
                    //this will try clicking the element again and refreshes on failure
//                    if(retryingFindClick(driver,"//button[contains(., 'More')]")==false){
//                        driver.navigate().refresh();
//                        continue;
//                    }
                }
                catch(Exception e){
                    Config.errorFileWriter(e.toString());
                    displayLog.append("failed to click the more button, fixing... \n");
                    driver.navigate().refresh();
                    continue;
                }
              
                 //check for unwanted orders
                if(filter.length>0){
                  String cart=order.findElement(By.className("orderA__category")).getText();
                  String[] cartArray=cart.split(",");
                  String subject;
                  try{
                      subject=cartArray[1];
                  }catch(ArrayIndexOutOfBoundsException e){
                      subject="nothing";
                  }
                  
                  subject=subject.trim();
                  List<String> list = Arrays.asList(filter);
                  
                  if(list.contains(subject)){
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='orderA__order' or @class=' orderA__order--read' or @class='orderA__order--paid'  or @class='orderA__order--premium']/div[@class='orderA__order__wrapper']/div[@class='orderA__contentWrapper']/div[@class='orderA__wrapper']/div[@class='orderA__meta']/div[@class='orderA__actions']/button[contains(.,'Hide')]"))).click();
                    //div[@class='orderA__order' or @class=' orderA__order--read' or @class='orderA__order--paid'  or @class='orderA__order--premium']/div[@class='orderA__order__wrapper']/div[2]/button[1]
                    displayLog.append(subject+" is unwanted, looking for new orders... \n");
                    continue;
                  }
                  
              
              }
                System.out.println("its wanted");
                displayLog.append("subject is accepted \n");
                // end of check for unwanted orders

                //click start bidding button
                try{
                  order.findElement(By.xpath("//button[contains(., 'Start bidding')]")).click();
                }
                catch(NoSuchElementException e){
//                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label='Close']")));
                    Config.errorFileWriter(e.toString());
                    driver.navigate().refresh();
                    continue;
                }
                catch(Exception e){
                    System.out.println(e);
                    Config.errorFileWriter(e.toString());
//                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label='Close']")));
                    driver.navigate().refresh();
                    continue;
                }
                //end of click start bidding button  
       
                  //message placing
                  try{
                       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='styled__Field-sc-13g4w8f-1 ePCfsj'])[last()]"))).click();
                     }catch(Exception e){
                        displayLog.append("message placing failed,looking for a fix \n"); 
                        driver.navigate().refresh();
                        Config.errorFileWriter(e.toString());
                        Thread.sleep(delay*1000);
                        continue;
                    }
                    
                        
                        try{
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='styled__Wrapper-sc-4f1cbs-0 gTBVTO'])[last()]"))).click();
                        System.out.println("message placed"); 
                        displayLog.append("message placed \n");
                        }catch(Exception e){
                        System.out.println("message was not selected");  
                        displayLog.append("message was not selected \n");
                        driver.navigate().refresh();
                        Config.errorFileWriter(e.toString());
                        Thread.sleep(delay*1000);
                        continue;
                        }
                    //end of message placing
                    
                    if(!priceLevel.equalsIgnoreCase("none")){   
                    //extract prces ranges from modal
                        minPrice=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='styled__Amount-qyovge-7 cTVHAe'])[last()]"))).getText().substring(1);
                        avPrice=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='styled__Amount-qyovge-7 gQtpqC'])[last()]"))).getText().substring(1);
                        maxPrice=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='styled__Amount-qyovge-7 gSIIkh'])[last()]"))).getText().substring(1);
                          
              
                        WebElement inputAmount=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='styled__Wrapper-sc-1novt9v-2 fLTTlZ']/div/div/input)[last()]")));

                            switch(priceLevel){
                                case "Minimum":
                                    inputAmount.sendKeys(minPrice);
                                    displayLog.append("price set: "+"$ "+minPrice+"\n");
                                    break;
                                case "Average":
                                    inputAmount.sendKeys(avPrice);
                                    displayLog.append("price set: "+"$ "+avPrice+"\n");
                                    break;
                                case "Maximum":
                                    inputAmount.sendKeys(maxPrice);
                                    displayLog.append("price set: "+"$ "+maxPrice+"\n");
                                    break;
                                default:
                                    //inputAmount.sendKeys(" ");
                                    break;    
                            }
                            
                    }
//                     end of check price level
                    
                        try{
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='ui-modal-content']/div[@class='ui-modal-body']/div/div[@class='sb-makeOffer__actions']/div/button[1])[last()]"))).click();
                        }
                        catch(Exception e){
                        driver.navigate().refresh();
                        Config.errorFileWriter(e.toString());
                        Thread.sleep(delay*1000);
                        }
                        displayLog.append("bidded on: "+order.findElement(By.className("orderA__name")).getText()+"\n");
                        displayLog.append("-----------------------------------------------------------------------------"+"\n");
                        System.out.println("Bidd title: "+order.findElement(By.className("orderA__name")).getText());
                        Thread.sleep(delay*1000);
                        
               
        }
        driver.navigate().refresh();
        
        }
        
    
   }
   
   public static boolean retryingFindClick(WebDriver driver,String xpath) {
     WebDriverWait wait=new WebDriverWait(driver,2);
    boolean result = false;
    int attempts = 0;
    while(attempts < 4) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
            result = true;
            break;
        } catch(StaleElementReferenceException e) {
        }
        attempts++;
    }
    return result;
}
   
   public static void exitBrowser(WebDriver driver){
       driver.close();
   }
   
   
}
