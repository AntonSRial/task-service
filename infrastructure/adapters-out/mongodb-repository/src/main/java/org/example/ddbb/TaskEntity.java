package org.example.ddbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Document(collection = "Task")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity implements Serializable {

    /**
	 * 
	 */
	@Serial
    private static final long serialVersionUID = 8953615786709346205L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    private String title;

    private Date dueDate;

    private String description;

    private List<String> tags;

    private TaskStatus status;



}
