package Dtos;

public class EmpleadosPorRegionDto {

    private String nombreRegion;
    private int cantidadEmpleados;

    /**
     * @return the nombreRegion
     */
    public String getNombreRegion() {
        return nombreRegion;
    }

    /**
     * @param nombreRegion the nombreRegion to set
     */
    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    /**
     * @return the cantidadEmpleados
     */
    public int getCantidadEmpleados() {
        return cantidadEmpleados;
    }

    /**
     * @param cantidadEmpleados the cantidadEmpleados to set
     */
    public void setCantidadEmpleados(int cantidadEmpleados) {
        this.cantidadEmpleados = cantidadEmpleados;
    }
}

