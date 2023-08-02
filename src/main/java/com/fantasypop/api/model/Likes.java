package com.fantasypop.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"user", "likedPost"})
public class Likes {
    private Long id;
    private User user; // the user that liked the Post
    private Post likedPost; // The post that was liked
}
