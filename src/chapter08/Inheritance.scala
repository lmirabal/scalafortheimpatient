package chapter08

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
  printf("1. Account = %s deposit = %s withdrawal = %s %n", account.getClass.getSimpleName, account.deposit(500), account.withdraw(200))
  printf("1. Account = %s deposit = %s withdrawal = %s %n", checkingAccount.getClass.getSimpleName, checkingAccount.deposit(500), checkingAccount.withdraw(200))

  class SavingsAccount(initialBalance: Double) extends BankAccount(initialBalance) {
    private val CHARGE = 1
    private val INTEREST = 0.01
    private val FREE_TRANSACTIONS = 3

    private var transactionCounter = 0

    def earnMonthlyInterest() {
      balance += balance * INTEREST
      transactionCounter = 0
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
  //TODO Client code

  //TODO 3 to be implemented

  abstract class Item {
    def price: Double
    def description: String
  }
  class SimpleItem(override val price: Double, override val description: String) extends Item
  class Bundle extends Item {
    private val items = collection.mutable.ArrayBuffer[Item]()

    def addItem(item: Item) { items.append(item) }

    def price: Double = items.map(_.price).sum

    def description: String = {
      "Bundle: \n" + items.foreach(item => s"\t ${item.description} \n")
    }
  }
}
