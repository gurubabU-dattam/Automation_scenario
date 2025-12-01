Project Description:-
This project automates a shopping flow on Flipkart India using Selenium WebDriver (version 4.34.0) with Java and Chrome. The script searches for Bluetooth Speakers, applies filters (Brand: boAt and 4 star & above rating), sorts the products by Price – Low to High, opens the first product in a new tab, and checks whether the “Available offers” section is displayed. If it is available, the total number of offers is printed in the console.
Execution Scenarios:-
If the “Add to Cart” button is visible and enabled, the product is added to the cart and a screenshot is saved as Screenshots/cart_result.png. If the product is unavailable (button missing, disabled, or out of stock), no action is taken, a message is printed to the console, and a screenshot of the product page is saved as Screenshots/result.png. All screenshots are stored in the Screenshots folder of the project.

Steps for Script:-

source path: src/test/java

package: Task

class file: FlipKart.java

Screenshot folder: Screenshots

project files: pom.xml and README.md


The test script as per the scenario is written inside "FlipKart.java" in "Task" package in "src/test/java" and the Screenshot for the same is present inside "Screenshots" folder. The Screenshots folder contains 2 files, one is as per the scenario and the other is for another product for checking the screenshot when the "Add To Cart" button is enabled
