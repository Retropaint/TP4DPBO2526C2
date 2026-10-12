public class Person {
  public int id;
  public String name;
  public int birthYear;
  public String email;

  public Person(int id, String name, int birthYear, String email) {
    this.id = id;
    this.name = name;
    this.birthYear = birthYear;
    this.email = email;
  }

  public Object[] asObj() {
    Object[] row = { id, name, birthYear, email };
    return row;
  }
}
