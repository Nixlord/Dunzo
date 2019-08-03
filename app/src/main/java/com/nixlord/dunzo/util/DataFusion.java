package com.nixlord.dunzo.util;

import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.phoenixoverlord.pravega.extensions.LoggerKt;
import java.util.*;
public class DataFusion {
    public static void createProduct(FirebaseVisionText visionText) {
//        food/bill10
        /*

        Transform image text into Product
        Study Firebase docs to know how much library will help. Then medium articles. Then regex.

        Print the Bill in an orderly fashion.
        Once it is printed well, distinctly,

        Then test it on some other bill, and fix formatting, etc.

        Once that is done, create a Product class and a Store class which can optimally store the Product/Store

        Replace print calls with Object creation and return the object.

         */

        int headerCount = 1;
        String name = "";
        String address = "";
        String phoneNo = "";
        String resultText = visionText.getText();
        //print(resultText);

        for(FirebaseVisionText.TextBlock block: visionText.getTextBlocks()){
            if(headerCount-->0) {
                String blockText = block.getText();
                List<FirebaseVisionText.Line> lineList = block.getLines();
                name = lineList.get(0).getText();
                address = lineList.get(1).getText() + " " + lineList.get(2).getText() + " " + lineList.get(3).getText();
                phoneNo = lineList.get(4).getText().replaceAll("Ph No. ", "");
            }
        }
        print("Name: "+name+"\n Address: "+address+"\nPhone No.: "+phoneNo);
        //LoggerKt.logDebug("DataFusion", "");

    }
    public static void print(String msg){
        LoggerKt.logDebug("DataFusion", msg);
    }
}
