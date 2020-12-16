package Dtos;

import java.math.BigDecimal;

/**
 *
 * @author DTI
 */
public class SalarioPorDepartamentoDto {

    private String nombreDepartamento;
    private BigDecimal salarioMinimo;
    private BigDecimal salarioMaximo;
    private BigDecimal salarioPromedio;

    /**
     * @return the nombreDepartamento
     */
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    /**
     * @param nombreDepartamento the nombreDepartamento to set
     */
    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    /**
     * @return the salarioMinimo
     */
    public BigDecimal getSalarioMinimo() {
        return salarioMinimo;
    }

    /**
     * @param salarioMinimo the salarioMinimo to set
     */
    public void setSalarioMinimo(BigDecimal salarioMinimo) {
        this.salarioMinimo = salarioMinimo;
    }

    /**
     * @return the salarioMaximo
     */
    public BigDecimal getSalarioMaximo() {
        return salarioMaximo;
    }

    /**
     * @param salarioMaximo the salarioMaximo to set
     */
    public void setSalarioMaximo(BigDecimal salarioMaximo) {
        this.salarioMaximo = salarioMaximo;
    }

    /**
     * @return the salarioPromedio
     */
    public BigDecimal getSalarioPromedio() {
        return salarioPromedio;
    }

    /**
     * @param salarioPromedio the salarioPromedio to set
     */
    public void setSalarioPromedio(BigDecimal salarioPromedio) {
        this.salarioPromedio = salarioPromedio;
    }

}

