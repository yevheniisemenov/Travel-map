package com.map.model;

import com.map.dto.PlaceDto;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Yevhenii Semenov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Place implements DtoConvertible<PlaceDto> {

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

    @Override
    public PlaceDto toDto() {
        return PlaceDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .latitude(latitude)
                .longitude(longitude)
                .photos(photos)
                .rating(rating.entrySet().stream().collect(Collectors.toMap(
                        e -> e.getKey().toDto(),
                        Map.Entry::getValue
                )))
                .comments(comments.stream().map(DtoConvertible::toDto).collect(Collectors.toCollection(ArrayList::new)))
                .build();
    }
}
