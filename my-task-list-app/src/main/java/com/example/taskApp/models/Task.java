package com.example.taskApp.models;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="tasks")
@JsonIgnoreProperties(value = {"createdOn"}, allowGetters = true)
public class Task{
    @Id
    private String taskId;

    @NotBlank
    @Size(max=100)
    @Indexed(unique=true)
    private String title;

    private Boolean completed = false;

    private Date createdOn = new Date();

    public Task() {
        super();
    }

    public Task(String title) {
        this.title = title;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return String.format(
                "Task[taskId=%s, title='%s', completed='%s']",
                taskId, title, completed);
    }
}