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
  val counter = new Counter
  printf("1. current = " + counter.current)
  for(i <- 0 to increments) { counter.increment();  print(" increment = " + counter.current) }
  println()

  class BankAccount(openingBalance: Double) {
    private var currentBalance = openingBalance

    def deposit(amount: Double) { currentBalance += amount }
    def withdraw(amount: Double) { currentBalance -= amount }
    def balance = currentBalance //TODO: There must be a better way to do this
  }
  val account = new BankAccount(1000)
  print("2. opening balance = " + account.balance)
  val depositAmount = 5500.55
  account.deposit(depositAmount)
  printf(" [deposit = £%.2f new balance = £%.2f]", depositAmount, account.balance)
  val withdrawalAmount = 3333.25
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
  val student1 = new Student("Linus", 1)
  val student2 = new Student("Richard", 2)
  printf("5. Student: name = %s id = %d %n", student1.getName, student1.getId)
  printf("5. Student: name = %s id = %d %n", student2.name, student2.id)
  //You can but you should not, as they exist only for java compatibility

  class Person(_age: Int) {
    val age = if(_age > 0) _age else 0
  }
  val person1 = new Person(29)
  val person2 = new Person(-10)
  println("6. person age = " + person1.age)
  println("6. person age = " + person2.age)

  //It's better to use a plain parameter as we don't want to keep the full name as String
  class NamedPerson(_fullName: String) {
    private val fullName = _fullName.split(" ")
    val firstName = fullName(0)
    val lastName = fullName(1)
  }
  val person3 = new NamedPerson("Luis Mirabal")
  printf("7. first name = %s last name = %s %n", person3.firstName, person3.lastName)

  //The more general constructor is primary as it can be called by any other constructor with any combination of values
  class Car(val manufacturer: String, val modelName: String, val modelYear: Int, var licensePlate: String) {
    def this(manufacturer: String, modelName: String){ this(manufacturer, modelName, -1, "") }
    def this(manufacturer: String, modelName: String, modelYear: Int){ this(manufacturer, modelName, modelYear, "") }
    def this(manufacturer: String, modelName: String, licensePlate: String){ this(manufacturer, modelName, -1, licensePlate) }

    override def toString: String = "[manufacturer = %s, model = %s, year = %d, plate = %s]".format(manufacturer, modelName, modelYear, licensePlate)
  }
  println("8. " + new Car("Ford", "Focus", 2005, "RV55 ERJ"))
  println("8. " + new Car("Volkswagen", "Golf"))
  println("8. " + new Car("Audi", "A5", 2012))
  println("8. " + new Car("Porsche", "Cayman", "LF63 GTY"))

  //Far shorter in Scala. 68 lines long in Java
  println("9. " + new JavaCar("Ford", "Focus", 2005, "RV55 ERJ"))
  println("9. " + new JavaCar("Volkswagen", "Golf"))
  println("9. " + new JavaCar("Audi", "A5", 2012))
  println("9. " + new JavaCar("Porsche", "Cayman", "LF63 GTY"))

  //Default primary constructor and explicit fields
  class Employee(_name: String = "John Q. Public", _salary: Double = 0.0) {
    val name: String = _name
    val salary: Double = _salary
  }
  //It's better to use this version as all you need is one constructor and no explicit fields
  class Employee2(val name: String = "John Q. Public", var salary: Double = 0.0) {
    override def toString: String = "[name = %s, salary = %.2f]".format(name, salary)
  }
  println("10. " + new Employee2("Luis Mirabal", 3000.36))
  println("10. " + new Employee2(salary = 5500.50))
  println("10. " + new Employee2)
}
