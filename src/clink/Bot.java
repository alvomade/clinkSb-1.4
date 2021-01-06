/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clink;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
   public static JSONArray selectors(){
       try {
           URL url = new URL("https://sb.clink.co.ke/getElements.php");
           HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
           httpURLConnection.setRequestMethod("GET");
           
           String line="";
           InputStreamReader inputStreamReader=new InputStreamReader(httpURLConnection.getInputStream());
           BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
           StringBuilder response=new StringBuilder();
           while ((line=bufferedReader.readLine())!=null){
               response.append(line);
           }
           bufferedReader.close();

           //adding square brackets t0 make a valid json array
           String datas="["+response+"]";
           JSONArray jsonArray=new JSONArray(response.toString());
           
           JSONObject rec = jsonArray.getJSONObject(0);
           System.out.println(jsonArray.getJSONObject(2).getString("locator"));
           System.out.println("Submit "+ jsonArray);
           
           
           return jsonArray;
           
       }catch (Exception e){
           System.out.println("Error in Making Get Request"+e);
           return null;
       }            
   }
   public static boolean login(WebDriver driver,String email,String password){
       JSONArray jsonArray = Bot.selectors();
       
       //open the browser and log in
    driver.get("https://studybay.app/");
    
    //email
    WebDriverWait wait=new WebDriverWait(driver,10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(jsonArray.getJSONObject(0).getString("locator")))).sendKeys(email);
    //password
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(jsonArray.getJSONObject(1).getString("locator")))).sendKeys(password);
    //submit
    driver.findElement(By.xpath(jsonArray.getJSONObject(2).getString("locator"))).sendKeys(Keys.RETURN);

    //wrong_email_password
    try{
    if(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(jsonArray.getJSONObject(15).getString("locator")))).isDisplayed()){
        isLoggedIn=false;
    }    
    
    }catch(Exception e){
        isLoggedIn=true;
        driver.get("https://studybay.app/order/search");
    }
    
    return isLoggedIn;
   }
   
   public static void bid(WebDriver driver,int delay,String priceLevel,Boolean refreshAfterBid,String[] filter, javax.swing.JTextArea displayLog) throws InterruptedException, IOException{
       int errorHeperer=0;
        int i=0;     
        JSONArray jsonArray = Bot.selectors();
        WebDriverWait wait=new WebDriverWait(driver,15);
        WebDriverWait waitx2=new WebDriverWait(driver,30);
       //first time refresh
        if(errorHeperer==0){
           errorHeperer++;
           driver.navigate().refresh();
        }
        //end of first time refresh
        //order_list
        while(i>=0){
        //List<WebElement> orders=driver.findElements(By.xpath("//div[@class='orderA__order' or @class=' orderA__order--read' or @class='orderA__order--paid'  or @class='orderA__order--premium'] "));
        List<WebElement> orders=driver.findElements(By.xpath(jsonArray.getJSONObject(3).getString("locator")));
        //if no new order,wait before refresh
        if(orders.size()<=0){
         driver.manage().timeouts().implicitlyWait(delay, TimeUnit.SECONDS);
         //order_list 
         //orders=driver.findElements(By.xpath("//div[@class='orderA__order' or @class=' orderA__order--read' or @class='orderA__order--paid'  or @class='orderA__order--premium'] "));
         orders=driver.findElements(By.xpath(jsonArray.getJSONObject(3).getString("locator")));
        }
        
        System.out.println("found "+orders.size()+" orders");
        displayLog.append("found "+orders.size()+" order(s) \n");
        displayLog.append("-----------------------------------------------------------------------------"+"\n");
        
        for(WebElement order:orders){
                //more
                try{
                    //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='orderA__order' or @class=' orderA__order--read' or @class='orderA__order--paid'  or @class='orderA__order--premium']/div[@class='orderA__order__wrapper']/div[@class='orderA__contentWrapper']/div[@class='orderA__wrapper']/div[@class='orderA__meta']/div[@class='orderA__actions']/button[contains(.,'More')]"))).click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(jsonArray.getJSONObject(4).getString("locator")))).click();
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
                  //subject
                  //String subjectPlusType=order.findElement(By.className("orderA__category")).getText();
                  String subjectPlusType=order.findElement(By.className(jsonArray.getJSONObject(5).getString("locator"))).getText();
                  String[] subjectPlusTypeArray=subjectPlusType.split(",");
                  String subject;
                  try{
                      subject=subjectPlusTypeArray[1];
                  }catch(ArrayIndexOutOfBoundsException e){
                      subject="nothing";
                  }
                  
                  subject=subject.trim();
                  List<String> list = Arrays.asList(filter);
                  
                  if(list.contains(subject)){
                    //close
                    //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='orderA__order' or @class=' orderA__order--read' or @class='orderA__order--paid'  or @class='orderA__order--premium']/div[@class='orderA__order__wrapper']/div[@class='orderA__contentWrapper']/div[@class='orderA__wrapper']/div[@class='orderA__meta']/div[@class='orderA__actions']/button[contains(.,'Hide')]"))).click();
                    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(jsonArray.getJSONObject(6).getString("locator")))).click();
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
                   //start_bidding 
                  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(jsonArray.getJSONObject(7).getString("locator")))).click();
                  //div[@class='orderA__order' or @class=' orderA__order--read' or @class='orderA__order--paid'  or @class='orderA__order--premium']/div[@class='orderA__order__wrapper']/div[@class='orderA__contentWrapper']/div[@class='ui-collapse-item ui-collapse-item-active']/div[@class='ui-collapse-content ui-collapse-content-active']/div/div/div/div[2]/button
//                  order.findElement(By.xpath(jsonArray.getJSONObject(7).getString("locator"))).click();
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
                    driver.navigate().refresh();
                    continue;
                }
                //end of click start bidding button  
       
                  //message_dropDown
                  try{
                       //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='styled__Field-sc-13g4w8f-1 ePCfsj'])[last()]"))).click();
                       wait.until(ExpectedConditions.elementToBeClickable(By.xpath(jsonArray.getJSONObject(8).getString("locator")))).click();
                     }catch(Exception e){
                        displayLog.append("message placing failed,looking for a fix... \n"); 
                        driver.navigate().refresh();
                        Config.errorFileWriter(e.toString());
                        Thread.sleep(delay*1000);
                        continue;
                    }
                    
                    //message_select    
                        try{
                        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='styled__Wrapper-sc-4f1cbs-0 gTBVTO'])[last()]"))).click();
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(jsonArray.getJSONObject(9).getString("locator")))).click();
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
                    //min,max,average
                        //minPrice=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='styled__Amount-qyovge-7 cTVHAe'])[last()]"))).getText().substring(1);
                        minPrice=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(jsonArray.getJSONObject(10).getString("locator")))).getText().substring(1);
                        //avPrice=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='styled__Amount-qyovge-7 gQtpqC'])[last()]"))).getText().substring(1);
                        avPrice=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(jsonArray.getJSONObject(11).getString("locator")))).getText().substring(1);
                        //maxPrice=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='styled__Amount-qyovge-7 gSIIkh'])[last()]"))).getText().substring(1);
                        maxPrice=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(jsonArray.getJSONObject(12).getString("locator")))).getText().substring(1);
                        
                        //price_input
                        //WebElement inputAmount=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='styled__Wrapper-sc-1novt9v-2 fLTTlZ']/div/div/input)[last()]")));
                        WebElement inputAmount=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(jsonArray.getJSONObject(13).getString("locator"))));

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
                        //start_bidding
                        try{
                        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='ui-modal-content']/div[@class='ui-modal-body']/div/div[@class='sb-makeOffer__actions']/div/button[1])[last()]"))).click();
                        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(jsonArray.getJSONObject(14).getString("locator")))).click();
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
                        
                        
                        //if its one bid per refresh
                        if(refreshAfterBid){    
                            try{
                            List<WebElement>closeReads=waitx2.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='orderA__order orderA__order--read']/div[@class='orderA__order__wrapper']/div[2]/button[1]")));
                            for(WebElement closeRead:closeReads){
                                closeRead.click();
                            }
                            displayLog.append("refreshing..... "+"\n");
                            }
                            catch(Exception e){
                            break;    
                            }
                            
                            break;
                        }
               
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
