package com.mysql.controller



import com.mysql.entity.StudentsEntity
import com.mysql.repository.StudentRepository
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpResponse


@Controller("/students")
class Controller(private val studentRepository: StudentRepository) {

    @Get("/")
    fun findAll(): HttpResponse<List<StudentsEntity>> {
        val students = studentRepository.findAll()
        return HttpResponse.ok(students) // Sends 200 OK with the list of students
    }
}