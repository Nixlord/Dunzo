# Dunzo

We are building an Android app capable of doing OCR which stores data collected from receipts
in a cloud database categorized by stores and products. We use Firebase Firestore, a
document-oriented database to store the inventory. The app can scan photos using the camera
and then we get the text using onDevice Firebase ML Kit Vision APIs

## How to build and use.

Get the apk and other relevant files from BOOTSTRAP folder.
Install the app on your device.

###
First Tab shows Categories of products.
Second Tab shows Stores

You need to navigate to the third Tab "ADD ITEM" to modify the inventory.
You need to click on the Camera button and then the app takes a Photo and sends it to AZURE ML apis.
You have wait for 10-15 seconds after which Azure replies. Then you have to press "Upload" Photo.
We then extract information from the text and then Upload to the cloud. It will be visible in the app after it is uploaded.
