package com.map.model;


import com.map.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Date;

/**
 * @author Yevhenii Semenov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment implements DtoConvertible<CommentDto> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @NotEmpty
    private String text;

    private Date date;

    @Override
    public CommentDto toDto() {
        return CommentDto.builder()
                .id(id)
                .user(user)
                .text(text)
                .date(date)
                .build();
    }
}
