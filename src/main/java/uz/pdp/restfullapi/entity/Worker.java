package uz.pdp.restfullapi.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Address address;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    private Department department;
}
