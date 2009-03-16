/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.biiblesoft.currency.ui;

import com.biiblesoft.currency.core.ConvertHelper;
import com.biiblesoft.currency.core.RecordStoreManager;
import java.io.IOException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import org.netbeans.microedition.lcdui.WaitScreen;
import org.netbeans.microedition.util.SimpleCancellableTask;

/**
 * @author shanbo
 */
public class CurrencyConverter extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private WaitScreen convert_waitScreen;
    private Form errorForm;
    private StringItem reasonStringItem;
    private Form convertForm;
    private StringItem convertForm_resultStringItem;
    private ChoiceGroup convertForm_intoChoiceGroup;
    private ChoiceGroup convertForm_fromChoiceGroup;
    private TextField convertForm_convertTextField;
    private Command convertForm_convertCommand;
    private Command errorForm_retryCommand;
    private Command errorForm_exitCommand;
    private Command cancelCommand;
    private Command convertForm_exitCommand;
    private SimpleCancellableTask doConvertTask;
    private Ticker waitScreen_ticker;
    private Image google_finance_logo_image;
    private Font bigBoldFont;
    private SimpleCancellableTask task;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The CurrencyConverter constructor.
     */
    public CurrencyConverter() {
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
        convert_waitScreen = new WaitScreen(getDisplay());//GEN-BEGIN:|0-initialize|1|0-postInitialize
        convert_waitScreen.setTitle("");
        convert_waitScreen.setTicker(getWaitScreen_ticker());
        convert_waitScreen.addCommand(getCancelCommand());
        convert_waitScreen.setCommandListener(this);
        convert_waitScreen.setImage(getGoogle_finance_logo_image());
        convert_waitScreen.setText("Loading...");
        convert_waitScreen.setTextFont(getBigBoldFont());
        convert_waitScreen.setTask(getDoConvertTask());
        errorForm = new Form("Error", new Item[] { getReasonStringItem() });
        errorForm.addCommand(getErrorForm_exitCommand());
        errorForm.addCommand(getErrorForm_retryCommand());
        errorForm.setCommandListener(this);
        convertForm_intoChoiceGroup = new ChoiceGroup("into", Choice.POPUP);
        convertForm_fromChoiceGroup = new ChoiceGroup("from", Choice.POPUP);
        convertForm_convertTextField = new TextField("convert", "1", 32, TextField.NUMERIC);//GEN-END:|0-initialize|1|0-postInitialize
        // write post-initialize user code here

    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        switchDisplayable(null, getConvertForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
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
        if (displayable == convertForm) {//GEN-BEGIN:|7-commandAction|1|66-preAction
            if (command == convertForm_convertCommand) {//GEN-END:|7-commandAction|1|66-preAction
                // write pre-action user code here
                switchDisplayable(null, convert_waitScreen);//GEN-LINE:|7-commandAction|2|66-postAction
                // write post-action user code here
            } else if (command == convertForm_exitCommand) {//GEN-LINE:|7-commandAction|3|91-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|4|91-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|5|18-preAction
        } else if (displayable == convert_waitScreen) {
            if (command == WaitScreen.FAILURE_COMMAND) {//GEN-END:|7-commandAction|5|18-preAction
                // write pre-action user code here
                switchDisplayable(null, errorForm);//GEN-LINE:|7-commandAction|6|18-postAction
                // write post-action user code here
            } else if (command == WaitScreen.SUCCESS_COMMAND) {//GEN-LINE:|7-commandAction|7|17-preAction
                // write pre-action user code here
                switchDisplayable(null, getConvertForm());//GEN-LINE:|7-commandAction|8|17-postAction
                // write post-action user code here
            } else if (command == cancelCommand) {//GEN-LINE:|7-commandAction|9|39-preAction
                try {
                    // write pre-action user code here
                    convert.cancel();
                } catch (IOException ex) {
                    //ignore
                }
                switchDisplayable(null, getConvertForm());//GEN-LINE:|7-commandAction|10|39-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|11|33-preAction
        } else if (displayable == errorForm) {
            if (command == errorForm_exitCommand) {//GEN-END:|7-commandAction|11|33-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|12|33-postAction
                // write post-action user code here
            } else if (command == errorForm_retryCommand) {//GEN-LINE:|7-commandAction|13|36-preAction
                // write pre-action user code here
                switchDisplayable(null, convert_waitScreen);//GEN-LINE:|7-commandAction|14|36-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|7-postCommandAction
        }//GEN-END:|7-commandAction|15|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|16|
    //</editor-fold>//GEN-END:|7-commandAction|16|

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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: doConvertTask ">//GEN-BEGIN:|19-getter|0|19-preInit
    /**
     * Returns an initiliazed instance of doConvertTask component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getDoConvertTask() {
        if (doConvertTask == null) {//GEN-END:|19-getter|0|19-preInit
            // write pre-init user code here
            doConvertTask = new SimpleCancellableTask();//GEN-BEGIN:|19-getter|1|19-execute
            doConvertTask.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|19-getter|1|19-execute
                    try {
                        doConvertCurrency();
                    } catch (Exception e) {
                        e.printStackTrace();
                        ((StringItem) errorForm.get(0)).setText(e.toString());
                        throw e;
                    }
                }//GEN-BEGIN:|19-getter|2|19-postInit
            });//GEN-END:|19-getter|2|19-postInit
            // write post-init user code here
        }//GEN-BEGIN:|19-getter|3|
        return doConvertTask;
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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: convertForm ">//GEN-BEGIN:|55-getter|0|55-preInit
    /**
     * Returns an initiliazed instance of convertForm component.
     * @return the initialized component instance
     */
    public Form getConvertForm() {
        if (convertForm == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
            convertForm = new Form("Currency Converter", new Item[] { convertForm_convertTextField, convertForm_fromChoiceGroup, convertForm_intoChoiceGroup, getConvertForm_resultStringItem() });//GEN-BEGIN:|55-getter|1|55-postInit
            convertForm.addCommand(getConvertForm_convertCommand());
            convertForm.addCommand(getConvertForm_exitCommand());
            convertForm.setCommandListener(this);//GEN-END:|55-getter|1|55-postInit
            // write post-init user code here
            initial();
        }//GEN-BEGIN:|55-getter|2|
        return convertForm;
    }
    //</editor-fold>//GEN-END:|55-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: convertForm_convertCommand ">//GEN-BEGIN:|65-getter|0|65-preInit
    /**
     * Returns an initiliazed instance of convertForm_convertCommand component.
     * @return the initialized component instance
     */
    public Command getConvertForm_convertCommand() {
        if (convertForm_convertCommand == null) {//GEN-END:|65-getter|0|65-preInit
            // write pre-init user code here
            convertForm_convertCommand = new Command("Convert", Command.OK, 0);//GEN-LINE:|65-getter|1|65-postInit
            // write post-init user code here
        }//GEN-BEGIN:|65-getter|2|
        return convertForm_convertCommand;
    }
    //</editor-fold>//GEN-END:|65-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: convertForm_resultStringItem ">//GEN-BEGIN:|83-getter|0|83-preInit
    /**
     * Returns an initiliazed instance of convertForm_resultStringItem component.
     * @return the initialized component instance
     */
    public StringItem getConvertForm_resultStringItem() {
        if (convertForm_resultStringItem == null) {//GEN-END:|83-getter|0|83-preInit
            // write pre-init user code here
            convertForm_resultStringItem = new StringItem("", null);//GEN-LINE:|83-getter|1|83-postInit
            // write post-init user code here
        }//GEN-BEGIN:|83-getter|2|
        return convertForm_resultStringItem;
    }
    //</editor-fold>//GEN-END:|83-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: task ">//GEN-BEGIN:|89-getter|0|89-preInit
    /**
     * Returns an initiliazed instance of task component.
     * @return the initialized component instance
     */
    public SimpleCancellableTask getTask() {
        if (task == null) {//GEN-END:|89-getter|0|89-preInit
            // write pre-init user code here
            task = new SimpleCancellableTask();//GEN-BEGIN:|89-getter|1|89-execute
            task.setExecutable(new org.netbeans.microedition.util.Executable() {
                public void execute() throws Exception {//GEN-END:|89-getter|1|89-execute
                    // write task-execution user code here
                }//GEN-BEGIN:|89-getter|2|89-postInit
            });//GEN-END:|89-getter|2|89-postInit
            // write post-init user code here
        }//GEN-BEGIN:|89-getter|3|
        return task;
    }
    //</editor-fold>//GEN-END:|89-getter|3|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: convertForm_exitCommand ">//GEN-BEGIN:|90-getter|0|90-preInit
    /**
     * Returns an initiliazed instance of convertForm_exitCommand component.
     * @return the initialized component instance
     */
    public Command getConvertForm_exitCommand() {
        if (convertForm_exitCommand == null) {//GEN-END:|90-getter|0|90-preInit
            // write pre-init user code here
            convertForm_exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|90-getter|1|90-postInit
            // write post-init user code here
        }//GEN-BEGIN:|90-getter|2|
        return convertForm_exitCommand;
    }
    //</editor-fold>//GEN-END:|90-getter|2|

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
    private ConvertHelper convert;
    private RecordStoreManager rsm = new RecordStoreManager();

    private void initial() {
        // f = convertForm_fromChoiceGroup_local
        ChoiceGroup f = (ChoiceGroup) convertForm.get(1);
        f.append("AED (United Arab Emirates Dirham)", null);
        f.append("ANG (Netherlands Antillean Gulden)", null);
        f.append("ARS (Argentine Peso)", null);
        f.append("AUD (Australian Dollar)", null);
        f.append("BGN (Bulgarian Lev)", null);
        f.append("BHD (Bahraini Dinar)", null);
        f.append("BND (Brunei Dollar)", null);
        f.append("BOB (Bolivian Boliviano)", null);
        f.append("BRL (Brazilian Real)", null);
        f.append("BWP (Botswana Pula)", null);
        f.append("CAD (Canadian Dollar)", null);
        f.append("CHF (Swiss Franc)", null);
        f.append("CLP (Chilean Peso)", null);
        f.append("CNY (Chinese Yuan)", null);
        f.append("COP (Colombian Peso)", null);
        f.append("CSD (Serbian Dinar)", null);
        f.append("CZK (Czech Koruna)", null);
        f.append("DKK (Danish Krone)", null);
        f.append("EEK (Estonian Kroon)", null);
        f.append("EGP (Egyptian Pound)", null);
        f.append("EUR (Euro)", null);
        f.append("FJD (Fijian Dollar)", null);
        f.append("GBP (British Pound)", null);
        f.append("HKD (Hong Kong Dollar)", null);
        f.append("HNL (Honduran Lempira)", null);
        f.append("HRK (Croatian Kuna)", null);
        f.append("HUF (Hungarian Forint)", null);
        f.append("IDR (Indonesian Rupiah)", null);
        f.append("ILS (New Israeli Sheqel)", null);
        f.append("INR (Indian Rupee)", null);
        f.append("ISK (Icelandic Króna)", null);
        f.append("JPY (Japanese Yen)", null);
        f.append("KRW (South Korean Won)", null);
        f.append("KWD (Kuwaiti Dinar)", null);
        f.append("KZT (Kazakhstani Tenge)", null);
        f.append("LKR (Sri Lankan Rupee)", null);
        f.append("LTL (Lithuanian Litas)", null);
        f.append("MAD (Moroccan Dirham)", null);
        f.append("MUR (Mauritian Rupee)", null);
        f.append("MXN (Mexican Peso)", null);
        f.append("MYR (Malaysian Ringgit)", null);
        f.append("NOK (Norwegian Krone)", null);
        f.append("NPR (Nepalese Rupee)", null);
        f.append("NZD (New Zealand Dollar)", null);
        f.append("OMR (Omani Rial)", null);
        f.append("PEN (Peruvian Nuevo Sol)", null);
        f.append("PHP (Philippine Peso)", null);
        f.append("PKR (Pakistani Rupee)", null);
        f.append("PLN (Polish Złoty)", null);
        f.append("QAR (Qatari Riyal)", null);
        f.append("RON (New Romanian Leu)", null);
        f.append("RUB (Russian Ruble)", null);
        f.append("SAR (Saudi Riyal)", null);
        f.append("SEK (Swedish Krona)", null);
        f.append("SGD (Singapore Dollar)", null);
        f.append("SIT (Slovenian Tolar)", null);
        f.append("SKK (Slovak Koruna)", null);
        f.append("THB (Thai Baht)", null);
        f.append("TRY (New Turkish Lira)", null);
        f.append("TTD (Trinidad and Tobago Dollar)", null);
        f.append("TWD (New Taiwan Dollar)", null);
        f.append("UAH (Ukrainian Hryvnia)", null);
        f.append("USD (United States Dollar)", null);
        f.append("VEB (Venezuelan Bolívar)", null);
        f.append("ZAR (South African Rand)", null);


        // t = convertForm_toChoiceGroup_local
        ChoiceGroup t = (ChoiceGroup) convertForm.get(2);
        t.append("AED (United Arab Emirates Dirham)", null);
        t.append("ANG (Netherlands Antillean Gulden)", null);
        t.append("ARS (Argentine Peso)", null);
        t.append("AUD (Australian Dollar)", null);
        t.append("BGN (Bulgarian Lev)", null);
        t.append("BHD (Bahraini Dinar)", null);
        t.append("BND (Brunei Dollar)", null);
        t.append("BOB (Bolivian Boliviano)", null);
        t.append("BRL (Brazilian Real)", null);
        t.append("BWP (Botswana Pula)", null);
        t.append("CAD (Canadian Dollar)", null);
        t.append("CHF (Swiss Franc)", null);
        t.append("CLP (Chilean Peso)", null);
        t.append("CNY (Chinese Yuan)", null);
        t.append("COP (Colombian Peso)", null);
        t.append("CSD (Serbian Dinar)", null);
        t.append("CZK (Czech Koruna)", null);
        t.append("DKK (Danish Krone)", null);
        t.append("EEK (Estonian Kroon)", null);
        t.append("EGP (Egyptian Pound)", null);
        t.append("EUR (Euro)", null);
        t.append("FJD (Fijian Dollar)", null);
        t.append("GBP (British Pound)", null);
        t.append("HKD (Hong Kong Dollar)", null);
        t.append("HNL (Honduran Lempira)", null);
        t.append("HRK (Croatian Kuna)", null);
        t.append("HUF (Hungarian Forint)", null);
        t.append("IDR (Indonesian Rupiah)", null);
        t.append("ILS (New Israeli Sheqel)", null);
        t.append("INR (Indian Rupee)", null);
        t.append("ISK (Icelandic Króna)", null);
        t.append("JPY (Japanese Yen)", null);
        t.append("KRW (South Korean Won)", null);
        t.append("KWD (Kuwaiti Dinar)", null);
        t.append("KZT (Kazakhstani Tenge)", null);
        t.append("LKR (Sri Lankan Rupee)", null);
        t.append("LTL (Lithuanian Litas)", null);
        t.append("MAD (Moroccan Dirham)", null);
        t.append("MUR (Mauritian Rupee)", null);
        t.append("MXN (Mexican Peso)", null);
        t.append("MYR (Malaysian Ringgit)", null);
        t.append("NOK (Norwegian Krone)", null);
        t.append("NPR (Nepalese Rupee)", null);
        t.append("NZD (New Zealand Dollar)", null);
        t.append("OMR (Omani Rial)", null);
        t.append("PEN (Peruvian Nuevo Sol)", null);
        t.append("PHP (Philippine Peso)", null);
        t.append("PKR (Pakistani Rupee)", null);
        t.append("PLN (Polish Złoty)", null);
        t.append("QAR (Qatari Riyal)", null);
        t.append("RON (New Romanian Leu)", null);
        t.append("RUB (Russian Ruble)", null);
        t.append("SAR (Saudi Riyal)", null);
        t.append("SEK (Swedish Krona)", null);
        t.append("SGD (Singapore Dollar)", null);
        t.append("SIT (Slovenian Tolar)", null);
        t.append("SKK (Slovak Koruna)", null);
        t.append("THB (Thai Baht)", null);
        t.append("TRY (New Turkish Lira)", null);
        t.append("TTD (Trinidad and Tobago Dollar)", null);
        t.append("TWD (New Taiwan Dollar)", null);
        t.append("UAH (Ukrainian Hryvnia)", null);
        t.append("USD (United States Dollar)", null);
        t.append("VEB (Venezuelan Bolívar)", null);
        t.append("ZAR (South African Rand)", null);


        String from = rsm.readFrom();
        String to = rsm.readTo();




        if (!from.equals("")) {
            for (int i = 0; i < t.size(); i++) {
                if (f.getString(i).equals(from)) {
                    f.setSelectedIndex(i, true);
                    break;
                }
            }
        }


        if (!to.equals("")) {
            for (int i = 0; i < t.size(); i++) {
                if (t.getString(i).equals(to)) {
                    t.setSelectedIndex(i, true);
                    break;
                }
            }
        }

    }

    private void doConvertCurrency() throws IOException {
        convert = new ConvertHelper();
        TextField convertForm_convertTextField_local = (TextField) convertForm.get(0);
        ChoiceGroup convertForm_fromChoiceGroup_local = (ChoiceGroup) convertForm.get(1);
        ChoiceGroup convertForm_toChoiceGroup_local = (ChoiceGroup) convertForm.get(2);
        StringItem convertForm_resultStringItem_local = (StringItem) convertForm.get(3);
        convertForm_resultStringItem_local.setFont(bigBoldFont);
        String number = convertForm_convertTextField_local.getString();
        String from = convertForm_fromChoiceGroup_local.getString(convertForm_fromChoiceGroup_local.getSelectedIndex());
        String to = convertForm_toChoiceGroup_local.getString(convertForm_toChoiceGroup_local.getSelectedIndex());
        rsm.writeFrom(from);
        rsm.writeTo(to);
        convertForm_resultStringItem_local.setText(convert.convert(number, from.substring(0, 3), to.substring(0, 3)));
    }
}
