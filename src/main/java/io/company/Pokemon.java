package io.company;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter @Setter @ToString @NoArgsConstructor @AllArgsConstructor
@Entity
public class Pokemon {
    @Id
    String Name;
    String location;
    int number;
    int high;
    int kg;
    String use;



}
