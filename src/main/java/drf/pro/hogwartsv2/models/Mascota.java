package drf.pro.hogwartsv2.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "mascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private long idMacota;

    private String nombre;

    private String especie;

    @OneToOne
    @JoinColumn(name = "estudiante_id", unique = true)
    private Estudiante estudiante;
}
