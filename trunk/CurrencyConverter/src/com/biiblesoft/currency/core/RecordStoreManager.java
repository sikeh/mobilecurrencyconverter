package com.biiblesoft.currency.core;

import javax.microedition.rms.RecordStore;

/**
 *
 * @author Shanbo Li
 */
public class RecordStoreManager {

    private static final String FROM = "currency_from";
    private static final String TO = "currency_to";
    
    private RecordStore recordStore;

    public RecordStoreManager() {

    }

    public String readFrom() {
        openRecordStore(FROM);
        String result = readRecord();
        closeRecStore();
        return result;
    }

    public void writeFrom(String from) {
        openRecordStore(FROM);
        writeRecord(from);
        closeRecStore();
    }

    public String readTo() {
        openRecordStore(TO);
        String result = readRecord();
        closeRecStore();
        return result;
    }

    public void writeTo(String to) {
        openRecordStore(TO);
        writeRecord(to);
        closeRecStore();
    }

    

    private void writeRecord(String data) {
        byte[] byteData = data.getBytes();
        try {
            recordStore.deleteRecord(recordStore.getNextRecordID()-1);
        }catch(Exception ignore){}

        try{
            int id  = recordStore.addRecord(byteData, 0, byteData.length);
        } catch (Exception ignore) {
        }
    }

    private String readRecord() {
        try {
            byte[] byteData = recordStore.getRecord(recordStore.getNextRecordID()-1);
            return new String(byteData);
        } catch (Exception ignore) {
            return "";
        }
    }

    private void openRecordStore(String storeName) {
        try {
            recordStore = RecordStore.openRecordStore(storeName, true);
        } catch (Exception ignore) {
        }
    }

    private void closeRecStore() {
        try {
            recordStore.closeRecordStore();
        } catch (Exception ignore) {
        }
    }
}
