package com.map.dto;


import com.map.model.Comment;
import com.map.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Yevhenii Semenov
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto implements Dto<Comment> {

    private long id;

    @NotNull
    private User user;

    @NotEmpty
    @Min(5)
    private String text;

    @NotNull
    private Date date;


    @Override
    public Comment toEntity() {
        return Comment.builder()
                .id(id)
                .user(user)
                .text(text)
                .date(date)
                .build();
    }
}
