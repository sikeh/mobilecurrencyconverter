/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biiblesoft.currency.ui;

import com.biiblesoft.currency.core.HtmlParser;
import java.io.IOException;
import java.util.Date;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.WaitScreen;
import org.netbeans.microedition.util.SimpleCancellableTask;

/**
 * @author shanbo
 */
public class SEKCNY extends MIDlet implements CommandListener {

    private boolean midletPaused = false;

    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private WaitScreen waitScreen;
    private Form mainForm;
    private ImageItem imageItem;
    private Spacer spacer;
    private StringItem updateTimeStringItem;
    private StringItem rateStringItem;
    private Form errorForm;
    private StringItem reasonStringItem;
    private Command mainForm_updateCommand;
    private Command mainForm_exitCommand;
    private Command errorForm_retryCommand;
    private Command errorForm_exitCommand;
    private Command cancelCommand;
    private SimpleCancellableTask parseHtmlTask;
    private Ticker waitScreen_ticker;
    private Image google_finance_logo_image;
    private Font bigBoldFont;
    private Image money_image;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The SEKCNY constructor.
     */
    public SEKCNY() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
        waitScreen = new WaitScreen(getDisplay());//GEN-BEGIN:|0-initialize|1|0-postInitialize
        waitScreen.setTitle("");
        waitScreen.setTicker(getWaitScreen_ticker());
        waitScreen.addCommand(getCancelCommand());
        waitScreen.setCommandListener(this);
        waitScreen.setImage(getGoogle_finance_logo_image());
        waitScreen.setText("Loading...");
        waitScreen.setTextFont(getBigBoldFont());
        waitScreen.setTask(getParseHtmlTask());
        mainForm = new Form("SEK - CNY", new Item[] { getRateStringItem(), getUpdateTimeStringItem(), getSpacer(), getImageItem() });
        mainForm.addCommand(getMainForm_updateCommand());
        mainForm.addCommand(getMainForm_exitCommand());
        mainForm.setCommandListener(this);
        errorForm = new Form("Error", new Item[] { getReasonStringItem() });
        errorForm.addCommand(getErrorForm_exitCommand());
        errorForm.addCommand(getErrorForm_retryCommand());
        errorForm.setCommandListener(this);//GEN-END:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, waitScreen);//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == errorForm) {//GEN-BEGIN:|7-commandAction|1|33-preAction
            if (command == errorForm_exitCommand) {//GEN-END:|7-commandAction|1|33-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|2|33-postAction
                // write post-action user code here
            } else if (command == errorForm_retryCommand) {//GEN-LINE:|7-commandAction|3|36-preAction
                // write pre-action user code here
                switchDisplayable(null, waitScreen);//GEN-LINE:|7-commandAction|4|36-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|28-preAction
        } else if (displayable == mainForm) {
            if (command == mainForm_exitCommand) {//GEN-END:|7-commandAction|5|28-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|6|28-postAction
                // write post-action user code here
            } else if (command == mainForm_updateCommand) {//GEN-LINE:|7-commandAction|7|26-preAction
                // write pre-action user code here
                switchDisplayable(null, waitScreen);//GEN-LINE:|7-commandAction|8|26-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|9|18-preAction
        } else if (displayable == waitScreen) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|9|18-preAction
                // write pre-action user code here
                switchDisplayable(null, errorForm);//GEN-LINE:|7-commandAction|10|18-postAction
                // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|11|17-preAction
                // write pre-action user code here
                switchDisplayable(null, mainForm);//GEN-LINE:|7-commandAction|12|17-postAction
                // write post-action user code here
            } else if (command == cancelCommand) {//GEN-LINE:|7-commandAction|13|39-preAction
                // write pre-action user code here
                switchDisplayable(null, mainForm);//GEN-LINE:|7-commandAction|14|39-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|7-postCommandAction
        }//GEN-END:|7-commandAction|15|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|16|
    //</editor-fold>//GEN-END:|7-commandAction|16|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: mainForm_updateCommand ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initiliazed instance of mainForm_updateCommand component.
     * @return the initialized component instance
     */
    public Command getMainForm_updateCommand() {
        if (mainForm_updateCommand == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            mainForm_updateCommand = new Command("Update", Command.OK, 0);//GEN-LINE:|25-getter|1|25-postInit
            // write post-init user code here
        }//GEN-BEGIN:|25-getter|2|
        return mainForm_updateCommand;
    }
    //</editor-fold>//GEN-END:|25-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: mainForm_exitCommand ">//GEN-BEGIN:|27-getter|0|27-preInit
    /**
     * Returns an initiliazed instance of mainForm_exitCommand component.
     * @return the initialized component instance
     */
    public Command getMainForm_exitCommand() {
        if (mainForm_exitCommand == null) {//GEN-END:|27-getter|0|27-preInit
            // write pre-init user code here
            mainForm_exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|27-getter|1|27-postInit
            // write post-init user code here
        }//GEN-BEGIN:|27-getter|2|
        return mainForm_exitCommand;
    }
    //</editor-fold>//GEN-END:|27-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: errorForm_exitCommand ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initiliazed instance of errorForm_exitCommand component.
     * @return the initialized component instance
     */
    public Command getErrorForm_exitCommand() {
        if (errorForm_exitCommand == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            errorForm_exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|32-getter|1|32-postInit
            // write post-init user code here
        }//GEN-BEGIN:|32-getter|2|
        return errorForm_exitCommand;
    }
    //</editor-fold>//GEN-END:|32-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: errorForm_retryCommand ">//GEN-BEGIN:|35-getter|0|35-preInit
    /**
     * Returns an initiliazed instance of errorForm_retryCommand component.
     * @return the initialized component instance
     */
    public Command getErrorForm_retryCommand() {
        if (errorForm_retryCommand == null) {//GEN-END:|35-getter|0|35-preInit
            // write pre-init user code here
            errorForm_retryCommand = new Command("Retry", Command.OK, 0);//GEN-LINE:|35-getter|1|35-postInit
            // write post-init user code here
        }//GEN-BEGIN:|35-getter|2|
        return errorForm_retryCommand;
    }
    //</editor-fold>//GEN-END:|35-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cancelCommand ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initiliazed instance of cancelCommand component.
     * @return the initialized component instance
     */
    public Command getCancelCommand() {
        if (cancelCommand == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            cancelCommand = new Command("Cancel", Command.CANCEL, 0);//GEN-LINE:|38-getter|1|38-postInit
            // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return cancelCommand;
    }
    //</editor-fold>//GEN-END:|38-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: parseHtmlTask ">//GEN-BEGIN:|19-getter|0|19-preInit
    /**
     * Returns an initiliazed instance of parseHtmlTask component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getParseHtmlTask() {
        if (parseHtmlTask == null) {//GEN-END:|19-getter|0|19-preInit
            // write pre-init user code here
            parseHtmlTask = new SimpleCancellableTask();//GEN-BEGIN:|19-getter|1|19-execute
            parseHtmlTask.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|19-getter|1|19-execute
                    try {
                        updateRate();
                    } catch (Exception e) {
                        e.printStackTrace();
                        ((StringItem) errorForm.get(0)).setText(e.toString());
                        throw e;
                    }
                }//GEN-BEGIN:|19-getter|2|19-postInit
            });//GEN-END:|19-getter|2|19-postInit
            // write post-init user code here
        }//GEN-BEGIN:|19-getter|3|
        return parseHtmlTask;
    }
    //</editor-fold>//GEN-END:|19-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: bigBoldFont ">//GEN-BEGIN:|41-getter|0|41-preInit
    /**
     * Returns an initiliazed instance of bigBoldFont component.
     * @return the initialized component instance
     */
    public Font getBigBoldFont() {
        if (bigBoldFont == null) {//GEN-END:|41-getter|0|41-preInit
            // write pre-init user code here
            bigBoldFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);//GEN-LINE:|41-getter|1|41-postInit
            // write post-init user code here
        }//GEN-BEGIN:|41-getter|2|
        return bigBoldFont;
    }
    //</editor-fold>//GEN-END:|41-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: waitScreen_ticker ">//GEN-BEGIN:|42-getter|0|42-preInit
    /**
     * Returns an initiliazed instance of waitScreen_ticker component.
     * @return the initialized component instance
     */
    public Ticker getWaitScreen_ticker() {
        if (waitScreen_ticker == null) {//GEN-END:|42-getter|0|42-preInit
            // write pre-init user code here
            waitScreen_ticker = new Ticker("Retrieving data from Google Finance...");//GEN-LINE:|42-getter|1|42-postInit
            // write post-init user code here
        }//GEN-BEGIN:|42-getter|2|
        return waitScreen_ticker;
    }
    //</editor-fold>//GEN-END:|42-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: google_finance_logo_image ">//GEN-BEGIN:|43-getter|0|43-preInit
    /**
     * Returns an initiliazed instance of google_finance_logo_image component.
     * @return the initialized component instance
     */
    public Image getGoogle_finance_logo_image() {
        if (google_finance_logo_image == null) {//GEN-END:|43-getter|0|43-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|43-getter|1|43-@java.io.IOException
                google_finance_logo_image = Image.createImage("/resource/images/google_finance_logo.png");
            } catch (java.io.IOException e) {//GEN-END:|43-getter|1|43-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|43-getter|2|43-postInit
            // write post-init user code here
        }//GEN-BEGIN:|43-getter|3|
        return google_finance_logo_image;
    }
    //</editor-fold>//GEN-END:|43-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: spacer ">//GEN-BEGIN:|49-getter|0|49-preInit
    /**
     * Returns an initiliazed instance of spacer component.
     * @return the initialized component instance
     */
    public Spacer getSpacer() {
        if (spacer == null) {//GEN-END:|49-getter|0|49-preInit
            // write pre-init user code here
            spacer = new Spacer(16, 8);//GEN-LINE:|49-getter|1|49-postInit
            // write post-init user code here
        }//GEN-BEGIN:|49-getter|2|
        return spacer;
    }
    //</editor-fold>//GEN-END:|49-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: imageItem ">//GEN-BEGIN:|50-getter|0|50-preInit
    /**
     * Returns an initiliazed instance of imageItem component.
     * @return the initialized component instance
     */
    public ImageItem getImageItem() {
        if (imageItem == null) {//GEN-END:|50-getter|0|50-preInit
            // write pre-init user code here
            imageItem = new ImageItem("", getMoney_image(), ImageItem.LAYOUT_CENTER | Item.LAYOUT_TOP | Item.LAYOUT_BOTTOM | Item.LAYOUT_VCENTER, "<Missing Image>", Item.PLAIN);//GEN-LINE:|50-getter|1|50-postInit
            // write post-init user code here
        }//GEN-BEGIN:|50-getter|2|
        return imageItem;
    }
    //</editor-fold>//GEN-END:|50-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: money_image ">//GEN-BEGIN:|51-getter|0|51-preInit
    /**
     * Returns an initiliazed instance of money_image component.
     * @return the initialized component instance
     */
    public Image getMoney_image() {
        if (money_image == null) {//GEN-END:|51-getter|0|51-preInit
            // write pre-init user code here
            try {//GEN-BEGIN:|51-getter|1|51-@java.io.IOException
                money_image = Image.createImage("/resource/images/money.jpg");
            } catch (java.io.IOException e) {//GEN-END:|51-getter|1|51-@java.io.IOException
                e.printStackTrace();
            }//GEN-LINE:|51-getter|2|51-postInit
            // write post-init user code here
        }//GEN-BEGIN:|51-getter|3|
        return money_image;
    }
    //</editor-fold>//GEN-END:|51-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: rateStringItem ">//GEN-BEGIN:|52-getter|0|52-preInit
    /**
     * Returns an initiliazed instance of rateStringItem component.
     * @return the initialized component instance
     */
    public StringItem getRateStringItem() {
        if (rateStringItem == null) {//GEN-END:|52-getter|0|52-preInit
            // write pre-init user code here
            rateStringItem = new StringItem("", null);//GEN-LINE:|52-getter|1|52-postInit
            // write post-init user code here
        }//GEN-BEGIN:|52-getter|2|
        return rateStringItem;
    }
    //</editor-fold>//GEN-END:|52-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: updateTimeStringItem ">//GEN-BEGIN:|53-getter|0|53-preInit
    /**
     * Returns an initiliazed instance of updateTimeStringItem component.
     * @return the initialized component instance
     */
    public StringItem getUpdateTimeStringItem() {
        if (updateTimeStringItem == null) {//GEN-END:|53-getter|0|53-preInit
            // write pre-init user code here
            updateTimeStringItem = new StringItem("Update: ", null);//GEN-LINE:|53-getter|1|53-postInit
            // write post-init user code here
        }//GEN-BEGIN:|53-getter|2|
        return updateTimeStringItem;
    }
    //</editor-fold>//GEN-END:|53-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: reasonStringItem ">//GEN-BEGIN:|54-getter|0|54-preInit
    /**
     * Returns an initiliazed instance of reasonStringItem component.
     * @return the initialized component instance
     */
    public StringItem getReasonStringItem() {
        if (reasonStringItem == null) {//GEN-END:|54-getter|0|54-preInit
            // write pre-init user code here
            reasonStringItem = new StringItem("Reason:", null);//GEN-LINE:|54-getter|1|54-postInit
            // write post-init user code here
        }//GEN-BEGIN:|54-getter|2|
        return reasonStringItem;
    }
    //</editor-fold>//GEN-END:|54-getter|2|





    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();
    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

    //**********************************************
    private HtmlParser htmlParser = new HtmlParser();

    private void updateRate() throws IOException {
        StringItem rateStringItem_local = (StringItem) mainForm.get(0);
        rateStringItem_local.setFont(bigBoldFont);
        rateStringItem_local.setText("1 SEK = " + htmlParser.getRate() + " CNY");
        Date date = new Date();
        ((StringItem) mainForm.get(1)).setText(date.toString());
    }
}
