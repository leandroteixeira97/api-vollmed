ALTER TABLE IF EXISTS doctors ADD COLUMN expired BOOLEAN;
UPDATE doctors SET expired = false WHERE expired = null;