
-- Drop table

-- DROP TABLE public.arenas;

CREATE TABLE public.arenas (
	id int2 NOT NULL,
	"name" varchar(64) NOT NULL,
	description varchar(1024) NOT NULL,
	arena_bonus_todo varchar NULL,
	CONSTRAINT arenas_pk PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.bonus_types;

CREATE TABLE public.bonus_types (
	id int2 NOT NULL,
	"name" varchar(16) NOT NULL,
	CONSTRAINT bonus_types_pk PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.christmas;

CREATE TABLE public.christmas (
	id int2 NOT NULL,
	"name" varchar NULL,
	icon_code varchar NULL,
	priority int2 NULL,
	is_valid bool NULL
);

-- Drop table

-- DROP TABLE public.countries;

CREATE TABLE public.countries (
	code varchar(4) NOT NULL,
	"name" varchar(64) NOT NULL,
	CONSTRAINT countries_pk PRIMARY KEY (code)
);

-- Drop table

-- DROP TABLE public.player_types;

CREATE TABLE public.player_types (
	code varchar(8) NOT NULL,
	"name" varchar(32) NOT NULL,
	description varchar(1024) NOT NULL,
	CONSTRAINT player_types_pk PRIMARY KEY (code)
);

-- Drop table

-- DROP TABLE public.skills;

CREATE TABLE public.skills (
	code varchar(8) NOT NULL,
	"name" varchar(32) NOT NULL,
	description varchar(1024) NOT NULL,
	CONSTRAINT skills_pk PRIMARY KEY (code)
);

-- Drop table

-- DROP TABLE public.teams;

CREATE TABLE public.teams (
	id serial NOT NULL,
	"name" varchar(64) NULL,
	points int2 NOT NULL,
	funds int4 NOT NULL,
	CONSTRAINT teams_pk PRIMARY KEY (id)
);

-- Drop table

-- DROP TABLE public.abilities;

CREATE TABLE public.abilities (
	id int2 NOT NULL,
	"name" varchar(64) NOT NULL,
	description varchar(1024) NOT NULL,
	skill_id varchar(8) NOT NULL,
	bonus int2 NOT NULL,
	bonus_type_id int2 NOT NULL,
	"type" varchar(1) NOT NULL,
	CONSTRAINT abilities_pk PRIMARY KEY (id),
	CONSTRAINT abilities_fk FOREIGN KEY (bonus_type_id) REFERENCES bonus_types(id),
	CONSTRAINT abilities_fk1 FOREIGN KEY (skill_id) REFERENCES skills(code)
);

-- Drop table

-- DROP TABLE public.players;

CREATE TABLE public.players (
	"name" varchar(64) NOT NULL,
	id bigserial NOT NULL,
	creation_date timestamp NOT NULL,
	death_date timestamp NULL,
	type_id varchar(8) NOT NULL,
	price int4 NULL,
	health int2 NULL,
	power int2 NULL,
	team_id int4 NULL,
	CONSTRAINT players_pk PRIMARY KEY (id),
	CONSTRAINT players_fk FOREIGN KEY (type_id) REFERENCES player_types(code),
	CONSTRAINT players_fk2 FOREIGN KEY (team_id) REFERENCES teams(id)
);

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id serial NOT NULL,
	"name" varchar(64) NOT NULL,
	"password" varchar(32) NOT NULL,
	team_id int4 NOT NULL,
	country_id varchar(4) NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id),
	CONSTRAINT users_fk FOREIGN KEY (country_id) REFERENCES countries(code),
	CONSTRAINT users_fk_1 FOREIGN KEY (team_id) REFERENCES teams(id)
);

-- Drop table

-- DROP TABLE public.n_player_ability;

CREATE TABLE public.n_player_ability (
	player_id int8 NOT NULL,
	ability_id int2 NOT NULL,
	CONSTRAINT n_player_ability_fk FOREIGN KEY (player_id) REFERENCES players(id),
	CONSTRAINT n_player_ability_fk_1 FOREIGN KEY (ability_id) REFERENCES abilities(id)
);

-- Drop table

-- DROP TABLE public.n_player_skill;

CREATE TABLE public.n_player_skill (
	player_id int8 NOT NULL,
	skill_id varchar(8) NOT NULL,
	value int2 NOT NULL,
	CONSTRAINT n_player_skill_fk FOREIGN KEY (player_id) REFERENCES players(id),
	CONSTRAINT n_player_skill_fk_1 FOREIGN KEY (skill_id) REFERENCES skills(code)
);

-- Drop table

-- DROP TABLE public.n_team_player;

CREATE TABLE public.n_team_player (
	team_id int4 NOT NULL,
	player_id int8 NOT NULL,
	CONSTRAINT n_team_player_fk FOREIGN KEY (team_id) REFERENCES teams(id),
	CONSTRAINT n_team_player_fk_1 FOREIGN KEY (player_id) REFERENCES players(id)
);
