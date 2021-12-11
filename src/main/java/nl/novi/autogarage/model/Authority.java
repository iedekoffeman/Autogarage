package nl.novi.autogarage.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
@IdClass(AuthorityKey.class)
//Implements serializable houdt in dat hij een key heeft die een hashcode kan geven
public class Authority implements Serializable {
    //Gezamenlijk is dat de key. De ID wordt bepaald door een combinatie van beide
    //Spring heeft moeite met 2x ID. In repository wil hij de type van de identificatie weten en bij een dubbele heb je een probleem.
    //Aparte klasse om de ID samen te stellen.
    //username: admin / authority: user of username: admin / authority: admin
    @Id
    @Column(nullable = false)
    private String username;

    @Id
    @Column(nullable = false)
    private String authority;


}
