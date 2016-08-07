object Solution {

  val library  = List( Book("A","T1"), Book("B","T2"), Book("C","T3"), Book("D","T4"), Book("E","T5"))

  val p1 = new Person("F1", List(library(3),library(1)))
  val p2 = new Person("F2", List(library(3),library(2)))
  val p3 = new Person("F3", List(library(3),library(3)))
  val p4 = new Person("F4", List(library(3),library(1)))

  val me = new Person("RG",List(library(0),library(1)))
  me.friends = List(p1,p2,p3)

  case class Book(Author: String, Title: String)
  case class Person(name: String, var friends : List[Person], books: List[Book]) {
    def this(name: String) {
      this(name, List(), List())
    }
    def this(name: String, books: List[Book] ) {
      this(name, List(), books)
    }
  }

  def mostFrequentlyRead(person: Person): Book = {

    println("Library " + library)
    println("My books " + me.books.mkString)
    println("My friends " + me.friends.mkString("\n"))

    var booksMap: Map[Book, Int] = Map.empty
    println("booksMap " + booksMap)
    me.books.foreach(b => {
      var count = {
        if (booksMap.isDefinedAt(b)) booksMap(b)
        else 0}

      count += 1
      booksMap += (b -> count)
    })

    println("booksMap " + booksMap)
    //books read by my friends
    me.friends.map(f => {
      println(f.name)
      f.books.foreach(b => {
        var count = {
          if (booksMap.isDefinedAt(b)) booksMap(b)
          else 0
        }
        count += 1
        println("inside friend loop")
        booksMap += (b -> count)
      })
    })
    println("booksMap " + booksMap)

    //the most frequently read book
    //booksMap.toList.sortWith( (b1, b2) => (b1._2 > b2._2))
    //println(booksMap.toList.sortWith( (b1, b2) => (b1._2 > b2._2)))

    val result: Book = booksMap.toList.sortWith( (b1, b2) => (b1._2 > b2._2)).map(b => b._1).head
    println(result)

    result
  }

  def main(args: Array[String]) {
    println("MostFrequentlyRead: " + mostFrequentlyRead(me).Title)

  }
}
