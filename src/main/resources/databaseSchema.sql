CREATE TABLE students
(
    student_id   serial PRIMARY KEY,
    student_name varchar(50) NOT NULL,
    email        varchar(50) NOT NULL,
    phone_number int         NOT NULL
);

CREATE TABLE courses
(
    course_id     serial PRIMARY KEY,
    course_name   varchar(50)  NOT NULL,
    description   varchar(100) NOT NULL,
    instructor_id int REFERENCES instructors (instructor_id)
);

CREATE TABLE instructors
(
    instructor_id   serial PRIMARY KEY,
    instructor_name varchar(50) NOT NULL,
    email           varchar(50) NOT NULL
);

CREATE TABLE student_course
(
    id         serial PRIMARY KEY,
    student_id int REFERENCES students (student_id) on delete cascade,
    course_id  int REFERENCES courses (course_id)
);