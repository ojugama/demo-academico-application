package co.edu.demoacademico.model;

import jakarta.persistence.*;

@Entity
@Table(name = "grupos", uniqueConstraints = {
        @UniqueConstraint(name = "uk_grupo_codigo_asignatura", columnNames = {"codigo", "id_asignatura"})
})
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 20)
    private String codigo;

    @Column(name = "maximo_cupos", nullable = false)
    private Integer maximoCupos;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asignatura", nullable = false, foreignKey = @ForeignKey(name = "fk_grupo_asignatura"))
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

    public Integer getMaximoCupos() {
        return maximoCupos;
    }

    public void setMaximoCupos(Integer cupoMaximo) {
        this.maximoCupos = cupoMaximo;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }
}
