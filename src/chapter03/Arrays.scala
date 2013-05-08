package chapter03

import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import java.util.TimeZone

/**
 * Date: 07/05/13
 * Time: 22:30
 */
object Arrays extends App{

  val x = Array(1,0,-3,-4,5)
  val y = Array(1.05, 3.67, -4.11, 12.18)
  val z = ArrayBuffer(1,0,-3,-4,5)
  var v: Array[Int] = null
  var w: ArrayBuffer[Int] = null
  var result: Array[Int] = null

  def randomArray(size: Int) = {
    (for(i <- 1 to size) yield Random.nextInt(size)).toArray
  }
  result = randomArray(10)
  println("1. " + result.toList)

  def swapAdjacentElements(values: Array[Int]) {
    val n = values.length
    for (i <- 0 until n by 2 if n - i > 1){
      val value1 = values(i)
      values(i) = values(i + 1)
      values(i+1) = value1
    }
  }
  result = x.clone()
  swapAdjacentElements(result)
  printf("2. original = %s swapped = %s\n", x.toList, result.toList)

  result = (for (pair <- x.grouped(2)) yield pair.reverse).flatten.toArray
  printf("3. original = %s swapped = %s\n", x.toList, result.toList)

  def positivesThenZerosAndNegatives(values: Array[Int]) = {
    Array.concat(for (value <- values if value > 0) yield value,
      for (value <- values if value == 0) yield value,
      for (value <- values if value < 0) yield value)
  }
  printf("4. original = %s result = %s\n", x.toList, positivesThenZerosAndNegatives(x).toList)
  result = x.filter(_ > 0) ++ x.filter(_ == 0) ++ x.filter(_ < 0)
  printf("4. original = %s result = %s\n", x.toList, positivesThenZerosAndNegatives(x).toList)

  printf("5. array = %s average = %f\n", y.toList, y.sum/y.length)

  printf("6. original = %s reverse sorted order = %s\n", x.toList, x.sorted.reverse.toList)
  printf("6. original = %s reverse sorted order = %s\n", z, z.sorted.reverse)

  v = Array(1,2,3,2,1,1,4,5,3)
  printf("7. original = %s no duplicates = %s\n", v.toList, v.distinct.toList)

  //book's solution
  w = z.clone()
  var first = true
  var indexes = for(i <- 0 until w.length if first || w(i) > 0) yield { if(w(i) < 0) first = false ; i }
  for(j <- 0 until indexes.length) w(j) = w(indexes(j))
  w.trimEnd(w.length - indexes.length)
  printf("8. original = %s result = %s\n", z, w)
  //suggested solution
  w = z.clone()
  indexes = (for(i <- 0 until w.length if w(i) < 0) yield i).reverse.dropRight(1)
  for(j <- 0 until indexes.length) w.remove(indexes(j))
  printf("8. original = %s result = %s\n", z, w)
  //other solution
  printf("8. original = %s result = %s\n", z, z.diff(z.filter(_ < 0).reverse.dropRight(1)))

  val prefix = "America/"
  println("9. America time zones = " + TimeZone.getAvailableIDs.filter(_.startsWith(prefix)).transform(_.stripPrefix(prefix)))

  import java.awt.datatransfer._
  import scala.collection.JavaConversions.asScalaBuffer
  def flavors(): collection.mutable.Buffer[String] = {
    val flavors = SystemFlavorMap.getDefaultFlavorMap.asInstanceOf[SystemFlavorMap]
    flavors.getNativesForFlavor(DataFlavor.imageFlavor)
  }
  println("10. Flavors = " + flavors)
}
