package com.example.eatgo.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Array;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 생성자
@Builder
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long categoryId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;


    @Transient // 임시로 통과
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuItem> menuItems;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Review> reviews;

    public String getInformation() {
        return name + " in " + address;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void updateInformation(String name, String address) {
        this.name =name;
        this.address = address;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);

    }

    public void setReviews(List<Review> reviews) {
        this.reviews = new ArrayList<>(reviews);
    }
}
