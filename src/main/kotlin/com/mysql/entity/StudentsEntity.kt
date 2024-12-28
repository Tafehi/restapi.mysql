package com.mysql.entity

import io.micronaut.core.annotation.Introspected
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.MappedProperty
import io.micronaut.data.annotation.GeneratedValue.Type
import io.micronaut.serde.annotation.Serdeable


@Serdeable // Add this annotation for serialization support
@Introspected
@MappedEntity("students") // Use the database table name here
data class StudentsEntity(

    @field:Id
    @field:GeneratedValue(Type.IDENTITY) // Use Micronaut's GeneratedValue for auto-incrementing primary keys
    val id: Int? = null, // Primary key

    @MappedProperty("firstName") // Explicitly mapping the field to match the DB column
    val firstName: String, // First name of the student

    @MappedProperty("lastName")
    val lastName: String,  // Last name of the student

    @MappedProperty("email")
    val email: String,     // Email of the student

    @MappedProperty("address")
    val address: String    // Address of the student
)