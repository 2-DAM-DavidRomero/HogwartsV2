package drf.pro.hogwartsv2.repositories;

import drf.pro.hogwartsv2.models.Asignatura;
import drf.pro.hogwartsv2.models.Casa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {
}
