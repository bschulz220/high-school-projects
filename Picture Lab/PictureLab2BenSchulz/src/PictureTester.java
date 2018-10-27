import java.util.Scanner;

/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.explore();
    swan.edgeDetection(10);
    swan.explore();
  }
  
  public static void testKeepOnlyBlue() {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.keepOnlyBlue();
	  beach.explore();
  }
  
  public static void testNegate() {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.negate();
	  beach.explore();
  }
  
  public static void testGrayscale() {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.grayscale();
	  beach.explore();
  }
  
  public static void testFixUnderwater() {
	  Picture underwater = new Picture("water.jpg");
	  underwater.explore();
	  underwater.fixUnderwater();
	  underwater.explore();
  }
  
  public static void testMirrorVerticalRightToLeft() {
	  Picture caterpillar = new Picture("caterpillar.jpg");
	  caterpillar.explore();
	  caterpillar.mirrorVerticalRightToLeft();
	  caterpillar.explore();
  }
  
  public static void testMirrorHorizontal() {
	  Picture motorcycle = new Picture("redMotorcycle.jpg");
	  motorcycle.explore();
	  motorcycle.mirrorHorizontal();
	  motorcycle.explore();
  }
  
  public static void testMirrorHorizontalBotToTop() {
	  Picture motorcycle = new Picture("redMotorcycle.jpg");
	  motorcycle.explore();
	  motorcycle.mirrorHorizontalBotToTop();
	  motorcycle.explore();
  }
  
  public static void testMirrorDiagonal() {
	  Picture beach = new Picture("beach.jpg");
	  beach.explore();
	  beach.mirrorDiagonal();
	  beach.explore();
  }
  
  public static void testMirrorArms() {
	  Picture snowman = new Picture("snowman.jpg");
	  snowman.explore();
	  snowman.mirrorArms();
	  snowman.explore();
  }
  
  public static void testMirrorGull() {
	  Picture seagull = new Picture("seagull.jpg");
	  seagull.explore();
	  seagull.mirrorGull();
	  seagull.explore();
  }
  
  public static void testNewCopy() {
	  Picture seagull = new Picture("seagull.jpg");
	  Picture snowman = new Picture("snowman.jpg");
	  seagull.explore();
	  seagull.copy(snowman, 100, 100, 200, 200);
	  seagull.explore();
  }
  
  public static void testMyCollage() {
	  Picture seagull = new Picture("seagull.jpg");
	  Picture snowman = new Picture("snowman.jpg");
//	  Picture redMotorcycle = new Picture("redMotorcyle.jpg");
//	  Picture blueMotorcycle = new Picture("blueMotorcyle.jpg");
	  Picture redMotorcycle = new Picture("beach.jpg");
	  Picture blueMotorcycle = new Picture("temple.jpg");
//	  seagull.explore();
//	  snowman.explore();
	  redMotorcycle.explore();
//	  blueMotorcycle.explore();
	  redMotorcycle.myCollage(redMotorcycle, seagull, snowman, blueMotorcycle);
	  redMotorcycle.explore();
  }
  
  public static void testEdgeDetection2() {
    Picture swan = new Picture("swan.jpg");
    swan.explore();
    swan.edgeDetection2(250);
    swan.explore();
  }
  
  public static void lightsaber() {
	  Picture light = new Picture("lightsaberBlue.jpg");
	  Picture bridge = new Picture("bridge.jpg");
	  Picture nms = new Picture("nms.jpg");
	  //light.explore();
	  light.chroma(bridge); //for Enterprise background
	  //light.chroma(nms); //for No Man's Sky background
	  light.write("darksaber.jpg");
	  light.explore();
  }
  
  public static void lightsaberRedScreen() {
	  Picture light = new Picture("lightsaberBlueOnRed.jpg");
	  Picture bridge = new Picture("bridge.jpg");
	  Picture nms = new Picture("nms.jpg");
	  //light.explore();
	  light.chromaRed(bridge);
	  //light.chromaRed(nms);
	  light.explore();
  }
  
  public static void testStego1() {
	  Scanner textScanner = new Scanner(System.in);
	  String userMess = "";
	  Picture cycle = new Picture("redMotorcycle.jpg");
	  System.out.println("What message would you like to encrypt?");
	  while (userMess == "") {
		  userMess = textScanner.nextLine();
	  }
	  cycle.explore();
	  cycle.stego1(userMess);
	  cycle.explore();
	  cycle.decrypt1();
  }
  
  public static void testStego2() {
	  Scanner textScanner = new Scanner(System.in);
	  String userMess = "";
	  Picture cycle = new Picture("redMotorcycle.jpg");
	  System.out.println("What message would you like to encrypt?");
	  while (userMess == "") {
		  userMess = textScanner.nextLine();
	  }
	  cycle.explore();
	  cycle.stego2(userMess);
	  cycle.explore();
	  cycle.decrypt2();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
//    testKeepOnlyRed();
//    testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
//    testMirrorVertical();
//	testMirrorVerticalRightToLeft();
//	testMirrorHorizontal();
//	testMirrorHorizontalBotToTop();
//	  testMirrorDiagonal();
//    testMirrorTemple();
//    testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
	//testNewCopy();
	//testMyCollage();
//    testCollage();
//    testCopy();
//    testEdgeDetection();
//    testEdgeDetection2();
	//lightsaber();
	//lightsaberRedScreen();
	  //testStego1();
	  testStego2();
//    testEncodeAndDecode();
//    testGetCountRedOverValue(250);
//    testSetRedToHalfValueInTopHalf();
//    testClearBlueOverValue(200);
//    testGetAverageForColumn(0);
  }
}