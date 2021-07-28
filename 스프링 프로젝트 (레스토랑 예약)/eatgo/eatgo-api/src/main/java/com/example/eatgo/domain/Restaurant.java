package com.example.eatgo.domain;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
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

    private String name;

    private String address;


    @Transient // 임시로 통과
    private List<MenuItem> menuItems;

    public String getInformation() {
        return name + " in " + address;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);

     }

    public void setId(long id) {
        this.id = id;
    }

    public void updateInformation(String name, String address) {
        this.name =name;
        this.address = address;
    }
}
