package co.edu.demoacademico.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grupos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_grupo_codigo", columnNames = {"codigo_grupo"})
})
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "codigo", nullable = false)
    private Integer cupoMaximo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "asignatura_id", nullable = false, foreignKey = @ForeignKey(name = "fk_grupo_asignatura"))
    private Asignatura asignatura;

    public Grupo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(Integer cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
}
