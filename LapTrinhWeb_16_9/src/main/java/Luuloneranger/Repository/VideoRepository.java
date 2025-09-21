package Luuloneranger.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Luuloneranger.Entity.Video;

public interface VideoRepository extends JpaRepository<Video, Integer> {
	List<Video> findByTitleContaining(String keyword);
}
