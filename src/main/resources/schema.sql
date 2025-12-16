CREATE TABLE IF NOT EXISTS directors (
  id BIGSERIAL PRIMARY KEY,
  full_name VARCHAR(200) NOT NULL,
  country VARCHAR(100),
  birth_year INT
);

-- уникальность, чтобы работал ON CONFLICT по full_name
CREATE UNIQUE INDEX IF NOT EXISTS uk_directors_full_name
  ON directors(full_name);

CREATE TABLE IF NOT EXISTS films (
  id BIGSERIAL PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  release_year INT CHECK (release_year >= 1888),
  duration_min INT CHECK (duration_min >= 1),
  rating NUMERIC(3,1) CHECK (rating >= 0 AND rating <= 10),
  description VARCHAR(2000),
  director_id BIGINT NOT NULL REFERENCES directors(id)
);

-- уникальность, чтобы работал ON CONFLICT по (title, release_year)
CREATE UNIQUE INDEX IF NOT EXISTS uk_films_title_year
  ON films(title, release_year);
