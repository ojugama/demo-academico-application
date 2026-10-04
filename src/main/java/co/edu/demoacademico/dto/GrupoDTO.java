package co.edu.demoacademico.dto;

public class GrupoDTO {
    private Long id;
    private String codigo;
    private Integer maximoCupos;
    private Long idAsignatura;
    private String nombreAsignatura;

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

    public void setMaximoCupos(Integer maximoCupos) {
        this.maximoCupos = maximoCupos;
    }

    public Long getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }
}
