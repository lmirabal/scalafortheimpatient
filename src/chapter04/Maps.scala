package chapter04

import scala.io.Source
import scala.collection.immutable.TreeMap
import scala.collection.mutable

/**
  * Date: 09/05/13
 * Time: 20:42
 */
object Maps extends App{

  val gizmos = Map("iPhone 5" -> 650D, "iPad" -> 499.50D, "Laptop" -> 1150.20D)
  printf("1. full price = %s discounted price = %s\n",gizmos, gizmos.transform((_,price) => price * 0.9))

  import java.util.Scanner
  val wordCounter2 = collection.mutable.Map[String, Int]().withDefaultValue(0)
  val in2 = new Scanner(new java.io.File("resources/words.txt"))
  while(in2.hasNext) {
    val word = in2.next().toLowerCase.replaceAll("[.,]", "")
    wordCounter2(word) += 1
  }
  println("2. words = " + wordCounter2)

  val words3 = Source.fromFile("resources/words.txt").mkString.toLowerCase.split("[ ,.\n]+")
  var wordCounter3 = words3.foldLeft(Map[String,Int]()) { (map,word) => map + (word -> (map.getOrElse(word, 0) + 1)) }
  println("3. words = " + wordCounter3)

  val words4 = Source.fromFile("resources/words.txt").mkString.toLowerCase.split("[ ,.\n]+")
  val wordCounter4 = words4.foldLeft(TreeMap[String,Int]()) { (map,word) => map + (word -> (map.getOrElse(word, 0) + 1)) }
  println("4. words = " + wordCounter4)

  import scala.collection.JavaConversions.mapAsScalaMap
  val wordCounter5 = new java.util.TreeMap[String, Int]()
  val in5 = new Scanner(new java.io.File("resources/words.txt"))
  while(in5.hasNext) {
    val word = in5.next().toLowerCase.replaceAll("[.,]", "")
    if(!wordCounter5.containsKey(word)) wordCounter5(word) = 0
    wordCounter5(word) += 1
  }
  println("5. words = " + wordCounter5)

  import java.util.Calendar._
  val days = mutable.LinkedHashMap("Monday" -> MONDAY,
    "Tuesday" -> TUESDAY,
    "Wednesday" -> WEDNESDAY,
    "Thursday" -> THURSDAY,
    "Friday" -> FRIDAY,
    "Saturday" -> SATURDAY,
    "Sunday" -> SUNDAY)
  println("6. Days = " + days)

  import scala.collection.JavaConversions.propertiesAsScalaMap
  var properties: mutable.Map[String, String] = System.getProperties
  //var maxLength = properties.keySet.maxBy(_.length).length
  var maxLength = properties.map(_._1.length).max
  println("7. System properties:")
  properties.foreach (p => printf("%s %s | %s %n", p._1, " " * (maxLength - p._1.length), p._2))

  def minmax(values: Array[Int]) = {
    (values.min, values.max)
  }
  val values = Array(21,43,87,10,-23,-11)
  val minmaxTuple = minmax(values)
  printf("8. values = %s min = %d max = %d %n", values.toList, minmaxTuple._1, minmaxTuple._2)

  def lteqgt(values: Array[Int], v: Int) = {
    (values.filter(_ < v).size, values.filter(_ == v).size, values.filter(_ > v).size)
  }
  val lteqgtTuple = lteqgt(values, 10)
  printf("9. values = %s lt = %d eq = %d gt = %d %n", values.toList, lteqgtTuple._1, lteqgtTuple._2, lteqgtTuple._3)

  println("10. " + "Hello".zip("World"))
}
