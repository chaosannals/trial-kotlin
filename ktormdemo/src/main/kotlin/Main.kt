import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select
import org.ktorm.schema.*

object Departments : Table<Nothing>("t_department") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val location = varchar("location")
}

object Employees : Table<Nothing>("t_employee") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val job = varchar("job")
    val managerId = int("manager_id")
    val hireDate = date("hire_date")
    val salary = long("salary")
    val departmentId = int("department_id")
}

fun main() {
//    Class.forName("com.mysql.jdbc.Driver")
    val database = Database.connect(
        "jdbc:mysql://127.0.0.1:3306/demo",
        user = "root",
        password = "123456"
    )

    println("start")

    for (row in database.from(Employees).select()) {
        println(row[Employees.name])
    }

    println("end")
}