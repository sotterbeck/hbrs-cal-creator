package de.sotterbeck.hbrscalcreator.eva

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coursesOfStudy")
class CourseOfStudyController(
    private val getAllCoursesOfStudyInteractor: GetAllCoursesOfStudyInteractor,
    private val getAllSemesterNamesInteractor: GetAllSemesterNamesInteractor
) {

    @GetMapping
    suspend fun coursesOfStudy(): ResponseEntity<GetAllCoursesOfStudyInteractor.Response> {
        return ResponseEntity.ok(getAllCoursesOfStudyInteractor())
    }

    @GetMapping("/semesters")
    suspend fun semesterNames(): ResponseEntity<GetAllSemesterNamesInteractor.Response> {
        return ResponseEntity.ok(getAllSemesterNamesInteractor())
    }
}