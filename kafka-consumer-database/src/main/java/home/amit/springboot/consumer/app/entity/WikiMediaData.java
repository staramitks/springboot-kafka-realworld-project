package home.amit.springboot.consumer.app.entity;
/*
User :- AmitSingh
Date :- 7/27/2023
Time :- 5:58 PM
Year :- 2023
*/

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="wikimedia_changes")
@Data
public class WikiMediaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String wikiData;

}
