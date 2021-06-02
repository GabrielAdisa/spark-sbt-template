import org.scalatest.FunSuite

class AppTest extends FunSuite {

  val testLog: List[String] =
    List("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

  /*
  test("An empty List should have size 0") {
    assert(List.empty.size === 0)
  }
   */
  test("test that the first element of the list is assigned to IP") {
    assert(testLog.head === DstiJob.toAccessLog(testLog).ip)
  }

  test("test that the last element of the list is assigned to UNK") {
    assert(testLog.last === DstiJob.toAccessLog(testLog).unk)
  }

  test("Accessing invalid index should throw IndexOutOfBoundsException") {
    assertThrows[IndexOutOfBoundsException] {
      testLog(11)
    }
  }
}
