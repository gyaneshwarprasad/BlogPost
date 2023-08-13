package com.blogepost.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id ;

    @NotEmpty
    @Size(min=2 , message = "title should be more then two characters ")
    private  String title ;

    @NotEmpty
    @Size(min=2 , message = "title should be more then two characters ")
    private String content ;

    @NotEmpty(message = "description is empty")
    private  String description ;
}
