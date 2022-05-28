package my.springboot.server.repository;

import my.springboot.server.entity.HelpTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpTopicRepository extends JpaRepository<HelpTopic, Integer> {
}
