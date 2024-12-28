package com.mysql.repository


import io.micronaut.data.annotation.Repository
import com.mysql.entity.StudentsEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository



@JdbcRepository(dialect = Dialect.MYSQL) // Specify MySQL as the SQL dialect
interface StudentRepository : CrudRepository<StudentsEntity, Int> {

    override fun findAll(): List<StudentsEntity> // Retrieve all students
}
