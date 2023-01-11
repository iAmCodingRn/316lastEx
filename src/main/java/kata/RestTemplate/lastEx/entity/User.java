package kata.RestTemplate.lastEx.entity;

import lombok.*;


@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@ToString
 public class User {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;
}

