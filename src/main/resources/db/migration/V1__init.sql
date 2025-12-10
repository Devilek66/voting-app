CREATE TABLE voter (
id BIGSERIAL PRIMARY KEY,
external_id VARCHAR(255) NOT NULL UNIQUE,
blocked BOOLEAN NOT NULL DEFAULT FALSE
);


CREATE TABLE election (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(255) NOT NULL
);


CREATE TABLE option (
id BIGSERIAL PRIMARY KEY,
label VARCHAR(255) NOT NULL,
election_id bigint not null references election(id) on delete cascade
);


CREATE TABLE vote (
id BIGSERIAL PRIMARY KEY,
voter_id bigint not null references voter(id),
election_id bigint not null references election(id),
option_id bigint not null references option(id),
cast_at timestamptz not null default now(),
CONSTRAINT unique_voter_election UNIQUE (voter_id, election_id)
);