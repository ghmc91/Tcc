/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package importxml;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "curso", catalog = "tcc", schema = "")
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c")
    , @NamedQuery(name = "Curso.findByIdCurso", query = "SELECT c FROM Curso c WHERE c.idCurso = :idCurso")
    , @NamedQuery(name = "Curso.findByNomeCurso", query = "SELECT c FROM Curso c WHERE c.nomeCurso = :nomeCurso")
    , @NamedQuery(name = "Curso.findByTipoCurso", query = "SELECT c FROM Curso c WHERE c.tipoCurso = :tipoCurso")})
public class Curso implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCurso")
    private Integer idCurso;
    @Column(name = "nomeCurso")
    private String nomeCurso;
    @Column(name = "tipoCurso")
    private String tipoCurso;

    public Curso() {
    }

    public Curso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        Integer oldIdCurso = this.idCurso;
        this.idCurso = idCurso;
        changeSupport.firePropertyChange("idCurso", oldIdCurso, idCurso);
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        String oldNomeCurso = this.nomeCurso;
        this.nomeCurso = nomeCurso;
        changeSupport.firePropertyChange("nomeCurso", oldNomeCurso, nomeCurso);
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        String oldTipoCurso = this.tipoCurso;
        this.tipoCurso = tipoCurso;
        changeSupport.firePropertyChange("tipoCurso", oldTipoCurso, tipoCurso);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCurso != null ? idCurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Curso)) {
            return false;
        }
        Curso other = (Curso) object;
        if ((this.idCurso == null && other.idCurso != null) || (this.idCurso != null && !this.idCurso.equals(other.idCurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "importxml.Curso[ idCurso=" + idCurso + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
