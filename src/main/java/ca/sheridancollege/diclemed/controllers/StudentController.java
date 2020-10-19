package ca.sheridancollege.diclemed.controllers;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.diclemed.beans.Student;
import ca.sheridancollege.diclemed.database.DatabaseAccess;

@Controller
public class StudentController {

	@Autowired
	private DatabaseAccess da; // Create instance of DatabaseAccess class
	
	List<Student> studentList = new CopyOnWriteArrayList<Student>();
	
	@GetMapping("/")
	public String goHome(Model model) {
		// call insertStudent() in DatabaseAccess to add our student from form into STUDENT table
		da.insertStudent();
		model.addAttribute("student", new Student());
		model.addAttribute("studentList", da.getStudents());
		return "index";
	}
	
	@PostMapping("/insertStudent")
	public String addStudent(Model model, @RequestParam String name) {
		
		da.insertStudent(name);
		model.addAttribute("student", new Student());
		model.addAttribute("studentList", da.getStudents());
		
		return "index";
	}
	
	@PostMapping("/searchStudent")
	public String searchStudent(Model model, @RequestParam String name) {
		
		model.addAttribute("student", new Student());
		model.addAttribute("searchStudentList", da.getStudents(name));
		return "index";
	}
	
	@GetMapping("/deleteStudent/{id}")
	public String deleteStudent(Model model, @PathVariable Long id) {
		
		da.deleteStudent(id);
		model.addAttribute("student", new Student());
		model.addAttribute("studentList", da.getStudents());
		return "index";
	}
	
	@GetMapping("/editStudent/{id}")
	public String editStudent(Model model, @PathVariable Long id) {
		
		Student student = da.getStudentById(id).get(0);
		model.addAttribute("student", student); // makes the info of the deleted student show in the text field
		da.deleteStudent(id);
		model.addAttribute("studentList", da.getStudents());
		return "index";
	}
}






