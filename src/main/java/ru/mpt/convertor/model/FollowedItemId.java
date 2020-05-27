package ru.mpt.convertor.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
@ToString
public class FollowedItemId implements Serializable {
    private int userId;
    private int itemId;
}
