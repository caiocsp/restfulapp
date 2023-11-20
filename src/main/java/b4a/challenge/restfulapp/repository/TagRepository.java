package b4a.challenge.restfulapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import b4a.challenge.restfulapp.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
    
    Optional<Tag> findByName(String tagName);

}
