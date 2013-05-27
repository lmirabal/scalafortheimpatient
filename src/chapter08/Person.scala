package chapter08

/**
 * Date: 27/05/13
 * Time: 22:03
 */
class Person(val name: String) {
  override def toString = getClass.getName + "[name=" + name + "]"
}

//public class chapter08.Person {
//  private final java.lang.String name;
//
//  public java.lang.String name();
//    Code:
//       0: aload_0
//       1: getfield      #13                 // Field name:Ljava/lang/String;
//       4: areturn
//
//  public java.lang.String toString();
//    Code:
//       0: new           #18                 // class scala/collection/mutable/StringBuilder
//       3: dup
//       4: invokespecial #22                 // Method scala/collection/mutable/StringBuilder."<init>":()V
//       7: aload_0
//       8: invokevirtual #26                 // Method java/lang/Object.getClass:()Ljava/lang/Class;
//      11: invokevirtual #31                 // Method java/lang/Class.getName:()Ljava/lang/String;
//      14: invokevirtual #35                 // Method scala/collection/mutable/StringBuilder.append:(Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
//      17: ldc           #37                 // String [name=
//      19: invokevirtual #35                 // Method scala/collection/mutable/StringBuilder.append:(Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
//      22: aload_0
//      23: invokevirtual #39                 // Method name:()Ljava/lang/String;
//      26: invokevirtual #35                 // Method scala/collection/mutable/StringBuilder.append:(Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
//      29: ldc           #41                 // String ]
//      31: invokevirtual #35                 // Method scala/collection/mutable/StringBuilder.append:(Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
//      34: invokevirtual #43                 // Method scala/collection/mutable/StringBuilder.toString:()Ljava/lang/String;
//      37: areturn
//
//  public chapter08.Person(java.lang.String);
//    Code:
//       0: aload_0
//       1: aload_1
//       2: putfield      #13                 // Field name:Ljava/lang/String;
//       5: aload_0
//       6: invokespecial #45                 // Method java/lang/Object."<init>":()V
//       9: return
//}

class SecretAgent(codename: String) extends Person(codename) {
  override val name = "secret" // Don't want to reveal name
  override val toString = "secret"
}

//public class chapter08.SecretAgent extends chapter08.Person {
//  private final java.lang.String name;
//
//  private final java.lang.String toString;
//
//  public java.lang.String name();
//    Code:
//       0: aload_0
//       1: getfield      #14                 // Field name:Ljava/lang/String;
//       4: areturn
//
//  public java.lang.String toString();
//    Code:
//       0: aload_0
//       1: getfield      #18                 // Field toString:Ljava/lang/String;
//       4: areturn
//
//  public chapter08.SecretAgent(java.lang.String);
//    Code:
//       0: aload_0
//       1: aload_1
//       2: invokespecial #22                 // Method chapter08/Person."<init>":(Ljava/lang/String;)V
//       5: aload_0
//       6: ldc           #24                 // String secret
//       8: putfield      #14                 // Field name:Ljava/lang/String;
//      11: aload_0
//      12: ldc           #24                 // String secret
//      14: putfield      #18                 // Field toString:Ljava/lang/String;
//      17: return
//}
