package chapter08

import scala.collection.mutable

/**
 * Date: 21/05/13
 * Time: 22:37
 */
object Inheritance extends App{

  class BankAccount(initialBalance: Double) {
    protected var balance = initialBalance

    def deposit(amount: Double) = {
      balance += amount
      balance
    }

    def withdraw(amount: Double) = {
      balance -= amount
      balance
    }
  }
  class CheckingAccount(balance: Double) extends BankAccount(balance) {
    private val CHARGE = 1

    override def deposit(amount: Double) = super.deposit(amount - CHARGE)

    override def withdraw(amount: Double): Double = super.withdraw(amount + CHARGE)
  }
  val account = new BankAccount(1000)
  val checkingAccount = new CheckingAccount(1000)
  println(s"1. Account = ${account.getClass.getSimpleName} deposit = ${account.deposit(500)} withdrawal = ${account.withdraw(200)}")
  println(s"1. Account = ${checkingAccount.getClass.getSimpleName} deposit = ${checkingAccount.deposit(500)} withdrawal = ${checkingAccount.withdraw(200)}")

  class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    private val CHARGE = 1
    private val INTEREST = 0.01
    private val FREE_TRANSACTIONS = 3

    private var transactionCounter = 0

    def earnMonthlyInterest() = {
      transactionCounter = 0
      balance += balance * INTEREST
      balance
    }
    override def deposit(amount: Double) = {
      transactionCounter += 1
      super.deposit(if (transactionCounter <= FREE_TRANSACTIONS) amount else amount - CHARGE)
    }

    override def withdraw(amount: Double) = {
      transactionCounter += 1
      super.withdraw(if (transactionCounter <= FREE_TRANSACTIONS) amount else amount + CHARGE)
    }
  }
  val savingsAccount = new SavingsAccount(2500)
  var amount = 500
  println(s"2. Deposit = $amount Balance = ${savingsAccount.deposit(amount)}")
  amount = 1100
  println(s"2. Withdrawal = $amount Balance = ${savingsAccount.withdraw(amount)}")
  amount = 200
  println(s"2. Withdrawal = $amount Balance = ${savingsAccount.withdraw(amount)}")
  amount = 301
  println(s"2. Deposit = $amount Balance = ${savingsAccount.deposit(amount)}")
  println(s"2. Balance after monthly interest = ${savingsAccount.earnMonthlyInterest()}")

  abstract class Person(val name: String, val address: String) {
    def printDetails() {
      print(s"3. Name: $name. Address: $address.")
    }
  }
  class Student(name: String, address: String) extends Person(name, address){
    var grades = mutable.Map[String,Double]()

    def addCourseGrade(course: String, grade: Double) {
      grades(course) = grade
    }

    def averageGrade = grades.values.sum / grades.size

    override def printDetails() {
      super.printDetails()
      println(s" Avg: $averageGrade")
    }
  }
  class Teacher(name: String, address: String) extends Person(name, address) {
    var courses = mutable.Map[String, Int]()

    def addCourse(course: String, year: Int) {
      courses(course) = year
    }

    override def printDetails() {
      super.printDetails()
      print(" Courses: ")
      for ((course, year) <- courses) print(s"$course($year). ")
      println()
    }
  }
  val student = new Student("Luis", "Isleworth")
  student.addCourseGrade("Development I", 18.55)
  student.addCourseGrade("Maths II", 20)
  student.printDetails()
  val teacher = new Teacher("Randy", "Barquisimeto")
  teacher.addCourse("Development III", 2005)
  teacher.addCourse("Development I", 2000)
  teacher.printDetails()

  abstract class Item {
    def price: Double
    def description: String
  }
  class SimpleItem(override val price: Double, override val description: String) extends Item
  class Bundle(initialItems: Item*) extends Item {
    private val items = initialItems.foldLeft(collection.mutable.ArrayBuffer[Item]())((seq, item) => seq += item)

    def addItem(item: Item) { items += item }

    def price: Double = items.map(_.price).sum

    def description: String = {
      s"[${items.map(_.description).mkString(", ")}]"
    }
  }
  val simpleItem = new SimpleItem(150, "Simple Item")
  val bundle = new Bundle(simpleItem, new SimpleItem(200, "Another Item"))
  bundle.addItem(new Bundle(new SimpleItem(50, "Cheap Item"), new SimpleItem(500, "Expensive Item")))
  println(s"4. Simple item. Price = ${simpleItem.price} Description= ${simpleItem.description}")
  println(s"4. Bundle. Price = ${bundle.price} Description = ${bundle.description}")

  class Point (val x: Double, val y: Double){
    override def toString: String = s"($x,$y)"
  }
  class LabeledPoint(private val label: String, x: Double, y: Double) extends Point(x, y) {
    override def toString: String = s"$label: ${super.toString}"
  }
  println(s"5. Point = ${new Point(2153.78, 1111.33)}")
  println(s"5. Labeled point = ${new LabeledPoint("Black Thursday", 1929, 230.07)}")

  abstract class Shape {
    def centrePoint: Point
  }
  class Rectangle(val width: Double, val height: Double) extends Shape {
    val centrePoint = new Point(width/2, height/2)
    override def toString: String = s"Rectangle: Centre = $centrePoint Width = $width Height = $height"
  }
  class Circle(val x: Double, val y: Double, val radius: Double) extends Shape {
    val centrePoint = new Point(x,y)
    override def toString: String = s"Circle: Centre = $centrePoint Radius = $radius"
  }
  println(s"6. ${new Rectangle(8,3)} | ${new Circle(3,7,11)}")

  object Point {
    def apply(x: Int, y: Int) = new Point(x, y)
  }
  class Square(corner: Point, width: Int) extends java.awt.Rectangle(corner.x.toInt, corner.y.toInt, width, width) {

    def this(width: Int) { this(Point(0,0), width) }

    def this() { this(0) }
  }
  var square = new Square(Point(3,5), 10)
  println(s"7. Square = $square")
  square = new Square(10)
  println(s"7. Square = $square")
  square = new Square()
  println(s"7. Square = $square")

  //Definition and output from javap in separate file
  val person = new SecretAgent("Luis")
  println(s"8. There are 2 name fields and 2 name getters. Agent name = ${person.name}")

  class Creature {
//    val range: Int = 10
    def range: Int = 10
    val env: Array[Int] = new Array[Int](range)
  }
  class DefAnt extends Creature {
    override def range = 2
  }
  class ValAnt extends Creature {
    override val range = 2
  }
  println(s"9. DefAnt = ${new DefAnt().env.length}")
  println(s"9. ValAnt = ${new ValAnt().env.length}")
  println("9. Changing range to be def on the base class makes it a method, which is overridable from subclasses")
  println("9. Using def in the subclass makes it a method, which overrides the parent class")
  println("9. Using val in the subclass makes it a field, which is not initialised when the base class constructor is called")

  println("10. Protected before the param means the constructor is private")
  println("10. Protected in the param definition means the field is protected")
}
