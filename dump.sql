--
-- PostgreSQL database dump
--

-- Dumped from database version 10.3
-- Dumped by pg_dump version 10.3

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: articolo; Type: TABLE; Schema: public; Owner: IngSof
--

CREATE TABLE public.articolo (
    codice integer NOT NULL,
    tipoarticolo character varying,
    prezzo money,
    dataproduzione date
);


ALTER TABLE public.articolo OWNER TO "IngSof";

--
-- Name: ingressi; Type: TABLE; Schema: public; Owner: IngSof
--

CREATE TABLE public.ingressi (
    id integer NOT NULL,
    data date,
    article integer,
    "position" integer
);


ALTER TABLE public.ingressi OWNER TO "IngSof";

--
-- Name: ingressi_id_seq; Type: SEQUENCE; Schema: public; Owner: IngSof
--

CREATE SEQUENCE public.ingressi_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ingressi_id_seq OWNER TO "IngSof";

--
-- Name: ingressi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: IngSof
--

ALTER SEQUENCE public.ingressi_id_seq OWNED BY public.ingressi.id;


--
-- Name: logins; Type: TABLE; Schema: public; Owner: IngSof
--

CREATE TABLE public.logins (
    username character varying NOT NULL,
    password character varying NOT NULL,
    accounttype public."AcctType",
    salt character varying NOT NULL
);


ALTER TABLE public.logins OWNER TO "IngSof";

--
-- Name: ordini; Type: TABLE; Schema: public; Owner: IngSof
--

CREATE TABLE public.ordini (
    negozio character varying NOT NULL,
    codice integer NOT NULL,
    data date,
    tipoarticolo character varying NOT NULL,
    quantita integer,
    prezzototale numeric
);


ALTER TABLE public.ordini OWNER TO "IngSof";

--
-- Name: uscite; Type: TABLE; Schema: public; Owner: IngSof
--

CREATE TABLE public.uscite (
    data date NOT NULL,
    numerobolla integer NOT NULL,
    negozio character varying NOT NULL,
    spedizioniere character varying,
    ordine integer NOT NULL
);


ALTER TABLE public.uscite OWNER TO "IngSof";

--
-- Name: negozi; Type: TABLE; Schema: public; Owner: IngSof
--

CREATE TABLE public.negozi (
    codicefiscale character varying NOT NULL,
    nome character varying,
    indirizzo character varying,
    citta character varying
);


ALTER TABLE public.negozi OWNER TO "IngSof";

--
-- Name: tipiarticolo; Type: TABLE; Schema: public; Owner: IngSof
--

CREATE TABLE public.tipiarticolo (
    nome character varying NOT NULL,
    descrizione character varying,
    sport character varying,
    materiali character varying[]
);


ALTER TABLE public.tipiarticolo OWNER TO "IngSof";

--
-- Name: ingressi id; Type: DEFAULT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.ingressi ALTER COLUMN id SET DEFAULT nextval('public.ingressi_id_seq'::regclass);


--
-- Data for Name: articolo; Type: TABLE DATA; Schema: public; Owner: IngSof
--

COPY public.articolo (codice, tipoarticolo, prezzo, dataproduzione) FROM stdin;
1	Scarpe da ginnastica	56,70 €	2019-01-03
2	Pantaloncini	20,00 €	2017-01-06
3	Occhiali	100,00 €	2015-01-15
\.


--
-- Data for Name: ingressi; Type: TABLE DATA; Schema: public; Owner: IngSof
--

COPY public.ingressi (id, data, article, "position") FROM stdin;
1	2019-01-06	1	6
0	2019-01-06	1	24
2	2019-01-06	2	10
3	2019-01-06	3	5
\.


--
-- Data for Name: logins; Type: TABLE DATA; Schema: public; Owner: IngSof
--

COPY public.logins (username, password, accounttype, salt) FROM stdin;
admin	6Nu+IoBr185zhjCa2OhU57cz4jQ=	store_manager	Vh63plkp61Q=
manager	Q7x+AqKOKmu/apnqqBnkQcQcQLc=	manager	Eu0HsEeKHrg=
a	OF6zJbw1WX0XjXoV7VGISDOTjRs=	warehouse_worker	JMdN2zRWYpg=
\.


--
-- Data for Name: negozi; Type: TABLE DATA; Schema: public; Owner: IngSof
--

COPY public.negozi (codicefiscale, nome, indirizzo, citta) FROM stdin;
czzmrc	Castiglione	ss martiri 	Castiglione
prova1	negozio di prova	via delle grazie 2	verona
\.


