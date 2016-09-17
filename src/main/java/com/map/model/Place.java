package com.map.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yevhenii Semenov, Andrew Pasika
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    private String description;

    @NonNull
    private BigDecimal latitude;

    @NonNull
    private BigDecimal longitude;

    @Transient
    private List<MultipartFile> photos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "USER_RATE")
    @MapKeyJoinColumn(name = "USER_ID")
    @Column(name = "RATE")
    private Map<User, Integer> rating = new HashMap<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

}
