ALTER TABLE nutrition_plans
    ADD COLUMN IF NOT EXISTS user_id UUID,
    ADD COLUMN IF NOT EXISTS goal VARCHAR(255),
    ADD COLUMN IF NOT EXISTS daily_calories INTEGER,
    ADD COLUMN IF NOT EXISTS status VARCHAR(20) NOT NULL DEFAULT 'ARCHIVED';

CREATE TABLE IF NOT EXISTS meal_days (
    id UUID PRIMARY KEY,
    day VARCHAR(255) NOT NULL,
    nutrition_plan_id UUID,
    CONSTRAINT fk_meal_day_nutrition_plan
        FOREIGN KEY (nutrition_plan_id) REFERENCES nutrition_plans(id)
            ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_meal_days_nutrition_plan_id
    ON meal_days(nutrition_plan_id);

ALTER TABLE meals
    ADD COLUMN IF NOT EXISTS meal_day_id UUID,
    ADD COLUMN IF NOT EXISTS protein INTEGER,
    ADD COLUMN IF NOT EXISTS carbs INTEGER,
    ADD COLUMN IF NOT EXISTS fats INTEGER;

ALTER TABLE meals
    ADD CONSTRAINT fk_meal_day
        FOREIGN KEY (meal_day_id) REFERENCES meal_days(id)
            ON DELETE CASCADE;

CREATE INDEX IF NOT EXISTS idx_meals_meal_day_id
    ON meals(meal_day_id);

CREATE TABLE IF NOT EXISTS meal_ingredients (
    meal_id UUID NOT NULL,
    ingredient VARCHAR(255) NOT NULL,
    CONSTRAINT fk_meal_ingredient_meal
        FOREIGN KEY (meal_id) REFERENCES meals(id)
            ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_meal_ingredients_meal_id
    ON meal_ingredients(meal_id);
