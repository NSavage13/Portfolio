package com.week3.jeapordy;

public class JeopardyData {
    private int id;
    private String answer;
    private String question;
    private int value;
    private String airdate;
    private String created_at;
    private String updated_at;
    private int category_id;
    private int game_id;
    private int invalid_count; 
    private Category category;

    public JeopardyData() {
    }

    public JeopardyData(int id, String answer, String question, int value, String airdate, String created_at, String updated_at,
                        int category_id, int game_id, Integer invalid_count, Category category) {
        this.id = id;
        this.answer = answer;
        this.question = question;
        this.value = value;
        this.airdate = airdate;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.category_id = category_id;
        this.game_id = game_id;
        this.invalid_count = invalid_count;
        this.category = category;
    }

    // Setters and Getters
    // id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // answer
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    // question
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    // value
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // airdate
    public String getAirdate() {
        return airdate;
    }

    public void setAirdate(String airdate) {
        this.airdate = airdate;
    }

    // created_at
    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    // updated_at
    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    // category_id
    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    // game_id
    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    // invalid_count
    public Integer getInvalid_count() {
        return invalid_count;
    }

    public void setInvalid_count(Integer invalid_count) {
        this.invalid_count = invalid_count;
    }

    // category
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "JeopardyData{" +
                "id=" + id +
                ", answer='" + answer + '\'' +
                ", question='" + question + '\'' +
                ", value=" + value +
                ", airdate='" + airdate + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                ", category_id=" + category_id +
                ", game_id=" + game_id +
                ", invalid_count=" + invalid_count +
                ", category=" + category +
                '}';
    }

    // Inner class representing Category
    public static class Category {
        private int id;
        private String title;
        private String created_at;
        private String updated_at;
        private int clues_count;

        public Category() {
        }

        public Category(int id, String title, String created_at, String updated_at, int clues_count) {
            this.id = id;
            this.title = title;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.clues_count = clues_count;
        }

        // Setters and Getters for Category
        // id
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        // title
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        // created_at
        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        // updated_at
        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        // clues_count
        public int getClues_count() {
            return clues_count;
        }

        public void setClues_count(int clues_count) {
            this.clues_count = clues_count;
        }

        @Override
        public String toString() {
            return "Category{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", created_at='" + created_at + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    ", clues_count=" + clues_count +
                    '}';
        }
    }
}
