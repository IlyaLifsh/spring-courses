package telran.courses;
//Ilya_HW60
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import telran.courses.dto.Course;
import telran.courses.service.CoursesService;

@SpringBootTest
@AutoConfigureTestDatabase
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CoursesServiceTests {
	private static final @NotEmpty String COURSE1 = "course1";
	private static final @NotEmpty String LECTURER1 = "lecturer1";
	private static final @Min(80) @Max(500) int HOURS1 = 101;
	private static final @Min(5000) @Max(20000) int COST1 = 10001;
	private static final @NotNull @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}.*") String DATE1 = "2022-01-01";
	private static final @NotEmpty String COURSE2 = "course2";
	private static final @NotEmpty String LECTURER2 = "lecturer2";
	private static final @Min(80) @Max(500) int HOURS2 = 102;
	private static final @Min(5000) @Max(20000) int COST2 = 10002;
	private static final @NotNull @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}.*") String DATE2 = "2022-02-02";
	private static final @NotEmpty String COURSE3 = "course3";
	private static final @NotEmpty String LECTURER3 = "lecturer3";
	private static final @Min(80) @Max(500) int HOURS3 = 103;
	private static final @Min(5000) @Max(20000) int COST3 = 10003;
	private static final @NotNull @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}.*") String DATE3 = "2022-03-03";
	static Course course1 = new Course(COURSE1, LECTURER1, HOURS1, COST1, DATE1);
	static Course course2 = new Course(COURSE2, LECTURER2, HOURS2, COST2, DATE2);
	static int id1;
	static int id2;
	@Autowired
CoursesService coursesService;
	@Test
	@Order(1)
	void addCoursesTest() {
		course1 = coursesService.addCourse(course1);
		assertNotNull(course1.id);
		course2 = coursesService.addCourse(course2);
		assertNotNull(course2.id);
		id1 = course1.id;
		id2 = course2.id;
		assertNotEquals(id1, id2);
	}
	@Test
	@Order(2)
	void getCourseTest() {
		assertEquals(course1, coursesService.getCourse(id1));
		assertEquals(course2, coursesService.getCourse(id2));
	}
	@Test
	@Order(3)
	void updateCourseTest() {
		//	public Course updateCourse(int id, Course course) 
		
		assertEquals(2, coursesService.getAllCourses().size());
		
		Course courseToUpdate = new Course(COURSE3, LECTURER3, HOURS3, COST3, DATE3);
		courseToUpdate.id=id1;
		Course courseBeforeUpdate= coursesService.getCourse(id1); 
		Course courseOld= coursesService.updateCourse(id1, courseToUpdate);
		Course courseNew = coursesService.getCourse(id1); 
		
		assertEquals(courseToUpdate, courseNew);
		assertEquals(courseBeforeUpdate, courseOld);
		assertEquals(2, coursesService.getAllCourses().size());
	}
	@Test
	@Order(4)
	void removeCourseTest() {
		//public Course removeCourse(int id)
		
		assertEquals(2, coursesService.getAllCourses().size());
		Course courseBeforeRemove1= coursesService.getCourse(id1);
		Course courseRemove1= coursesService.removeCourse(id1);
		assertEquals(courseBeforeRemove1, courseRemove1);
		
		assertEquals(1, coursesService.getAllCourses().size());
		Course courseBeforeRemove2= coursesService.getCourse(id2);
		Course courseRemove2= coursesService.removeCourse(id2);
		assertEquals(courseBeforeRemove2, courseRemove2);
		
		assertEquals(0, coursesService.getAllCourses().size());
		
	}

}
