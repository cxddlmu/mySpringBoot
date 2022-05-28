package my.springboot.server.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "help_topic")
@Data
public class HelpTopic {

    @Id
    @Column(name = "help_topic_id")
    private Integer helpTopicId;


    @Column(name = "name")
    private String name;

    @Column(name = "help_category_id")
    private Integer helpCategoryId;
  @Column(name = "description")
    private String description;
@Column(name = "example")
    private String example;
@Column(name = "url")
    private String url;

}