--
-- Data for Name: ordini; Type: TABLE DATA; Schema: public; Owner: IngSof
--

COPY public.ordini (negozio, codice, data, tipoarticolo, quantita, prezzototale) FROM stdin;
czzmrc	1	1998-12-22	Scarpe da ginnastica	10	62.60
prova1	2	2015-05-20	Pantaloncini	4	224.0
czzmrc	3	2019-04-12	Occhiali	4	400
\.


--
-- Data for Name: tipiarticolo; Type: TABLE DATA; Schema: public; Owner: IngSof
--

COPY public.tipiarticolo (nome, descrizione, sport, materiali) FROM stdin;
Scarpe da ginnastica	Paio di scarpe da ginnastica	Corsa	{pelle}
Pantaloncini	Paio di pantaloncini	Corsa	{nylon}
Occhiali	paio di occhiali sportivi	Generico	{Plastica,metallo,vetro}
\.


--
-- Data for Name: uscite; Type: TABLE DATA; Schema: public; Owner: IngSof
--

COPY public.uscite (data, numerobolla, negozio, spedizioniere, ordine) FROM stdin;
2019-01-06	0	czzmrc	marco	1
2019-01-02	1	prova1	luca	2
2019-01-06	2	czzmrc	paolo	3
\.


--
-- Name: ingressi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: IngSof
--

SELECT pg_catalog.setval('public.ingressi_id_seq', 1, false);


--
-- Name: logins Logins_pkey; Type: CONSTRAINT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.logins
    ADD CONSTRAINT "Logins_pkey" PRIMARY KEY (username);


--
-- Name: articolo articolo_pkey; Type: CONSTRAINT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.articolo
    ADD CONSTRAINT articolo_pkey PRIMARY KEY (codice);


--
-- Name: ingressi ingressi_pk; Type: CONSTRAINT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.ingressi
    ADD CONSTRAINT ingressi_pk PRIMARY KEY (id);


--
-- Name: negozi negozi_pkey; Type: CONSTRAINT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.negozi
    ADD CONSTRAINT negozi_pkey PRIMARY KEY (codicefiscale);


--
-- Name: ordini ordine_pk; Type: CONSTRAINT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.ordini
    ADD CONSTRAINT ordine_pk PRIMARY KEY (negozio, codice, tipoarticolo);


--
-- Name: tipiarticolo tipoarticolo_pkey; Type: CONSTRAINT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.tipiarticolo
    ADD CONSTRAINT tipoarticolo_pkey PRIMARY KEY (nome);


--
-- Name: uscite uscite_pk_2; Type: CONSTRAINT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.uscite
    ADD CONSTRAINT uscite_pk_2 PRIMARY KEY (data, ordine);


--
-- Name: uscite_numerobolla_uindex; Type: INDEX; Schema: public; Owner: IngSof
--

CREATE UNIQUE INDEX uscite_numerobolla_uindex ON public.uscite USING btree (numerobolla);


--
-- Name: ingressi ingressi_articolo_codice_fk; Type: FK CONSTRAINT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.ingressi
    ADD CONSTRAINT ingressi_articolo_codice_fk FOREIGN KEY (article) REFERENCES public.articolo(codice);


--
-- Name: ordini ordine_negozi_codicefiscale_fk; Type: FK CONSTRAINT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.ordini
    ADD CONSTRAINT ordine_negozi_codicefiscale_fk FOREIGN KEY (negozio) REFERENCES public.negozi(codicefiscale);


--
-- Name: ordini ordine_tipoarticolo_descrizione_fk; Type: FK CONSTRAINT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.ordini
    ADD CONSTRAINT ordine_tipoarticolo_descrizione_fk FOREIGN KEY (tipoarticolo) REFERENCES public.tipiarticolo(nome);


--
-- Name: articolo typecheck; Type: FK CONSTRAINT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.articolo
    ADD CONSTRAINT typecheck FOREIGN KEY (tipoarticolo) REFERENCES public.tipiarticolo(nome);


--
-- Name: uscite uscite_negozi_codicefiscale_fk; Type: FK CONSTRAINT; Schema: public; Owner: IngSof
--

ALTER TABLE ONLY public.uscite
    ADD CONSTRAINT uscite_negozi_codicefiscale_fk FOREIGN KEY (negozio) REFERENCES public.negozi(codicefiscale);


--
-- PostgreSQL database dump complete
--

