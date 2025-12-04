package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

//            createCourseAndReviews(appDAO);
//            retrieveCourseAndReviews(appDAO);
            deleteCourseAndReviews(appDAO);
		};
	}

    private void deleteCourseAndReviews(AppDAO appDAO) {

        int theId = 10;
        System.out.println("Deleting course id: " + theId);
        appDAO.deleteCourseById(theId);
        System.out.println("Done!");
    }

    private void retrieveCourseAndReviews(AppDAO appDAO) {
        // get the course and reviews
        int theId = 10;
        Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);

        // print the course
        System.out.println(tempCourse);
        // print the reviews
        System.out.println(tempCourse.getReviews());
        System.out.println("Done!");
    }

    private void createCourseAndReviews(AppDAO appDAO) {

        // create a course

        Course tempCourse = new Course("Pacman - How to score one million points");

        // add some reviews
        tempCourse.addReview(new Review("Great Course ... loved it!"));
        tempCourse.addReview(new Review("Cool Course ... job well done."));
        tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

        // save the course
        System.out.println("Saving the course");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());

        appDAO.save(tempCourse);
        System.out.println("Done!");
    }

    private void deleteCourseById(AppDAO appDAO) {
        int theId = 13;

        System.out.println("Deleting course id: " + theId);

        appDAO.deleteCourseById(theId);

        System.out.println("Done!");
    }

    private void updateCourse(AppDAO appDAO) {
        int theId = 10;
        System.out.println("Finding course id: " + theId);
        Course tempCourse = appDAO.findCourseById(theId);
        System.out.println("updating course title");
        tempCourse.setTitle("Enjoy the Simple Things");
        appDAO.update(tempCourse);
        System.out.println("Done!");
    }

    private void updateInstructor(AppDAO appDAO) {
        int theId = 1;
        // find the instructor
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);
        // update the instructor
        System.out.println("updating instructor last name");
        tempInstructor.setLastName("Tester");
        appDAO.update(tempInstructor);
        System.out.println("Done!");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor id: " + theId);
        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
        System.out.println("temp instructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void findCoursesForInstructor(AppDAO appDAO) {
        int theId = 1;
        // find the instructor

        Instructor tempInstructor = appDAO.findInstructorById(theId);
        System.out.println("temp instructor: " + tempInstructor);
        // find courses for instructor

        System.out.println("Finding courses for instructor id: " + theId);
        List<Course> courses = appDAO.findCoursesByInstructorId(theId);
        // associate the objects

        tempInstructor.setCourses(courses);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void findInstructorWithCourses(AppDAO appDAO) {
        int theId = 1;
        System.out.println("Finding instructor: " + theId);
        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor: " + tempInstructor);
        System.out.println("the associated courses: " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void createInstructorwithCourse(AppDAO appDAO) {
        // create the instructor

        Instructor tempInstructor = new Instructor("Asad","Ali","aliasad@gmail.com");

        // create the instructor detail

        InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.aliasad.com",
                "Gaming");

        // associate the objects

        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses
        Course tempCourse1 = new Course("Applied Physics");
        Course tempCourse2 = new Course("Web Development");

        // add courses to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        //
        // NOTE: this will also save the courses
        // because of CascadeType.PERSIST
        System.out.println("Saving instructor: " + tempInstructor);
        System.out.println("The courses: " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);
        System.out.println("Done!");
    }

    private void deleteInstructorDetailById(AppDAO appDAO) {

        int theId = 7;
        System.out.println("Deleting instructor detail id: " + theId);
        appDAO.deleteInstructorDetailById(theId);
        System.out.println("Done!");
    }

    private void findInstructorDetailById(AppDAO appDAO) {

        // get the instructor detail object
		int id = 1;
		System.out.println("Retrieving InstructorDetail by Id " + id);
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(id);
        // print the instructor detail object
		System.out.println("Retrieved Instructor: " + tempInstructorDetail);
        // print the associated instructor
		System.out.println("The associated instructor only: " + tempInstructorDetail.getInstructor());
        System.out.println("Done!");
	}

	private void deleteInstructorById(AppDAO appDAO) {

		int id = 5;
		System.out.println("Deleting instructor against id: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("Deletion successful!");
	}

	private void findInstructorById(AppDAO appDAO) {

		int id = 1;
		System.out.println("Retrieving Instructor by Id " + id);
		Instructor retrieveInstructor = appDAO.findInstructorById(id);
		System.out.println("Retrieved Instructor: " + retrieveInstructor);
		System.out.println("the associated instructorDetail only: " + retrieveInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// create the instructor
		/*
		Instructor tempInstructor = new Instructor("Chad","Darby","darby@luv2code.com");

		// create the instructor detail

		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube",
				"luv2code");


		 */

		// create the instructor

		Instructor tempInstructor = new Instructor("Saad","Suriya","suriya880@gmail.com");

		// create the instructor detail

		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com",
				"Guitar");

		// associate the objects

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// save the instructor

		// Note: this will also save the details object because of CascadeType.ALL

		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

}
