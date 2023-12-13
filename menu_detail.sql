--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1
-- Dumped by pg_dump version 16.1

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: menu_detail; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.menu_detail (
    index integer DEFAULT nextval('public.menu_detail_sequence'::regclass),
    menu_sub_key character varying(5) NOT NULL,
    detail_key character varying(5) NOT NULL,
    menu_name character varying(20) NOT NULL,
    menu_icon character varying(20) DEFAULT 'document'::character varying
);


ALTER TABLE public.menu_detail OWNER TO root;

--
-- Data for Name: menu_detail; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.menu_detail (index, menu_sub_key, detail_key, menu_name, menu_icon) FROM stdin;
10	ma1	00001	Home	home
11	ma2	00001	About	about
14	ma5	00001	Contact	contact
45	Di044	00003	Diary	document
47	Gi046	00002	Git	document
49	Re048	00002	React	document
51	Ne050	00002	Next.js	document
53	Ja052	00002	Java	document
55	Sp054	00002	Spring	document
57	AW056	00002	AWS	document
61	Er060	00003	ErrorReport	document
59	To058	00004	Today's Styling	cloth
13	ma3	00001	Skill	bulb
12	ma4	00001	Projects	project
7	Ty005	00002	Typescript	document
11	Sc005	00004	Scheduler	calendar
\.


--
-- Name: menu_detail menu_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.menu_detail
    ADD CONSTRAINT menu_detail_pkey PRIMARY KEY (menu_sub_key);


--
-- Name: menu_detail detail_key; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.menu_detail
    ADD CONSTRAINT detail_key FOREIGN KEY (detail_key) REFERENCES public.menu_type(detail_key);


--
-- PostgreSQL database dump complete
--

