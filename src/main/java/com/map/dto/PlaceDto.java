package com.map.dto;

import com.map.model.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class PlaceDto implements Dto<Place> {

    private Long id;
    @NotNull
    @Size(min = 3, max = 48)
    private String name;
    @NotNull
    @Min(24)
    private String description;
    @NotNull
    private BigDecimal latitude;
    @NotNull
    private BigDecimal longitude;
    private List<MultipartFile> photos = new ArrayList<>();
    private Map<UserDto, Integer> rating = new HashMap<>();
    private List<CommentDto> comments = new ArrayList<>();

    @Override
    public Place toEntity() {
        return Place.builder()
                .id(id)
                .name(name)
                .description(description)
                .latitude(latitude)
                .longitude(longitude)
                .photos(photos)
                .rating(rating.entrySet().stream().collect(Collectors.toMap(
                        e -> e.getKey().toEntity(),
                        Map.Entry::getValue
                )))
                .comments(comments.stream().map(Dto::toEntity).collect(Collectors.toCollection(ArrayList::new)))
                .build();
    }
}
