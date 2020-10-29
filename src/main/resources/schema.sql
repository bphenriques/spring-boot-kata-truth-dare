-- This needs to be integrated within a flyway
CREATE TABLE IF NOT EXISTS "truth_dare_challenges" (
  "id" BIGSERIAL,
  "challenge" VARCHAR NOT NULL,
  "type"      VARCHAR(50) NOT NULL
);
