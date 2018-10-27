import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        count++;
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
    System.out.println(count);
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel upPixel = null;
    Pixel downPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    Color downPixelColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        boolean setBlack = false;
        upPixel = pixels[row][col];
        if (row+1 < 360) {
        	downPixel = pixels[row+1][col];
        }
        else {
        	downPixel = pixels[row][col];
        }
        downPixelColor = downPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > edgeDist) {
        	//leftPixel.setColor(Color.BLACK);
        	setBlack = true;
        }
        else {
        	//leftPixel.setColor(Color.WHITE);
        }
        
        if (upPixel.colorDistance(downPixelColor) > edgeDist) {
        	//upPixel.setColor(Color.BLACK);
        	setBlack = true;
        }
        else {
        	//upPixel.setColor(Color.WHITE);
        }
        if (setBlack) {
        	leftPixel.setColor(Color.BLACK);
        }
        else {
        	leftPixel.setColor(Color.WHITE);
        }
      }
    }
  }
  
  public void keepOnlyBlue() {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels){
		  for (Pixel pixelObj : rowArray){
			  pixelObj.setGreen(0);
			  pixelObj.setRed(0);
		  }
	  }
  }
  
  public void negate() {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels){
		  for (Pixel pixelObj : rowArray){
			  pixelObj.setGreen(255 - pixelObj.getGreen());
			  pixelObj.setRed(255 - pixelObj.getRed());
			  pixelObj.setBlue(255 - pixelObj.getBlue());
		  }
	  }
  }
  
  public void grayscale() {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels){
		  for (Pixel pixelObj : rowArray){
			  int average = ((pixelObj.getBlue() + pixelObj.getGreen() + pixelObj.getRed()) / 3);
			  pixelObj.setBlue(average);
			  pixelObj.setGreen(average);
			  pixelObj.setRed(average);
		  }
	  }
  }
  
  public void fixUnderwater() {
	  Pixel[][] pixels = this.getPixels2D();
	  for (Pixel[] rowArray : pixels){
		  for (Pixel pixelObj : rowArray){
			  if ((pixelObj.getRed()>20 || pixelObj.getRed()<8) ) {
				  pixelObj.setBlue(pixelObj.getBlue()-50);
				  pixelObj.setGreen(pixelObj.getGreen()-50);
				  pixelObj.setRed(pixelObj.getRed()-50);
			  }
			  else {
				  pixelObj.setBlue(pixelObj.getBlue()+200);
			  }
		  }
	  }
  }
  
  public void mirrorVerticalRightToLeft() {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel leftPixel = null;
	  Pixel rightPixel = null;
	  int width = pixels[0].length;
	  for (int row = 0; row < pixels.length; row++){
		  for (int col = 0; col < width / 2; col++){
			  leftPixel = pixels[row][col];
			  rightPixel = pixels[row][width - 1 - col]; 
			  leftPixel.setColor(rightPixel.getColor());
		  }
	  } 
  }
  
  public void mirrorHorizontal() {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  int height = pixels.length-1;//640x480
	  int width = pixels[0].length-1;
	  System.out.println(height);//480, height
	  System.out.println(pixels[0].length);//640, width
	  for (int row = 0; row < (height/2); row++){//x-axis
		  for (int col = 0; col < width; col++){ //y-axis
			  topPixel = pixels[row][col];
			  //System.out.println(topPixel);
//			  System.out.println(row);
//			  System.out.println(height - row);
			  bottomPixel = pixels[height - 1 - row][col];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
  }
  
  public void mirrorHorizontalBotToTop() {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  int height = pixels.length-1;//640x480
	  int width = pixels[0].length-1;
	  System.out.println(height);//480, height
	  System.out.println(pixels[0].length);//640, width
	  for (int row = 0; row < (height/2); row++){//x-axis
		  for (int col = 0; col < width; col++){ //y-axis
			  topPixel = pixels[row][col];
			  //System.out.println(topPixel);
//			  System.out.println(row);
//			  System.out.println(height - row);
			  bottomPixel = pixels[height - 1 - row][col];
			  topPixel.setColor(bottomPixel.getColor());
		  }
	  }
  }
  
  public void mirrorDiagonal() {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  int height = pixels.length-1;//640x480
	  int width = pixels[0].length-1;
	  System.out.println(height);//480, height
	  System.out.println(pixels[0].length);//640, width
	  for (int row = 0; row <= height; row++){//x-axis
		  for (int col = row; col <= height; col++){ //y-axis
			  if (row - 1 >= 0 && col - 1 >= 0) {
				  topPixel = pixels[row][col];
				  bottomPixel = pixels[col][row];
				  topPixel.setColor(bottomPixel.getColor());
			  }
//			  else {
//				  topPixel = pixels[row][col];
//			  }
		  }
	  }
  }
  
  public void mirrorArms() {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  for (int row = 156; row < 192; row++){//192, 36 high
		  for (int col = 104; col < 171; col++){//169, 65 wide
			  topPixel = pixels[row][col];
			  bottomPixel = pixels[192 - row + 192][col];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
	  for (int row = 169; row < 200; row++){
		  for (int col = 238; col < 295; col++){
			  topPixel = pixels[row][col];
			  bottomPixel = pixels[190 - row + 200][col];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
  }
  
  public void mirrorGull() {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  for (int row = 230; row < 345; row++){
		  for (int col = 230; col < 345; col++){
			  topPixel = pixels[row][col];
			  bottomPixel = pixels[row + 20][col + 200 + 1];
			  bottomPixel.setColor(topPixel.getColor());
		  }
	  }
  }
  
  public void copy(Picture fromPic, int startRow, int startCol, int endRow, int endCol) {
	  Pixel fromPixel = null;
	  Pixel toPixel = null;
	  Pixel[][] toPixels = this.getPixels2D();
	  Pixel[][] fromPixels = fromPic.getPixels2D();
	  	for (int fromRow = 0, toRow = startRow; fromRow < startRow && toRow < endRow; fromRow++, toRow++) {
	    	for (int fromCol = 0, toCol = startCol; fromCol < startCol && toCol < endCol; fromCol++, toCol++) {
	    		fromPixel = fromPixels[fromRow][fromCol];
	    		toPixel = toPixels[toRow][toCol];
	    		toPixel.setColor(fromPixel.getColor());
	    	}
	    }
  }
  
  public void myCollage(Picture p1, Picture p2, Picture p3, Picture p4) {
	  p2.negate();
	  p3.fixUnderwater();
	  p4.zeroBlue();
	  p1.copy(p3, 100, 200, 200, 300);
	  p1.copy(p2, 125, 345, 345, 445);//not working yet, seagull
	  p1.copy(p4, 150, 150, 250, 250);
	  p1.mirrorHorizontal();
  }
  
  public void edgeDetection2(int edgeDist) {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel upPixel = null;
    Pixel downPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    Color downPixelColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        upPixel = pixels[row][col];
        if (row+1 < 360) {
        	downPixel = pixels[row+1][col];
        }
        else {
        	downPixel = pixels[row][col];
        }
        downPixelColor = downPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
        
        if (upPixel.colorDistance(downPixelColor) > edgeDist) {
        	upPixel.setColor(Color.BLACK);
        }
        else {
        	upPixel.setColor(Color.WHITE);
        }
      }
    }
  }
  
  public void chroma(Picture fromPic) {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  Pixel leftPixel = null;
	  Pixel rightPixel = null;
	  Pixel nextPixel = null;
	  Pixel[][] fromPixels = fromPic.getPixels2D();
	  for (int row = 1004; row < 1360; row++){//changes the Nightwing logo on my shirt from blue to red
		  for (int col = 2420; col < 2616; col++){
			  topPixel = pixels[row][col];
			  if (topPixel.getBlue() > 100) {
				  topPixel.setBlue(topPixel.getBlue()-100);
				  topPixel.setRed(topPixel.getRed()+200);
				  topPixel.setGreen(topPixel.getGreen()-50);
			  }
		  }
	  }
	  for (int row = 0; row < 2336; row++) {//puts me on the bridge of the Enterprise D, or in No Man's Sky
		  for (int col = 0; col < 2874; col++) {
			  topPixel = pixels[row][col];
			  bottomPixel = fromPixels[row][col];
			  boolean ignore = false;
			  if (col > 0 && col < 2873) {
				  leftPixel = pixels[row][col-1];
				  rightPixel = pixels[row][col+1];
			  }
			  else {
				  ignore = true;
			  }
			  if (col < 784) {
				  topPixel.setColor(bottomPixel.getColor());
			  }
			  else {
				  if (topPixel.getRed() < 90 && topPixel.getGreen() > 90 && topPixel.getBlue() > 15 && topPixel.getBlue() < 105) {
					  topPixel.setColor(bottomPixel.getColor());
				  }
			  }
			  if (!ignore) {
				  if (row == 1374 && col == 1493) {//testing a pixel that's triggering the gradient when it shouldn't
					  if (!(topPixel.getBlue() >= 250 && topPixel.getRed() <= 5 && topPixel.getGreen() <= 5)) {//checks out; thinks the pixel is blue
						  System.out.println("This is totally not blue!");
					  }
					  if (topPixel.getBlue() < 230 && topPixel.getRed() < 240 && topPixel.getGreen() < 200) {
						  System.out.println("This is not white");
					  }
				  }
				  if (topPixel.getBlue() >= 250 && topPixel.getRed() <= 5 && topPixel.getGreen() <= 5) {
					  //if left or right pixel is different than blue, start gradient -------------------------------------------
					  if (!(leftPixel.getBlue() >= 250 && leftPixel.getRed() <= 5 && leftPixel.getGreen() <= 5) && (leftPixel.getBlue() < 230 && leftPixel.getRed() < 240 && leftPixel.getGreen() < 200) && (rightPixel.getBlue() >= 250 && rightPixel.getRed() <= 5 && rightPixel.getGreen() <= 5)) {
						  for (int x = 0; x <= 40; x++) {
							  nextPixel = pixels[row][col - x];
							  nextPixel.setBlue((nextPixel.getBlue() + 230) - x*3);
							  nextPixel.setGreen((nextPixel.getGreen() + 230) - x*3);
							  nextPixel.setRed((nextPixel.getRed() + 230) - x*3);
						  }
					  }
					  if (!(rightPixel.getBlue() >= 250 && rightPixel.getRed() <= 5 && rightPixel.getGreen() <= 5) && (rightPixel.getBlue() < 230 && rightPixel.getRed() < 240 && rightPixel.getGreen() < 200) && (leftPixel.getBlue() >= 250 && leftPixel.getRed() <= 5 && leftPixel.getGreen() <= 5)) {
						  for (int x = 0; x <= 40; x++) {
							  nextPixel = pixels[row][col + x];
							  nextPixel.setBlue((nextPixel.getBlue() + 230) - x*3);
							  nextPixel.setGreen((nextPixel.getGreen() + 230) - x*3);
							  nextPixel.setRed((nextPixel.getRed() + 230) - x*3);
						  }
					  }
				  }
			  }
			  ignore = false;
		  }
	  }
	  for (int row = 1848; row < 2336; row++) {//gets the shadowed green screen in the bottom right of the picture
		  for (int col = 2628; col < 2874; col++) {
			  topPixel = pixels[row][col];
			  bottomPixel = fromPixels[row][col];
			  if (topPixel.getRed() < 30 && topPixel.getGreen() > 30 && topPixel.getGreen() < 100) {
				  topPixel.setColor(bottomPixel.getColor());
			  }
			  else if (topPixel.getRed() < 10 && topPixel.getGreen() > 20 && topPixel.getBlue() < 10) {
				  topPixel.setColor(bottomPixel.getColor());
			  }
		  }
	  }
	  
	  for (int row = 0; row < 2336; row++) {//changes the color of the lightsaber blade
		  for (int col = 0; col < 2874; col++) {
			  topPixel = pixels[row][col];
			  if (topPixel.getBlue() >= 250 && topPixel.getRed() <= 5 && topPixel.getGreen() <= 5) {
				  topPixel.setColor(Color.BLACK);
				  //System.out.println("found a blue pixel, should be changing it to black now");
			  }
		  }
	  }
  }
  
  public void chromaRed(Picture fromPic) {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel topPixel = null;
	  Pixel bottomPixel = null;
	  Pixel[][] fromPixels = fromPic.getPixels2D();
	  for (int row = 0; row < 2336; row++) {//puts me on the bridge of the Enterprise D, or in No Man's Sky
		  for (int col = 0; col < 2874; col++) {
			  topPixel = pixels[row][col];
			  bottomPixel = fromPixels[row][col];
			  if (col < 784) {
				  topPixel.setColor(bottomPixel.getColor());
			  }
			  else {
				  if (topPixel.getRed() > 80 && topPixel.getGreen() < 65 && topPixel.getBlue() < 65) {
					  topPixel.setColor(bottomPixel.getColor());
				  }
			  }
		  }
	  }
	  for (int row = 1848; row < 2336; row++) {//gets the shadowed red screen in the bottom right of the picture
		  for (int col = 2628; col < 2874; col++) {
			  topPixel = pixels[row][col];
			  bottomPixel = fromPixels[row][col];
			  if (topPixel.getRed() > 27 && topPixel.getGreen() < 30 && topPixel.getBlue() < 30) {
				  topPixel.setColor(bottomPixel.getColor());
			  }
			  else if (topPixel.getRed() < 10 && topPixel.getGreen() > 20 && topPixel.getBlue() < 10) {
				  topPixel.setColor(bottomPixel.getColor());
			  }
		  }
	  }
	  for (int row = 1004; row < 1360; row++){//changes the Nightwing logo on my shirt from blue to red
		  for (int col = 2420; col < 2616; col++){
			  topPixel = pixels[row][col];
			  if (topPixel.getBlue() > 100) {
				  topPixel.setBlue(topPixel.getBlue()-100);
				  topPixel.setRed(topPixel.getRed()+200);
				  topPixel.setGreen(topPixel.getGreen()-50);
			  }
		  }
	  }
	  
  }
  
  public void stego1(String mess) {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel pixel = null;
	  String message = mess + "#%#";
	  //String s = "01000001";
	  int charNum = 0;
	  //int charToInt;// = Integer.parseInt(c, 2);
	  
	  for (int row = 0; row < pixels.length; row++) {
		  for (int col = 0; col < pixels[0].length; col++) {
			  pixel = pixels[row][col];
			  if (charNum < message.length()) {
				  char c = message.charAt(charNum);
				  int cInt = (int)c;
				  if (pixel.getBlue() < 100 && pixel.getGreen() < 100) {
					  System.out.println(c + " " + cInt);
					  pixel.setRed(cInt);
					  System.out.println("this actually did something... row=" + row + " col=" + col);
					  charNum++;
				  }
			  }
			  
		  }
	  }
  }
  
  public void decrypt1() {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel pixel = null;
	  String message = "";
	  int redVal = 0;
	  int charNum = 0;
	  char prevChar = '@';
	  char oldestChar = '@';
	  boolean stop = false;
	  System.out.println("----------------------now decrypting-------------------------");
	  for (int row = 0; row < pixels.length; row++) {
		  for (int col = 0; col < pixels[0].length; col++) {
			  pixel = pixels[row][col];
			  if (!stop) {
				  if (pixel.getBlue() < 100 && pixel.getGreen() < 100) {
					  redVal = pixel.getRed();
					  if ((char)redVal == '#' && prevChar == '%' && oldestChar == '#') {
						  stop = true;
					  }
					  message = message + (char)redVal;
					  System.out.println("So far: " + message);
					  oldestChar = prevChar;
					  prevChar = (char)redVal;
					  
					  //System.out.println("this found something... row=" + row + " col=" + col);
					  //charNum++;
				  }
			  }
			  
		  }
	  }
	  System.out.println("Final decrypted message: " + message.substring(0, message.length()-3));
  }
  
  public void stego2(String mess) {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel pixel = null;
	  String message = mess + "   ";//space in binary is 00100000, detect three of these to stop decrypting
	  String s = "";
	  int charNum = 0;
	  int counter = 0;
	  int messCharNum = 0;
	  int blue = 0;
	  String newBlueBinary = "";
	  int newBlue = 0;//only change blue value, not all RGB values
	  //int charToInt = 0;
	  boolean stop = false;
	  
	  //int charToInt;// = Integer.parseInt(c, 2);
	  
	  for (int row = 0; row < pixels.length; row++) {
		  for (int col = 0; col < pixels[0].length; col++) {
			  pixel = pixels[row][col];
//			  System.out.println("this ran");
			  
//			  System.out.println(stop);
			  if (counter < (message.length()*8) && !stop) {
				  blue = pixel.getBlue();
				  char c = message.charAt(messCharNum);//finds char in message at messCharNum index
				  s = Integer.toString(c, 2);//sets s to binary value of c, tested and works
				  if (s.length() < 8) {
					  //add 0's to the front
					  for (int x = 0; s.length() < 8; x++) {
						  s = "0" + s;
					  }
				  }
				  newBlueBinary = Integer.toBinaryString(blue);
				  if (newBlueBinary.length() < 8) {
					  //add 0's to the front
					  for (int x = 0; newBlueBinary.length() < 8; x++) {
						  newBlueBinary = "0" + newBlueBinary;
					  }
				  }
				  System.out.println(newBlueBinary);
				  newBlueBinary = newBlueBinary.substring(0, newBlueBinary.length()-1) + s.charAt(charNum);
				  System.out.println(newBlueBinary);
				  //System.out.println(Integer.toString('A', 2));
				  System.out.println(s);
				  //System.out.println(s.charAt(7));//prints rightmost binary value
				  newBlue = Integer.parseInt(newBlueBinary, 2);
//				  System.out.println(charToInt);
				  
				  pixel.setBlue(newBlue);
				  
				  System.out.println("this actually did something... row=" + row + " col=" + col);
				  counter++;
				  if (charNum >= 7) {
					  charNum = 0;
					  messCharNum++;
				  }
				  else {
					  charNum++;
				  }
				  if (messCharNum > message.length()) {
					  stop = true;
				  }
			  }
			  
		  }
	  }
  }
  
  public void decrypt2() {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel pixel = null;
	  String message = "";//space in binary is 00100000, detect three of these to stop decrypting
	  String s = "";
	  char current = '0';
	  int blue = 0;
	  String blueBinary = "";
	  //int charToInt = 0;
	  boolean stop = false;
	  
	  //int charToInt;// = Integer.parseInt(c, 2);
	  System.out.println("----------------------now decrypting-------------------------");
	  for (int row = 0; row < pixels.length; row++) {
		  for (int col = 0; col < pixels[0].length; col++) {
			  pixel = pixels[row][col];
			  if (!stop) {
				  blue = pixel.getBlue();
				  blueBinary = Integer.toBinaryString(blue);
				  current = blueBinary.charAt(blueBinary.length()-1);
				  s = s + current;
				  
				  System.out.println("this is decrypting... row=" + row + " col=" + col);
				  if (s.length() >= 8) {
					  char currentChar = (char)Integer.parseInt(s, 2);
					  message = message + currentChar;
					  System.out.println(currentChar);
					  s = "";
					  System.out.println("So far: " + message);
				  }
				  if (message.length() > 3) {
					  if (message.contains("   ")) {
						  stop = true;
					  }
				  }
			  }
			  
		  }
	  }
	  System.out.println("Final decrypted message: " + message.substring(0, message.length()-3));
  }
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
