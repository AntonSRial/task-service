package org.example.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.domain.model.TaskStatusDomain;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    private String id;

    private String title;

    private Date dueDate;

    private String description;

    private List<String> tags;

    private TaskStatus status;

}
