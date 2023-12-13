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
-- Name: api_key; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.api_key (
    name character varying NOT NULL,
    key character varying NOT NULL
);


ALTER TABLE public.api_key OWNER TO root;

--
-- Name: board_list_sequence; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.board_list_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.board_list_sequence OWNER TO root;

--
-- Name: board_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.board_list (
    index integer DEFAULT nextval('public.board_list_sequence'::regclass) NOT NULL,
    board_key character varying(5) NOT NULL,
    title character varying NOT NULL,
    author character varying(20) DEFAULT 'Jeong Daegyun'::character varying,
    create_time timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    modified_time timestamp without time zone,
    menu_sub_key character varying(5) NOT NULL,
    content text DEFAULT ''::text
);


ALTER TABLE public.board_list OWNER TO postgres;

--
-- Name: cloth_list_sequence; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.cloth_list_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.cloth_list_sequence OWNER TO root;

--
-- Name: clothes_list; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.clothes_list (
    type character varying NOT NULL,
    name character varying NOT NULL,
    index integer DEFAULT nextval('public.cloth_list_sequence'::regclass) NOT NULL,
    status integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.clothes_list OWNER TO root;

--
-- Name: clothes_type; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.clothes_type (
    type character varying NOT NULL
);


ALTER TABLE public.clothes_type OWNER TO root;

--
-- Name: menu_type; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.menu_type (
    detail_key character varying(5) NOT NULL,
    menu_type character varying(20) NOT NULL
);


ALTER TABLE public.menu_type OWNER TO root;

--
-- Name: side_menu_sequence; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.side_menu_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.side_menu_sequence OWNER TO root;

--
-- Name: side_menu; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.side_menu (
    index integer DEFAULT nextval('public.side_menu_sequence'::regclass) NOT NULL,
    menu_key character varying(5) DEFAULT nextval('public.side_menu_sequence'::regclass) NOT NULL,
    detail_key character varying(5) NOT NULL
);


ALTER TABLE public.side_menu OWNER TO root;

--
-- Name: menu_category; Type: VIEW; Schema: public; Owner: root
--

CREATE VIEW public.menu_category AS
 SELECT s.index,
    s.menu_key,
    s.detail_key,
    t.menu_type
   FROM public.side_menu s,
    public.menu_type t
  WHERE ((s.detail_key)::text = (t.detail_key)::text);


ALTER VIEW public.menu_category OWNER TO root;

--
-- Name: menu_detail_sequence; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.menu_detail_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.menu_detail_sequence OWNER TO root;

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
-- Name: project_list; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.project_list (
    project_name character varying NOT NULL,
    project_key character varying NOT NULL
);


ALTER TABLE public.project_list OWNER TO root;

--
-- Name: skill_stack_list; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.skill_stack_list (
    index integer NOT NULL,
    type character varying NOT NULL,
    name character varying NOT NULL,
    id character varying NOT NULL
);


ALTER TABLE public.skill_stack_list OWNER TO root;

--
-- Name: skill_stack_sequence; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.skill_stack_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.skill_stack_sequence OWNER TO root;

--
-- Name: styling_data; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.styling_data (
    max_temperature double precision,
    min_temperature double precision,
    current_temperature double precision,
    wind_speed double precision,
    humidity double precision,
    precip_probability double precision,
    icon character varying,
    date timestamp without time zone NOT NULL
);


ALTER TABLE public.styling_data OWNER TO root;

--
-- Name: styling_personal_info; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.styling_personal_info (
    height double precision,
    body_type character varying,
    gender character varying,
    project_key character varying
);


ALTER TABLE public.styling_personal_info OWNER TO postgres;

--
-- Data for Name: api_key; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.api_key (name, key) FROM stdin;
weather	tnZVRSTIILHMNbidLrb6uQzwEBiP0HX4
\.


--
-- Data for Name: board_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.board_list (index, board_key, title, author, create_time, modified_time, menu_sub_key, content) FROM stdin;
2	00001	2023.11 ・ｩ岺・・・・ｱ・ｼ 1	JDG	2023-12-01 15:07:10.057	2023-12-05 16:17:26.258	Di044	<h1><strong class="ql-size-huge">・ｩ岺・/strong></h1><p><u class="ql-size-large">1. MyPage・・Main ・・┳﨑俾ｸｰ.</u></p><p><span class="ql-size-large" style="color: rgb(230, 0, 0);">2. CI/CD ・・嶸ｸ・､甯・・・ｨ ・ｵ・</span></p><p><br></p><p><br></p><p><span class="ql-size-huge" style="background-color: rgb(250, 204, 204);">・ｱ・ｼ</span></p><p><span style="background-color: rgb(250, 204, 204);">奛護侃孖ｸ</span></p>
\.


--
-- Data for Name: clothes_list; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.clothes_list (type, name, index, status) FROM stdin;
繝医ャ繝励せ	逋ｽ繝ｯ繧､繧ｷ繝｣繝・4	3
\.


--
-- Data for Name: clothes_type; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.clothes_type (type) FROM stdin;
繝医ャ繝励せ
繝懊ヨ繝繧ｹ
繧ｯ繝・
繧｢繧ｯ繧ｻ繧ｵ繝ｪ繝ｼ
\.


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
-- Data for Name: menu_type; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.menu_type (detail_key, menu_type) FROM stdin;
00001	Main
00002	Board
00003	Others
00004	Project
\.


--
-- Data for Name: project_list; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.project_list (project_name, project_key) FROM stdin;
Today's Styling	toda1
\.


--
-- Data for Name: side_menu; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.side_menu (index, menu_key, detail_key) FROM stdin;
3	1	00001
4	4	00002
5	5	00003
10	Pe004	00004
\.


--
-- Data for Name: skill_stack_list; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.skill_stack_list (index, type, name, id) FROM stdin;
1	Language	Java	La001
2	Language	HTML	La002
3	Language	CSS	La003
4	Language	Javascript	La004
5	Language	Typescript	La005
6	Language	Python	La006
7	Language	C#	La007
8	Language	VB	La008
9	Framework	Spring	Fr009
10	Framework	React	Fr010
11	Framework	Next.js	Fr011
12	Framework	.NetFramework	Fr012
13	Framework	JUnit	Fr013
14	Library	Recoil	Li014
15	Library	MyBatis	Li015
16	Library	JPA	Li016
17	Library	JQuery	Li017
18	Library	Material UI	Li018
19	Library	Bootstrap	Li019
20	Database	MS-SQL	Da020
21	Database	Oracle	Da021
22	Database	PostgreSQL	Da022
23	Database	MongoDB	Da023
24	Others	Excel	Ot024
25	Others	JP1	Ot025
26	Others	Linux	Ot026
\.


--
-- Data for Name: styling_data; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.styling_data (max_temperature, min_temperature, current_temperature, wind_speed, humidity, precip_probability, icon, date) FROM stdin;
15.4	7.81	13.89	4.03	0.31	0	partly-cloudy-day	2023-11-30 00:00:00
10.8	8.1	8.72	1.83	0.42	0	partly-cloudy-day	2023-12-01 00:00:00
12.47	9.71	9.41	1.52	0.39	0	clear-day	2023-12-04 00:00:00
11.41	10.24	9.95	3.18	0.5	0.0318	rain	2023-12-05 00:00:00
15.51	12.3	10.78	2.61	0.53	0.0105	partly-cloudy-day	2023-12-06 00:00:00
15.87	12.02	12.62	1.55	0.33	0	partly-cloudy-day	2023-12-07 00:00:00
14.94	11.84	13.96	4.98	0.73	0.0333	rain	2023-12-11 00:00:00
14.14	10.29	10.81	2.79	0.35	0	partly-cloudy-day	2023-12-13 00:00:00
\.


--
-- Data for Name: styling_personal_info; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.styling_personal_info (height, body_type, gender, project_key) FROM stdin;
178.9	譎ｮ騾・逕ｷ諤ｧ	toda1
\.


--
-- Name: board_list_sequence; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.board_list_sequence', 24, true);


--
-- Name: cloth_list_sequence; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.cloth_list_sequence', 16, true);


--
-- Name: menu_detail_sequence; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.menu_detail_sequence', 14, true);


--
-- Name: side_menu_sequence; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.side_menu_sequence', 8, true);


--
-- Name: skill_stack_sequence; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.skill_stack_sequence', 26, true);


--
-- Name: board_list board_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.board_list
    ADD CONSTRAINT board_key PRIMARY KEY (board_key);


--
-- Name: clothes_list clothes_list_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.clothes_list
    ADD CONSTRAINT clothes_list_pkey PRIMARY KEY (index);


--
-- Name: clothes_type clothes_type_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.clothes_type
    ADD CONSTRAINT clothes_type_pkey PRIMARY KEY (type);


--
-- Name: menu_detail menu_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.menu_detail
    ADD CONSTRAINT menu_detail_pkey PRIMARY KEY (menu_sub_key);


--
-- Name: menu_type menu_type_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.menu_type
    ADD CONSTRAINT menu_type_pkey PRIMARY KEY (detail_key);


--
-- Name: project_list project_list_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_list
    ADD CONSTRAINT project_list_pkey PRIMARY KEY (project_key);


--
-- Name: side_menu side_menu_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.side_menu
    ADD CONSTRAINT side_menu_pkey PRIMARY KEY (menu_key);


--
-- Name: skill_stack_list skill_stack_list_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.skill_stack_list
    ADD CONSTRAINT skill_stack_list_pkey PRIMARY KEY (id);


--
-- Name: styling_data styling_data_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.styling_data
    ADD CONSTRAINT styling_data_pkey PRIMARY KEY (date);


--
-- Name: side_menu detail_key; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.side_menu
    ADD CONSTRAINT detail_key FOREIGN KEY (detail_key) REFERENCES public.menu_type(detail_key);


--
-- Name: menu_detail detail_key; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.menu_detail
    ADD CONSTRAINT detail_key FOREIGN KEY (detail_key) REFERENCES public.menu_type(detail_key);


--
-- Name: board_list menu_sub_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.board_list
    ADD CONSTRAINT menu_sub_key FOREIGN KEY (menu_sub_key) REFERENCES public.menu_detail(menu_sub_key);


--
-- Name: styling_personal_info project_key; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.styling_personal_info
    ADD CONSTRAINT project_key FOREIGN KEY (project_key) REFERENCES public.project_list(project_key);


--
-- Name: clothes_list type; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.clothes_list
    ADD CONSTRAINT type FOREIGN KEY (type) REFERENCES public.clothes_type(type);


--
-- PostgreSQL database dump complete
--

