-- V1__create_exercises_table.sql

-- Create exercises table
CREATE TABLE exercises (
                           id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                           name TEXT NOT NULL,
                           description TEXT,
                           type TEXT NOT NULL,
                           sets INT,
                           reps INT,
                           difficulty TEXT,
                           video_url TEXT,
                           rest_time DOUBLE PRECISION
);

-- Create exercise_muscle_groups join table
CREATE TABLE exercise_muscle_groups (
                                        exercise_id UUID NOT NULL REFERENCES exercises(id) ON DELETE CASCADE,
                                        muscle_group TEXT NOT NULL,
                                        PRIMARY KEY (exercise_id, muscle_group)
);