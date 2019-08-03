package com.nixlord.dunzo.util;

import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.phoenixoverlord.pravega.extensions.LoggerKt;

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
        String resultText = visionText.getText();
        print(resultText);
        for(FirebaseVisionText.TextBlock block: visionText.getTextBlocks()){
            String blockText = block.getText();
            for(FirebaseVisionText.Line line: block.getLines()){
                String lineText = line.getText();
                for(FirebaseVisionText.Element element: line.getElements()){
                    String elementText = element.getText();
                }
            }
        }
        //LoggerKt.logDebug("DataFusion", "");

    }
    public static void print(String msg){
        LoggerKt.logDebug("DataFusion", msg);
    }
}
