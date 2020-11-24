package carshowroomsimulator.model.tablemodel;

import carshowroomsimulator.model.Vehicle;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ShowroomModel extends AbstractTableModel {

    private List<Vehicle> carShowrooms = null;
    private final static Object[] columnNames = {"ID", "BRAND", "MODEL", "CAPACITY", "PRICE"};

    private final static int ID = 0;
    private final static int BRAND = 1;
    private final static int MODEL = 2;
    private final static int CAPACITY = 3;
    private final static int PRICE = 4;

    public ShowroomModel() {}

    @Override
    public int getRowCount() {
        if(carShowrooms ==null) return 0;
        return carShowrooms.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if(carShowrooms == null) return null;
        Vehicle p = (Vehicle) carShowrooms.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return rowIndex + 1;
            case BRAND:
                return p.getBrand();
            case MODEL:
                return p.getModel();
            case CAPACITY:
                return p.getEngineCapacity();
            case PRICE:
                return p.getPrice();
            default:
                return p;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column].toString();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void setModelData(List<Vehicle> carShowrooms) {
        this.carShowrooms =  carShowrooms;
    }
    public Vehicle getPerson(int position) {
        return carShowrooms.get(position);
    }

}