package chapter06

/**
 * Date: 16/05/13
 * Time: 20:07
 */
object Objects extends App{

  object Conversions {
    def inchesToCentimeters(inches: Double) = inches * 2.54

    def gallonsToLiters(gallons: Double) = gallons * 4.54609

    def milesToKilometers(miles: Double) = miles * 1.609344
  }
  val inches = 34.5
  printf("1. inches = %.2f centimeters = %.2f %n", inches, Conversions.inchesToCentimeters(inches))
  val gallons = 20.34
  printf("1. gallons = %.2f liters = %.2f %n", gallons, Conversions.gallonsToLiters(gallons))
  val miles = 14.8
  printf("1. miles = %.2f kilometers = %.2f %n", miles, Conversions.milesToKilometers(miles))

  class UnitConversion(scale: Double) {
    def convert(value: Double) = value * scale
  }
  object InchesToCentimeters extends UnitConversion(2.54)
  object GallonsToLiters extends UnitConversion(4.54609)
  object MilesToKilometers extends UnitConversion(1.609344)
  printf("2. inches = %.2f centimeters = %.2f %n", inches, InchesToCentimeters.convert(inches))
  printf("2. gallons = %.2f liters = %.2f %n", gallons, GallonsToLiters.convert(gallons))
  printf("2. miles = %.2f kilometers = %.2f %n", miles, MilesToKilometers.convert(miles))

  object Origin extends java.awt.Point
  // Origin should be constant and the class Point allow changes in the x,y fields.
  println("3. Origin = " + Origin)
  Origin.setLocation(3,10)
  println("3. Origin = " + Origin)

  class Point(var x: Int, var y: Int){
    override def toString: String = "[%d,%d]".format(x, y)
  }
  object Point {
    def apply(x: Int, y: Int) = new Point(x, y)
  }
  println("4. Point = " + Point(3, 4))

  // It can be run from the command line passing the arguments, but this makes it easier to run
  print("5. ")
  Reverse.main(Array("Hello", "World"))

  object CardSuit extends Enumeration {
    type CardSuit = Value

    val Spade = Value("♠")
    val Heart = Value("♡")
    val Diamond = Value("♢")
    val Club = Value("♣")

    def isRed(suit: CardSuit) = suit.equals(Heart) || suit.equals(Diamond)
  }
  print("6. ")
  for(cardSuit <- CardSuit.values) print(cardSuit + " ")
  println()

  print("7. ")
  for(cardSuit <- CardSuit.values) printf("suit = %s is red? %b ", cardSuit, CardSuit.isRed(cardSuit))
  println()


  object RgbCorner extends Enumeration {
    type RgbCorner = Value

    val Black = Value(0x000000)
    val Red =  Value(0xff0000)
    val Green = Value(0x00ff00)
    val Blue = Value(0x0000ff)
    val Yellow = Value(0xffff00)
    val Magenta = Value(0xff00ff)
    val Cyan = Value(0x00ffff)
    val White = Value(0xffffff)
  }
  print("8. ")
  for(rgb <- RgbCorner.values) print("name = %s value = 0x%06x %n".format(rgb,rgb.id))
}
