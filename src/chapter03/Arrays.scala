package chapter03

import scala.util.Random

/**
 * Date: 07/05/13
 * Time: 22:30
 */
object Arrays extends App{

  var x = Array(1,2,3,4,5)

  def randomArray(size: Int) = {
    for(i <- 1 to size) yield Random.nextInt(size)
  }
  val a = randomArray(10)
  println("1. " + a)

  def swapAdjacentElements(values: Array[Int]) {
    val n = values.length
    for (i <- 0 until n by 2 if n - i > 1){
      val value1 = values(i)
      values(i) = values(i + 1)
      values(i+1) = value1
    }
  }
  var original = x.clone()
  var swapped = x.clone()
  swapAdjacentElements(swapped)
  printf("2. original = %s swapped = %s\n", original.toList, swapped.toList)

  var swapped2 = (for (pair <- x.grouped(2)) yield pair.reverse).flatten
  printf("3. original = %s swapped = %s\n", x.toList, swapped2.toList)
}
