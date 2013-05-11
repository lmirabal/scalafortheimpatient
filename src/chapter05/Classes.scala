package chapter05

import scala.beans.BeanProperty

/**
 * Date: 11/05/13
 * Time: 21:08
 */
object Classes extends App {

  val increments = 3
  class Counter {
    private var value = Int.MaxValue - increments

    def current = value

    def increment() { if(value < Int.MaxValue) value += 1 }
  }
  var counter = new Counter
  printf("1. current = " + counter.current)
  for(i <- 0 to increments) { counter.increment();  print(" increment = " + counter.current) }
  println()

  class BankAccount(openingBalance: Double) {
    private var currentBalance = openingBalance

    def deposit(amount: Double) { currentBalance += amount }
    def withdraw(amount: Double) { currentBalance -= amount }
    def balance = currentBalance //TODO: There must be a better way to do this
  }
  var account = new BankAccount(1000)
  print("2. opening balance = " + account.balance)
  var depositAmount = 5500.55
  account.deposit(depositAmount)
  printf(" [deposit = £%.2f new balance = £%.2f]", depositAmount, account.balance)
  var withdrawalAmount = 3333.25
  account.withdraw(withdrawalAmount)
  printf(" [withdraw = £%.2f new balance = £%.2f] %n", withdrawalAmount, account.balance)

  class Time(private val hours: Byte, private val minutes: Byte) {
    if(hours < 0 || hours > 23) throw new IllegalArgumentException("Hours was %d but must be between [0,23]".format(hours))
    if(minutes < 0 || minutes > 59) throw new IllegalArgumentException("Minutes was %d must be between [0,59]".format(minutes))

    def before(other: Time) = hours < other.hours || (hours == other.hours && minutes < other.minutes)

    override def toString: String = "%02d:%02d".format(hours, minutes)
  }
  val time1 = new Time(10, 23)
  val time2 = new Time(10, 35)
  val time3 = new Time(11, 3)
  printf("3. %s < %s = %b %n", time1, time2, time1.before(time2))
  printf("3. %s < %s = %b %n", time1, time3, time1.before(time3))
  printf("3. %s < %s = %b %n", time3, time2, time3.before(time2))
  try { new Time(24, 60) } catch { case e: IllegalArgumentException => println("3. Exception = " + e) }
  try { new Time(0, 60) } catch { case e: IllegalArgumentException => println("3. Exception = " + e) }
  class RoundTime(_hours: Byte, _minutes: Byte) {
    private val hours = {
      if(_hours < 0) 0
      else if(_hours > 23) 23
      else _hours
    }

    private val minutes = {
      if(_minutes < 0) 0
      else if(_minutes > 59) 59
      else _minutes
    }

    def before(other: RoundTime) = hours < other.hours || (hours == other.hours && minutes < other.minutes)

    override def toString: String = "%02d:%02d".format(hours, minutes)
  }
  val time4 = new RoundTime(24, 60)
  val time5 = new RoundTime(-1, -10)
  printf("3. %s < %s = %b %n", time4, time5, time4.before(time5))

  class MinutesTime(hours: Byte, minutes: Byte) {
    //No validation this time
    private val totalMinutes = hours * 60 + minutes

    def before(other: MinutesTime) = totalMinutes < other.totalMinutes

    override def toString: String = "%d minutes since midnight".format(totalMinutes)
  }
  val time6 = new MinutesTime(10, 23)
  val time7 = new MinutesTime(10, 35)
  val time8 = new MinutesTime(11, 3)
  printf("4. %s < %s = %b %n", time6, time7, time6.before(time7))
  printf("4. %s < %s = %b %n", time6, time8, time6.before(time8))
  printf("4. %s < %s = %b %n", time8, time7, time8.before(time7))

  class Student(@BeanProperty var name: String, @BeanProperty var id: Long)
  var student1 = new Student("Linus", 1)
  var student2 = new Student("Richard", 2)
  printf("5. Student: name = %s id = %d %n", student1.getName, student1.getId)
  printf("5. Student: name = %s id = %d %n", student2.name, student2.id)
  //You can but you should not, as they exist only for java compatibility
}
