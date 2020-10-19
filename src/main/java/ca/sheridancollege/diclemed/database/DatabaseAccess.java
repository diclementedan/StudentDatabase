/* 
 *  Name: Dan Di Clemente
 */

package ca.sheridancollege.diclemed.database;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.diclemed.beans.Student;

@Repository
public class DatabaseAccess {

	// Provides access to our database and we can write methods containing queries
	// into this file and run them from controller
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public void insertStudent() {
		String query = "INSERT INTO student(name) VALUES ('FRANK')";
		int rowsAffected = jdbc.update(query, new HashMap());
		if (rowsAffected > 0) {
			System.out.println("Inserted student into database");
		}
	}
	
	public void insertStudent(String name) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		
		// use single quotes for values inside the double quotes
		String query = "INSERT INTO student(name) VALUES(:name)";
		namedParameters.addValue("name", name);
		
		// .update is a method that executes query
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0)
			System.out.println("Inserted student into database");
	}
	
	public List<Student> getStudents() {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM student";
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
	}
	
	public List<Student> getStudents(String name) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM student WHERE name = :name";
		namedParameters.addValue("name", name);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
	}
	
	public void deleteStudent(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		// DELETE FROM <tablename> WHERE <column-name> = :value
		String query = "DELETE FROM student WHERE id = :id";
		namedParameters.addValue("id", id); // id is the parameter, must be written same as previous line
		int rowsAffected = jdbc.update(query, namedParameters);
		if (rowsAffected > 0) {
			System.out.println("Deleted student with id = " + id + " from student");
		}
	}
	
	public List<Student> getStudentById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "SELECT * FROM student WHERE id = :id";
		namedParameters.addValue("id", id);
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Student>(Student.class));
	}
}
