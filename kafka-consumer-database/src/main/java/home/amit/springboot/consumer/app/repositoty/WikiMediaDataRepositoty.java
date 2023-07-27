package home.amit.springboot.consumer.app.repositoty;
/*
User :- AmitSingh
Date :- 7/27/2023
Time :- 6:01 PM
Year :- 2023
*/

import home.amit.springboot.consumer.app.entity.WikiMediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikiMediaDataRepositoty extends JpaRepository<WikiMediaData, Long> {
}
