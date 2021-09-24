package com.miu.minionlinemarkert.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private String id;
    private String reviewText;
    private String userId;
    private boolean approveStatus;
}
