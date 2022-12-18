-- CREATE DATABASE IF NOT EXISTS codinghermit;
-- SELECT 'CREATE DATABASE codinghermit'
--   WHERE NOT EXISTS (
--     SELECT FROM pg_database WHERE datname = 'codinghermit'
--   )\gexec

-- \c codinghermit;

-- DROP TABLE IF EXISTS staffs CASCADE;
-- CREATE TABLE IF NOT EXISTS staffs (
--   staffId BIGINT PRIMARY KEY
--   , staffName VARCHAR(64) NOT NULL
--   , emailId VARCHAR(64) NOT NULL
--   , "role" VARCHAR(45) NOT NULL
--   , "password" VARCHAR(64) NOT NULL
--   , enabled SMALLINT DEFAULT 1
-- );

-- REQUIRED! Installed by default, activate manually once per database.
-- CREATE EXTENSION pgcrypto;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY
    , username VARCHAR(100) NOT NULL
    , emailId VARCHAR(64) NOT NULL
    , "role" VARCHAR(45) NOT NULL
    , "password" VARCHAR(100) NOT NULL
    , enabled SMALLINT DEFAULT 1
    , CONSTRAINT username_unique UNIQUE (username)
);

DROP TABLE IF EXISTS courses CASCADE;
CREATE TABLE IF NOT EXISTS courses (
  courseId BIGINT PRIMARY KEY
  , courseName VARCHAR(128) NOT NULL
  , id BIGINT NOT NULL
  , FOREIGN KEY (id) REFERENCES users(id)
);

DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE IF NOT EXISTS students (
  studentId BIGINT PRIMARY KEY
  , studentName VARCHAR(64) NOT NULL
  , emailId VARCHAR(64) NOT NULL
  , "role" VARCHAR(45) NOT NULL
  , "password" VARCHAR(64) NOT NULL
  , enabled SMALLINT DEFAULT 1
);

DROP TABLE IF EXISTS enrollments CASCADE;
CREATE TABLE IF NOT EXISTS enrollments (
  courseId BIGINT
  , studentId BIGINT
  , FOREIGN KEY (courseId) REFERENCES courses(courseId)
  , FOREIGN KEY (studentId) REFERENCES students(studentId)
  , PRIMARY KEY (courseId, studentId)
);

-- DROP TABLE IF EXISTS user_authorities CASCADE;
-- CREATE TABLE IF NOT EXISTS user_authorities (
--     id BIGINT PRIMARY KEY
--     , user_id   BIGINT NOT NULL
--     , authority varchar(50) NOT NULL
--     , FOREIGN KEY (user_id) REFERENCES users(id)
--     , CONSTRAINT username_authorities_unique UNIQUE (user_id, authority)
-- );

-- DROP TABLE IF EXISTS users CASCADE;
-- CREATE TABLE IF NOT EXISTS users (
--   user_id SERIAL PRIMARY KEY,
--   username VARCHAR(45) NOT NULL,
--   "password" VARCHAR(64) NOT NULL,
--   "role" VARCHAR(45) NOT NULL,
--   enabled SMALLINT DEFAULT NULL
-- );

-- INSERT INTO users (username, "password", "role", enabled)
-- VALUES ('akila',
-- '$2a$10$XptfskLsT1l/bRTLRiiCgejHqOpgXFreUnNUa35gJdCr2v2QbVFzu',
-- 'ROLE_USER', 1);
-- -- codejava

-- INSERT INTO users (username, "password", "role", enabled)
-- VALUES ('admin',
-- '$2a$10$zxvEq8XzYEYtNjbkRsJEbukHeRx3XS6MDXHMu8cNuNsRfZJWwswDy',
-- 'ROLE_ADMIN', 1);
-- -- nimda

-- Pass for testing: 123
INSERT INTO users VALUES (1
, 'Staff1'
, 'staff1@email.com'
, 'ROLE_ADMIN'
, '$2a$10$x1p24jiDbYNGYey4a/6rV.LRmqMm4aJnzjbKH7k1LK55PNeu7Wq8G') ON CONFLICT DO NOTHING;
INSERT INTO users VALUES (2
, 'Staff2'
, 'staff2@email.com'
, 'ROLE_ADMIN'
, '$2a$10$x1p24jiDbYNGYey4a/6rV.LRmqMm4aJnzjbKH7k1LK55PNeu7Wq8G') ON CONFLICT DO NOTHING;

INSERT INTO courses VALUES (1, 'Course1', 1) ON CONFLICT DO NOTHING;
INSERT INTO courses VALUES (2, 'Course2', 2) ON CONFLICT DO NOTHING;

INSERT INTO students VALUES (1
, 'Student1'
, 'student1@email.com'
, 'ROLE_USER'
, '$2a$10$x1p24jiDbYNGYey4a/6rV.LRmqMm4aJnzjbKH7k1LK55PNeu7Wq8G') ON CONFLICT DO NOTHING;
INSERT INTO students VALUES (2
, 'Student2'
, 'student2@email.com'
, 'ROLE_USER'
, '$2a$10$x1p24jiDbYNGYey4a/6rV.LRmqMm4aJnzjbKH7k1LK55PNeu7Wq8G') ON CONFLICT DO NOTHING;

INSERT INTO enrollments VALUES (1, 1) ON CONFLICT DO NOTHING;
INSERT INTO enrollments VALUES (2, 2) ON CONFLICT DO NOTHING;
