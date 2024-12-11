--
-- PostgreSQL database dump
--

-- Dumped from database version 15.3
-- Dumped by pg_dump version 15.3

-- Started on 2024-12-11 06:12:05

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 7 (class 2615 OID 34200)
-- Name: bookschema; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA bookschema;


ALTER SCHEMA bookschema OWNER TO postgres;

--
-- TOC entry 6 (class 2615 OID 34152)
-- Name: testing; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA testing;


ALTER SCHEMA testing OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 220 (class 1259 OID 34201)
-- Name: books; Type: TABLE; Schema: bookschema; Owner: postgres
--

CREATE TABLE bookschema.books (
    id integer NOT NULL,
    book_name character varying NOT NULL,
    author_name character varying,
    release_year character varying
);


ALTER TABLE bookschema.books OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 34153)
-- Name: user_data; Type: TABLE; Schema: testing; Owner: postgres
--

CREATE TABLE testing.user_data (
    id character varying NOT NULL,
    name character varying,
    age integer,
    email character varying,
    country character varying
);


ALTER TABLE testing.user_data OWNER TO postgres;

--
-- TOC entry 3335 (class 0 OID 34201)
-- Dependencies: 220
-- Data for Name: books; Type: TABLE DATA; Schema: bookschema; Owner: postgres
--

INSERT INTO bookschema.books VALUES (1, 'five point someone', 'chetan bhagat', '2020');
INSERT INTO bookschema.books VALUES (2, 'half girlfriend', 'testing bhagat', '2023');
INSERT INTO bookschema.books VALUES (21, 'Java Basics', 'John Doe', '2020');
INSERT INTO bookschema.books VALUES (22, 'Spring Framework', 'Jane Smith', '2021');
INSERT INTO bookschema.books VALUES (23, 'Effective Java', 'Joshua Bloch', '2019');
INSERT INTO bookschema.books VALUES (4, 'Clean Code', 'Robert C. Martin', '2008');
INSERT INTO bookschema.books VALUES (5, 'Design Patterns', 'Erich Gamma', '1994');
INSERT INTO bookschema.books VALUES (6, 'Microservices', 'Sam Newman', '2015');
INSERT INTO bookschema.books VALUES (7, 'The Pragmatic Programmer', 'Andrew Hunt', '1999');
INSERT INTO bookschema.books VALUES (8, 'Refactoring', 'Martin Fowler', '1999');
INSERT INTO bookschema.books VALUES (9, 'Algorithms', 'Robert Sedgewick', '2011');
INSERT INTO bookschema.books VALUES (10, 'Database Design', 'Carlos Coronel', '2016');
INSERT INTO bookschema.books VALUES (11, 'Introduction to Algorithms', 'Thomas H. Cormen', '2009');
INSERT INTO bookschema.books VALUES (12, 'The Mythical Man-Month', 'Frederick P. Brooks Jr.', '1975');
INSERT INTO bookschema.books VALUES (13, 'Clean Architecture', 'Robert C. Martin', '2017');
INSERT INTO bookschema.books VALUES (14, 'You Don''t Know JS', 'Kyle Simpson', '2014');
INSERT INTO bookschema.books VALUES (15, 'The Art of Computer Programming', 'Donald E. Knuth', '1968');
INSERT INTO bookschema.books VALUES (16, 'Learning Python', 'Mark Lutz', '2013');
INSERT INTO bookschema.books VALUES (17, 'Python Crash Course', 'Eric Matthes', '2015');
INSERT INTO bookschema.books VALUES (18, 'The Clean Coder', 'Robert C. Martin', '2011');
INSERT INTO bookschema.books VALUES (19, 'C Programming Language', 'Brian W. Kernighan', '1988');
INSERT INTO bookschema.books VALUES (20, 'Programming Pearls', 'Jon Bentley', '1986');
INSERT INTO bookschema.books VALUES (24, 'javascript', 'jai sharma', '2003');


--
-- TOC entry 3334 (class 0 OID 34153)
-- Dependencies: 216
-- Data for Name: user_data; Type: TABLE DATA; Schema: testing; Owner: postgres
--

INSERT INTO testing.user_data VALUES ('1', 'test user 1', 10, 'test@gmail.com', 'india');
INSERT INTO testing.user_data VALUES ('2', 'test user 2', 11, 'test@gmail.com', 'india');
INSERT INTO testing.user_data VALUES ('3', 'test user 3', 11, 'test@gmail.com', 'india');
INSERT INTO testing.user_data VALUES ('4', 'test user 4', 14, NULL, 'india');
INSERT INTO testing.user_data VALUES ('5', 'test user 5', 15, 'testing@email.com', 'india');
INSERT INTO testing.user_data VALUES ('6', 'test user 6', 15, 'testing@email.com', 'india');
INSERT INTO testing.user_data VALUES ('8', 'test user 8', 15, 'testing@email.com', 'india');
INSERT INTO testing.user_data VALUES ('7', 'test user 7', 15, 'test45@email.com', 'india');


--
-- TOC entry 3189 (class 2606 OID 34209)
-- Name: books books_book_name_key; Type: CONSTRAINT; Schema: bookschema; Owner: postgres
--

ALTER TABLE ONLY bookschema.books
    ADD CONSTRAINT books_book_name_key UNIQUE (book_name);


--
-- TOC entry 3191 (class 2606 OID 34207)
-- Name: books books_pkey; Type: CONSTRAINT; Schema: bookschema; Owner: postgres
--

ALTER TABLE ONLY bookschema.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);


--
-- TOC entry 3187 (class 2606 OID 34159)
-- Name: user_data user_data_pkey; Type: CONSTRAINT; Schema: testing; Owner: postgres
--

ALTER TABLE ONLY testing.user_data
    ADD CONSTRAINT user_data_pkey PRIMARY KEY (id);


-- Completed on 2024-12-11 06:12:06

--
-- PostgreSQL database dump complete
--

