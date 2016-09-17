package com.map.dto;

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

/**
 * @author Yevhenii Semenov, Andrew Pasika
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceDto {

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

}
